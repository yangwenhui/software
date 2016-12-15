package com.iezview.site.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.iezview.config.SwaySettings;
import com.iezview.domain.IezSwayProject;
import com.iezview.domain.IezSwayUpload;
import com.iezview.service.IezSwayProjectService;
import com.iezview.service.IezSwayUploadService;
import com.iezview.utils.FileUtil;

@Controller
public class FileController {
	@Autowired
	private SwaySettings settings;
	@Autowired
	private IezSwayProjectService iezProjectService;
	@Autowired
	private IezSwayUploadService iezUploadService;

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@RequestMapping(value = "/download_project_file", method = RequestMethod.GET)
	@ResponseBody
	public void download(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		if (id == null || "".equals(id)) {
			throw new UsernameNotFoundException("error");
		}
		IezSwayUpload isu = iezUploadService.findById(new Long(id));
		if (isu == null) {
			throw new UsernameNotFoundException("error");
		}
		String root = settings.getProjectPath();
		String fullPath = root + isu.getPath();
		File downloadFile = new File(fullPath);
		ServletContext context = request.getServletContext();
		String mimeType = context.getMimeType(fullPath);
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}
		System.out.println("MIME type: " + mimeType);
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(headerKey, headerValue);
		try {
			InputStream myStream = new FileInputStream(fullPath);
			IOUtils.copy(myStream, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		String id = request.getParameter("projectId");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String root = settings.getProjectPath();
		String context = "/" + id + "/upload/";
		String filePath = root + context + name;
		String suffix = name.substring(name.lastIndexOf(".") + 1).toLowerCase();

		if (id == null || "".equals(id)) {
			throw new UsernameNotFoundException("error");
		}
		if (type == null || "".equals(type)) {
			throw new UsernameNotFoundException("error");
		}
		IezSwayProject isp = iezProjectService.findById(new Long(id));
		IezSwayUpload isu = new IezSwayUpload();
		if (isp != null && !file.isEmpty()) {
			try {
				FileUtil.mkdirs(root + context);
				File temp = new File(filePath);
				file.transferTo(temp);
				isu.setAuthor(username);
				isu.setSuffix(suffix);
				isu.setTime(new Date());
				isu.setPath(context + name);
				isu.setType(type);
				isu.setName(name);
				isp.addIezSwayUpload(isu);
				iezProjectService.save(isp);
				return "You successfully uploaded " + name + " into " + name + "!";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name + " because the file was empty.";
		}
	}
}
