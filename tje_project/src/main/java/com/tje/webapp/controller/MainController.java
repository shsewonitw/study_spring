package com.tje.webapp.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tje.webapp.dao.MemberDAO;

@Controller
public class MainController {
	
//	@Autowired
//	private MemberDAO memberDAO;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
//		System.out.println("memberDAO in main : "+memberDAO.toString());
		return "forms/main";
	}
		
	

}
