package com.tje.webapp.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tje.webapp.model.*;
import com.tje.webapp.service.MemberInsertService;
import com.tje.webapp.service.MemberLoginService;
import com.tje.webapp.service.MemberSearchByIdService;
import com.tje.webapp.service.MessageCountByReceiverService;
import com.tje.webapp.service.MessageSearchByReceiverService;
import com.tje.webapp.setting.PagingInfo;

import java.util.*;
//@Controller

import javax.servlet.http.HttpSession;

// @RestController 어노테이션
// 현재 클래스의 모든 맵핑 메소드의 리턴값을
// 응답으로 사용하는 경우에 적용하는 어노테이션
// @ResponseBody 어노테이션의 중복을 방지
@Controller
public class JSONController_02 {
	@Autowired
	private MemberSearchByIdService msbIDService;
	@Autowired
	private MemberInsertService miService;
	
	@GetMapping("/json/ex_04")
	public String ex_04() {
		return "json/memberForm";
	}
	
	@PostMapping("/json/ex_05")
	@ResponseBody
	public Member ex_05(
			@RequestBody Member member) {
		System.out.printf("id= %s, password= %s, name= %s\n",
				member.getMember_id(), member.getPassword(), member.getName());
		return member;
	}
	
	// 회원가입 페이지로 이동하는 메소드
	@GetMapping("/json/ex_06")
	public String ex_06() {
		return "member/insertForm_JSON";
	}
	
	
	// 회원가입 정보를 AJAX로 전달받아
	// 생성 성공 , 실패 여부를 반환하는 메소드
	// 성공 : {"result" : "S"}
	// 실패 : {"result" : "F"}
	@PostMapping("/json/ex_07")
	@ResponseBody
	public String ex_07(@RequestBody Member member) {
		
		System.out.println(member.getMember_id() + "," + member.getPassword() + "," + member.getName());
		if (msbIDService.service(member) != null) {
			System.out.println(1);
			return "{\"result\" : \"F\"}";
		}
		
		if ((Boolean) miService.service(member)) {
			System.out.println(2);
			return "{\"result\" : \"S\"}";
		} else {
			System.out.println(3);
			return "{\"result\" : \"F\"}";
		}
		
		
	}
}
