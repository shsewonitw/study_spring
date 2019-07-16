package com.tje.webapp.controller;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tje.webapp.model.*;
import com.tje.webapp.service.*;

@Controller
public class MemberJoinController {

	private MemberRegistService mrService = new MemberRegistService();
	private MemberSelectService msService = new MemberSelectService();
	
	@GetMapping("/member_join.do")
	@ResponseBody
	public String memberJoinForm(HttpServletRequest request) {
		String type = request.getParameter("type");
		String member_id = null;
		String nickname = null;
		String email = null;
		
		try(Connection conn = ConnectionProvider.getConnection()){
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
				values.put("conn", conn);
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
				values.put("conn", conn);
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
					response.setContentType("text/plane;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println(false);
					out.flush();
					out.close();
					return null;
				}
				
				nickname = nickname.trim().toLowerCase();
				member.setNickname(nickname);
				values.put("conn", conn);
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
				values.put("conn", conn);
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
					response.setContentType("text/plane;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println(false);
					out.flush();
					out.close();
					return null;
				}
				
				email = email.trim().toLowerCase();
				member.setEmail(email);
				values.put("conn", conn);
				values.put("member",member);
				selectedMember = (Member)msService.service(values).get("result"); 
				
				if(selectedMember == null) {
					result = false;
					return null;
				}
				
				result = email.equals(selectedMember.getEmail()) ? true : false;
			}
			
			response.setContentType("text/plane;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
