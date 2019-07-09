package com.tje.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import javax.servlet.http.HttpSession;

import com.tje.webapp.service.*;
import com.google.gson.Gson;
import com.tje.webapp.model.*;

@Controller
@RequestMapping("/message")
public class MessageController {
	@Autowired
	private MemberSearchByIdService membersbIDService;
	@Autowired
	private MessageInsertService miService;
	@Autowired
	private MessageSearchByReceiverService msbrService;
	@Autowired
	private MessageSearchByIdService messagesbIDService;
	@Autowired
	private MessageUpdateReceiveTimeService murtService;
	
	@GetMapping("/transform")
	public String transformForm() {
		return "message/transform";
	}
	
	@PostMapping("/transform")
	public String transformSubmit(Message message, Model model) {
		int message_id = (Integer)miService.service(message);
		model.addAttribute("message_id", message_id);
		return "message/transformSubmit";
	}
	
	@PostMapping("/searchID")
	// @ResponseBody 어노테이션
	// 현재 메소드에서 반환(return)하는 값을
	// 클라이언트에게 즉시 전송하도록 제어하는 어노테이션
	// (VIEW 페이지로 이동하지 않고 현재 메소드에서 실행을 종료함)
	@ResponseBody
	public String searchID(
			@RequestParam(value="searchID") String searchID) {
		//System.out.println("searchID : " + searchID);
		Member member = new Member();
		member.setMember_id(searchID);
		
		List<Member> memberList = 
				(List<Member>)membersbIDService.service(member, true);
		
		Gson gson = new Gson();
		String strJson = gson.toJson(memberList);
		//System.out.println(strJson);
		
		//for( Member m : memberList )
		//	System.out.println(m.getMember_id());
				
		return strJson;
	}
	
	@GetMapping("/receive")
	public String receiveForm(Model model, HttpSession session) {
		Member loginMember = 
				(Member)session.getAttribute("loginMember");
		
		Message message = new Message();		
		message.setReceiver(loginMember.getMember_id());

		model.addAttribute("rList", msbrService.service(message));
		
		return "message/receiveForm";
	}
	
	// PathVariable : URL 정보를 변수의 값으로 사용하는 방법
	// EX) http://www.naver.com/article/10
	// EX) http://www.google.com/dept/10/emp/5
	@GetMapping("/content/{message_id}")
	public String content(
			Model model,
			@PathVariable("message_id") int message_id) {
		
		Message message = new Message();
		message.setMessage_id(message_id);
		murtService.service(message);
		model.addAttribute("message",messagesbIDService.service(message));
		return "message/message";
	}
}













