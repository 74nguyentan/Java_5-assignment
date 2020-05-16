package edu.poly.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.assignment.model.User;
import edu.poly.assignment.services.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping("/home")
	public String home(ModelMap model, User user) {
		String username = user.getUsername();
		String password = user.getPassword();

		if (!username.equals("") || !password.equals("")) {
			List<User> list = userService.findByUsername(username);
			if (!list.isEmpty()) {
				User us = list.get(0);
				String pas = us.getPassword();

				if (password.equals(pas)) {

					return "layouts/home";
				} else {
					User use = new User();
					model.addAttribute("User", use);
					model.addAttribute("message", "Sai mật khẩu");
					return "layouts/login";
				}

			}

			else {
				User use = new User();
				model.addAttribute("User", use);
				model.addAttribute("message", "Sai tên đăng nhập");
				return "layouts/login";
			}
		} else {
			User use = new User();
			model.addAttribute("User", use);
			model.addAttribute("message", "vui lòng nhập đủ thông tin đăng nhập");
			return "layouts/login";
		}

	}

	@GetMapping("/login")
	public String login(ModelMap model) {
		User user = new User();
		model.addAttribute("User", user);

		return "layouts/Login";
	}
	
	@GetMapping("/home2")
	public String home2() {
		
		return "layouts/home";
	}
}
