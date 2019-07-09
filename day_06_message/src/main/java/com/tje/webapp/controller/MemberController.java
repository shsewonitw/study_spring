package com.tje.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tje.webapp.service.*;
import com.tje.webapp.model.*;
import java.util.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberSearchByIdService msbIDService;
	@Autowired
	private MemberInsertService miService;
	@Autowired
	private MemberLoginService mlService;
	
	@GetMapping("/insert")
	public String insertForm() {
		return "member/insertForm";
	}
	
	@PostMapping("/insert")
	public String insertSubmit(Member member , Model model) {
		if(msbIDService.service(member) != null) {
			return "member/insertForm";
		}
		if( (Boolean)miService.service(member) )
			model.addAttribute("insert_result",true);
		else
			model.addAttribute("insert_result",false);
		return "member/insertSubmit";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "member/loginForm";
	}
	
	@PostMapping("/login")
	public String loginSubmit(Member member, Model model, HttpSession session) {
		Member target = null;
		if( ( target = (Member)msbIDService.service(member) ) == null ) {
			return "member/loginForm";
		}
		
		// 로그인 정보만 가진 객체에 이름값을 추가
		member.setName(target.getName());
		ArrayList<Member> args = new ArrayList<>();
		args.add(member);
		args.add(target);
		boolean isLogin = (boolean)mlService.service(args);
		model.addAttribute("login_result",isLogin);
		
		if(isLogin)
			session.setAttribute("loginMember", target);
		
		return "member/loginSubmit";
	}
	
	@GetMapping("/logout")
	public String logoutPost(HttpSession session) {
		session.removeAttribute("loginMember");
		return "member/logoutForm";
	}
	
}
