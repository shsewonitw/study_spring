package com.tje.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/regist")
public class RegistController {

	// @GetMapping("/regist/step1")
	@GetMapping("/step1")
	public void s1() {
		System.out.println("s1 �޼ҵ� ����");
	}
	
	@GetMapping("/step2")
	public void s2() {
		System.out.println("s2 �޼ҵ� ����");
	}
	
	@GetMapping("/step3")
	public void s3() {
		System.out.println("s3 �޼ҵ� ����");
	}
	
	
	
}
