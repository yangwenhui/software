package com.iezview.site.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.iezview.config.SwaySettings;
import com.iezview.domain.IezSwayProject;
import com.iezview.service.IezSwayProjectService;
import com.iezview.utils.FileUtil;

@Controller
public class ProjectController {
	@Autowired
	private SwaySettings settings;
	@Autowired
	private IezSwayProjectService iezProjectService;

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@RequestMapping(value = "/project_file", method = RequestMethod.GET)
	public String file_list(HttpServletRequest request, Model model) {
		String id = request.getParameter("projectId");
		try {
			IezSwayProject isp = iezProjectService.findById(new Long(id));
			model.addAttribute("isp", isp);
		} catch (Exception e) {
			throw new UsernameNotFoundException("error");
		}
		return "project_file";
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@RequestMapping(value = "/add_project", method = RequestMethod.POST)
	public String addProejct(HttpServletRequest request) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		String name = request.getParameter("name");
		if (username == null || "".equals(username)) {
			return "redirect:login";
		}
		if (name == null || "".equals(name)) {
			throw new UsernameNotFoundException("error");
		}
		IezSwayProject isp = new IezSwayProject();
		isp.setName(name);
		isp.setAuthor(userDetails.getUsername());
		isp.setTime(new Date());
		isp.setStatus("1");
		isp = iezProjectService.save(isp);
		return "redirect:upload_img?projectId=" + isp.getId();
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@RequestMapping(value = "/delete_project", method = RequestMethod.GET)
	public String deleteProject(@RequestParam("id") Long id) {
		if (id == null || "".equals(id)) {
			throw new UsernameNotFoundException(id + " cannot be found");
		}
		IezSwayProject isp = iezProjectService.findById(id);
		iezProjectService.delete(isp);
		String tempPath = settings.getUploadTempPath() + "/" + id + "/";
		String topPath = settings.getProjectPath() + "/" + id + "/top/";
		new Thread(new Runnable() {
			@Override
			public void run() {
				FileUtil.deleteDir(topPath);
				FileUtil.deleteDir(tempPath);
			}
		}).start();
		return "redirect:home";
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@RequestMapping(value = "/update_project", method = RequestMethod.POST)
	public String updateInfo(@RequestParam("id") Long id, @RequestParam("status") String status) {
		if (id == null || "".equals(id)) {
			throw new UsernameNotFoundException(id + " cannot be found");
		}
		IezSwayProject isp = iezProjectService.findById(id);
		isp.setStatus(status);
		iezProjectService.save(isp);
		return "redirect:home";
	}

	@RequestMapping(value = "/upload_img", method = RequestMethod.GET)
	public String uplaod_img(Model model, @RequestParam("projectId") Long projectId) {
		if (projectId == null || "".equals(projectId)) {
			throw new UsernameNotFoundException("error");
		}
		IezSwayProject isp = iezProjectService.findById(projectId);
		if (isp == null) {
			throw new UsernameNotFoundException("error");
		}
		model.addAttribute("isp", isp);
		model.addAttribute("type", "image");
		return "upload";
	}

	@RequestMapping(value = "/upload_obj", method = RequestMethod.GET)
	public String uplaod_obj(Model model, @RequestParam("projectId") Long projectId) {
		if (projectId == null || "".equals(projectId)) {
			throw new UsernameNotFoundException("error");
		}
		IezSwayProject isp = iezProjectService.findById(projectId);
		if (isp == null) {
			throw new UsernameNotFoundException("error");
		}
		model.addAttribute("isp", isp);
		model.addAttribute("type", "obj");
		return "upload";
	}

}
