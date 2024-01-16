package com.khit.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HOMEController {

	@GetMapping("/")
	public String home() {
		return "index";		//index.html(파일)
	}
}
