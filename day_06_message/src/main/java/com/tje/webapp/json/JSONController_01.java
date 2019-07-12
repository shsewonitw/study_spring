package com.tje.webapp.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tje.webapp.model.*;
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
@RestController
public class JSONController_01 {
	
	@Autowired
	private PagingInfo pagingInfo;
	@Autowired	
	private MessageCountByReceiverService mcbrService;
	@Autowired
	private MessageSearchByReceiverService msbrService;
	
	@GetMapping("/json/ex_01")
	//@ResponseBody
	public Member ex_01() {
		Member member = new Member("abc","def","TJE");
		return member;
	}

	@GetMapping("/json/ex_02")
	//@ResponseBody
	public ArrayList<Member> ex_02() {
		ArrayList<Member> list = new ArrayList<Member>();
		list.add(new Member("abc","def","TJE1"));
		list.add(new Member("bcd","efd","TJE2"));
		return list;
	}
	
	// 페이지 번호를 입력받아
	// 해당 페이지의 받은 메세지를 JSON 포멧으로 반환하는 
	// ex_03 메소드를 작성
	private List<Message> receiveForm(
			Integer page, Model model, HttpSession session) {
		Member loginMember = 
				(Member)session.getAttribute("loginMember");		
		HashMap<String, Object> args = 
				new HashMap<String, Object>();		
		
		args.put("curPageNo", page);		
		Message message = new Message();		
		message.setReceiver(loginMember.getMember_id());
		args.put("message", message);
		
		model.addAttribute("rList", msbrService.service(args));

		return (List<Message>)msbrService.service(args);
	}
		
	@GetMapping("/json/ex_03/{pageNo}")
	public List<Message> ex_03(
			@PathVariable("pageNo") Integer page,
			Model model, HttpSession session) {
		return receiveForm(page, model, session);
	}
	
}
