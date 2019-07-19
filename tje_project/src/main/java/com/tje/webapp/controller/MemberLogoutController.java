package com.tje.webapp.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tje.webapp.model.*;
import com.tje.webapp.service.*;

@Controller
public class MemberLogoutController {
	
	
	@PostMapping("/auth/member_logout.do")
	@ResponseBody
	public Boolean memberJoinSubmit(HttpServletRequest request,HttpServletResponse response) {

		HttpSession session = request.getSession();
		session.removeAttribute("login_member");
		
		//System.out.println(formPage);
		try {
			response.sendRedirect(request.getParameter("url"));
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	

	
}
