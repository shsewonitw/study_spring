package com.tje.webapp.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tje.webapp.model.*;
import com.tje.webapp.service.*;

@Controller
public class MemberLoginController {
	
	@Autowired
	private MemberSelectService msService;
	
	@PostMapping("/member_login.do")
	@ResponseBody
	public Boolean memberJoinSubmit(HttpServletRequest request) {

		String member_id = request.getParameter("member_id").trim().toLowerCase();
		String password= request.getParameter("password").trim().toLowerCase();
		
		Member member = new Member(member_id, password, null, null, null, null);
		Member selectedMember = null;
			HashMap<String,Object> values = new HashMap<>();
			values.put("member",member);
			
			selectedMember = (Member)msService.service(values).get("result");
			
			if(selectedMember == null)
				return null;
			
			if( member.getPassword().equals(selectedMember.getPassword()) ) {
				// 로그인 성공
				
				HttpSession session = request.getSession();
				session.setAttribute("login_member", selectedMember);
				
//				response.setContentType("text/plane;charset=utf-8");
//				PrintWriter out = response.getWriter();
//				out.println(true);
//				out.flush();
//				out.close();
				return true;
			} else {
				// 로그인 실패
//				response.setContentType("text/plane;charset=utf-8");
//				PrintWriter out = response.getWriter();
//				out.println(false);
//				out.flush();
//				out.close();
				return false;
			}
		
	}
	

	
}
