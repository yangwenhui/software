package com.iezview.site.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iezview.domain.IezSwayProject;
import com.iezview.service.IezSwayProjectService;

@Controller
public class HomeController {
	@Autowired
	private IezSwayProjectService iezProjectService;

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@RequestMapping(value = { "", "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		try {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			List<IezSwayProject> isps = iezProjectService.findByAuthor(userDetails.getUsername());
			model.addAttribute("isps", isps);
		} catch (Exception e) {
			throw new UsernameNotFoundException("cannot be found");
		}
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

}
