package com.project.ssiach6ex1.controller;

import com.project.ssiach6ex1.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainPageController {

	private final ProductService productService;

	@GetMapping("/main")
	public String main(Authentication auth, Model model) {
		model.addAttribute("username", auth.getName());
		model.addAttribute("products", productService.findAll());
		return "main";
	}
}
