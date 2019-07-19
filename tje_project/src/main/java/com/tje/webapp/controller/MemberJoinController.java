package com.tje.webapp.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tje.webapp.model.*;
import com.tje.webapp.service.*;

@Controller
public class MemberJoinController {

	@Autowired
	private MemberRegistService mrService;
	@Autowired
	private MemberSelectService msService;
	
	@PostMapping("/member_join.do")
	@ResponseBody
	public Boolean memberJoinSubmit(HttpServletRequest request) {
		String member_id = request.getParameter("member_id").trim().toLowerCase();
		String password= request.getParameter("password").trim().toLowerCase();
		String nickname = request.getParameter("nickname").trim().toLowerCase();
		String email = request.getParameter("email").trim().toLowerCase();
		
		Member member = new Member(member_id, password, nickname, email, null, null);
		
			HashMap<String, Object> values = new HashMap<>();
			values.put("member",member);
			
			boolean result = (boolean)mrService.service(values).get("result");
			
			
		
			// 회원가입 성공
//			response.setContentType("text/plane;charset=utf-8");
//			PrintWriter out = response.getWriter();
//			out.println(result);
//			out.flush();
//			out.close();
		
		return result;
	}
	
	@GetMapping("/member_join.do")
	@ResponseBody
	public Boolean memberJoinForm(HttpServletRequest request) {
		String type = request.getParameter("type");
		System.out.println(type);
		String member_id = null;
		String nickname = null;
		String email = null;
		

		Member member = new Member();
		HashMap<String, Object> values = new HashMap<>();
		Member selectedMember = null;
		boolean result = false;
		if(type.equals("idCheck")) {
			member_id = request.getParameter("member_id");
			
			if(member_id != null && member_id.length() == 0) {
				return null;
			}
			member_id = member_id.trim().toLowerCase();
			member.setMember_id(member_id);
			values.put("member",member);
			selectedMember = (Member)msService.service(values).get("result"); 
			
			if(selectedMember == null) {
				result = false;
				return null;
			}
			result = member_id.equals(selectedMember.getMember_id()) ? true : false;
		} else if(type.equals("nickNameCheck")) {
			nickname = request.getParameter("nickName");
			
			if(nickname != null && nickname.length() == 0) {
				return null;
			}
			nickname = nickname.trim().toLowerCase();
			member.setNickname(nickname);
			values.put("member",member);
			selectedMember = (Member)msService.service(values).get("result"); 
			
			if(selectedMember == null) {
				result = false;
				return null;
			}
			
			result = nickname.equals(selectedMember.getNickname()) ? true : false;
		}	else if(type.equals("modify_nickNameCheck")) {
			nickname = request.getParameter("nickName");
			
			if(nickname != null && nickname.length() == 0) {
				return null;
			}
			HttpSession session = request.getSession();
			Member login_member = (Member)session.getAttribute("login_member");
			
			if( (login_member != null) && (nickname.equals(login_member.getNickname())) ) {
//				response.setContentType("text/plane;charset=utf-8");
//				PrintWriter out = response.getWriter();
//				out.println(false);
//				out.flush();
//				out.close();
//				return null;
				return false;
			}
			
			nickname = nickname.trim().toLowerCase();
			member.setNickname(nickname);
			values.put("member",member);
			selectedMember = (Member)msService.service(values).get("result"); 
			
			if(selectedMember == null) {
				result = false;
				return null;
			}
			
			result = nickname.equals(selectedMember.getNickname()) ? true : false;
		} else if(type.equals("emailCheck")) {
			email = request.getParameter("email");
			if(email != null && email.length() == 0) {
				return null;
			}
			
			
			email = email.trim().toLowerCase();
			member.setEmail(email);
			values.put("member",member);
			selectedMember = (Member)msService.service(values).get("result"); 
			
			if(selectedMember == null) {
				result = false;
				return null;
			}
			
			result = email.equals(selectedMember.getEmail()) ? true : false;
		} else if(type.equals("modify_emailCheck")) {
			email = request.getParameter("email");
			if(email != null && email.length() == 0) {
				return null;
			}
			
			HttpSession session = request.getSession();
			Member login_member = (Member)session.getAttribute("login_member");
			
			if( (login_member != null) && (email.equals(login_member.getEmail())) ) {
//				response.setContentType("text/plane;charset=utf-8");
//				PrintWriter out = response.getWriter();
//				out.println(false);
//				out.flush();
//				out.close();
				return false;
			}
			
			email = email.trim().toLowerCase();
			member.setEmail(email);
			values.put("member",member);
			selectedMember = (Member)msService.service(values).get("result"); 
			
			if(selectedMember == null) {
				result = false;
				return null;
			}
			
			result = email.equals(selectedMember.getEmail()) ? true : false;
		}
		
//		response.setContentType("text/plane;charset=utf-8");
//		PrintWriter out = response.getWriter();
//		out.println(result);
//		out.flush();
//		out.close();
		
		
		return result;
	}
	
	
}
