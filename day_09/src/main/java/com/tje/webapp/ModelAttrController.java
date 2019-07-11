package com.tje.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tje.webapp.model.*;
@Controller
public class ModelAttrController {
	@GetMapping("/model_attr_1")
	public String model_attr_1(Member member) {
		return "model_attr_1";
	}
	
	@GetMapping("/model_attr_2")
	public String model_attr_2(@ModelAttribute("m") Member member) {
		return "model_attr_2";
	}
	
	@GetMapping("/model_attr_3")
	public String model_attr_3(@ModelAttribute("m") Member member) {
		return "model_attr_3";
	}
	
	@GetMapping("/model_attr_4")
	public String model_attr_4(@ModelAttribute("m") Member member) {
		return "model_attr_4";
	}
	
	@GetMapping("/test")
	public String model_attr_5(@ModelAttribute("m") Member member) {
		return "step1";
	}
}
