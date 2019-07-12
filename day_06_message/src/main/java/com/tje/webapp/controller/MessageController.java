package com.tje.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.tje.webapp.setting.*;

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
	@Autowired	
	private MessageCountByReceiverService mcbrService;
	@Autowired
	private MessageCountBySenderService mcbsService;
	@Autowired
	private MessageSearchByReceiverDateService msbrdService;
	@Autowired
	private MessageSearchBySenderDateService msbsdService;
	@Autowired
	private PagingInfo pagingInfo;
	@Autowired
	private MessageSearchBySenderService msbsService;
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
	
	private String receiveForm(
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
		
		HashMap<String, Integer> result = 
			(HashMap<String, Integer>)mcbrService.service(message);
		model.addAttribute(
				"r_count", result.get("totalCount"));
		
		int totalPageCount = (int)result.get("totalPageCount");
		// 시작페이지와 종료페이지 처리
		// 현재 페이지가 3인경우 한 화면에 보여줄 범위는 5
		// 시작은 1, 종료는 5
		// 시작 -> 현재페이지 / 페이지범위 + 1
		// 종료 -> 시작 + 범위 - 1
		int startPageNo =
			(page % pagingInfo.getPageRange() == 0 ? page-1 : page) 
			/ pagingInfo.getPageRange() * pagingInfo.getPageRange() + 1;
		
		int endPageNo = startPageNo + pagingInfo.getPageRange() - 1;
		if( endPageNo > totalPageCount )
			endPageNo = totalPageCount;
		
		// 이전, 다음
		// 이전을 만드는 경우 시작이 1이 아닐 때
		// 이전페이지의 값은 시작 - 페이지점위
		// 다음을 만드는 경우 종료가 마지막 페이지가 아닐 때
		// 다음페이지의 값은 다음 + 1
		int beforePageNo = startPageNo != 1 ? startPageNo - pagingInfo.getPageRange() : -1;
		int afterPageNo = endPageNo != totalPageCount ? endPageNo + 1 : -1;
		
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		model.addAttribute("beforePageNo", beforePageNo);
		model.addAttribute("afterPageNo", afterPageNo);
		model.addAttribute("curPage", page);
		return "message/receiveForm";
	}
		
	@GetMapping("/receive/{pageNo}")
	public String receiveFormWithPageNo(
			@PathVariable("pageNo") Integer page,
			Model model, HttpSession session) {
		return receiveForm(page, model, session);
	}
		
	@GetMapping("/receive")
	public String receiveFormNotPageNo(			
			Model model, HttpSession session) {
		return receiveForm(1, model, session);
	}
	
	@PostMapping("/receiveSearch")
	public String receiveSubmit(
			Model model,
			@ModelAttribute("command") MessageSearchCommand command, 
			HttpSession session) {
		HashMap<String, Object> args = new HashMap<>();
		args.put("command", command);
		String receiver = 
				((Member)session.getAttribute("loginMember")).getMember_id();
		args.put("receiver", receiver);
		
		// 서비스 호출
		List<Message> searched = 
				(List<Message>)msbrdService.service(args);
		
		model.addAttribute("searched", searched);
		model.addAttribute("searchedCount", searched == null ? 0 : searched.size());
		
		return "message/receiveSearchSubmit";
	}
	
	@PostMapping("/sendSearch")
	public String sendSubmit(
			Model model,
			@ModelAttribute("command") MessageSearchCommand command, 
			HttpSession session) {
		HashMap<String, Object> args = new HashMap<>();
		args.put("command", command);
		String receiver = 
				((Member)session.getAttribute("loginMember")).getMember_id();
		args.put("sender", receiver);
		
		// 서비스 호출
		List<Message> searched = 
				(List<Message>)msbsdService.service(args);
		
		model.addAttribute("searched", searched);
		model.addAttribute("searchedCount", searched == null ? 0 : searched.size());
		
		return "message/sendSearchSubmit";
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
		model.addAttribute("message", messagesbIDService.service(message));
		return "message/message";
		
	}
	
	private String sendForm(
			Integer page, Model model, HttpSession session) {
		Member loginMember = 
				(Member)session.getAttribute("loginMember");		
		HashMap<String, Object> args = 
				new HashMap<String, Object>();		
		
		args.put("curPageNo", page);		
		Message message = new Message();		
		message.setSender(loginMember.getMember_id());
		args.put("message", message);
		
		model.addAttribute("rList", msbsService.service(args));
		
		HashMap<String, Integer> result = 
			(HashMap<String, Integer>)mcbsService.service(message);
		model.addAttribute(
				"r_count", result.get("totalCount"));
		
		int totalPageCount = (int)result.get("totalPageCount");
		// 시작페이지와 종료페이지 처리
		// 현재 페이지가 3인경우 한 화면에 보여줄 범위는 5
		// 시작은 1, 종료는 5
		// 시작 -> 현재페이지 / 페이지범위 + 1
		// 종료 -> 시작 + 범위 - 1
		// 1 2 3 4 5 -> 1
		// 6 7 8 9 10 -> 6
		int startPageNo = 
				(page -1) / pagingInfo.getPageRange() * pagingInfo.getPageRange() +1;
		
 		int endPageNo = (startPageNo + pagingInfo.getPageRange() - 1) >= totalPageCount ? totalPageCount : startPageNo + pagingInfo.getPageRange() - 1;
		// 이전, 다음
		// 이전을 만드는 경우 시작이 1이 아닐 때
		// 이전페이지의 값은 시작 - 페이지점위
		// 다음을 만드는 경우 종료가 마지막 페이지가 아닐 때
		// 다음페이지의 값은 다음 + 1
		int beforePageNo = startPageNo != 1 ? startPageNo - pagingInfo.getPageRange() : -1;
		int afterPageNo = endPageNo != totalPageCount ? endPageNo + 1 : -1;
		
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("startPageNo", startPageNo);
		model.addAttribute("endPageNo", endPageNo);
		model.addAttribute("beforePageNo", beforePageNo);
		model.addAttribute("afterPageNo", afterPageNo);
		model.addAttribute("curPage",page);
		return "message/sendForm";
	}
		
	@GetMapping("/send/{pageNo}")
	public String sendFormWithPageNo(
			@PathVariable("pageNo") Integer page,
			Model model, HttpSession session) {
		return sendForm(page, model, session);
	}
		
	@GetMapping("/send")
	public String sendFormNotPageNo(			
			Model model, HttpSession session) {
		return sendForm(1, model, session);
	}
	
}













