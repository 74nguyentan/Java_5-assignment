package edu.poly.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistical")
public class statisticalController {

	@GetMapping("/TongHop")
	private String TongHop() {
		
		return "/statistical/TongHop";
	}
	
}
