package com.kenzan.processor.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kenzan.processor.entities.User;
import com.kenzan.processor.services.UserService;

@Controller
public class AuthController {
	@Autowired
	private UserService serv;
	
	@PostMapping("register")
	public String register(@RequestBody User user, RedirectAttributes redir) {
		try {
			User newUser = serv.registerUser(user);
			redir.addAttribute("user", newUser);
		} catch (Exception e) {
			System.err.println("User Registration Error: " + e);
		}
		return "redirect:success";
	}
	
	@GetMapping("success")
	public void success(@ModelAttribute("user") User user, HttpServletResponse resp) {
		if (user.getUsername() != null) {
			resp.setStatus(200);
		} else {
			resp.setStatus(409);
		}
	}
	
}
