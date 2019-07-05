package com.tje.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.DispatcherServlet;

import com.tje.webapp.model.Member;

// ������ ��Ʈ�ѷ� �����ϴ� ���
// 1. @Controller ������̼��� Ŭ���� ���Ǻο� ����
// - @Controller ������̼��� ������ ��Ʈ�ѷ� Ŭ������ ������ �� ���
// - �� ������ �ε� ��, servlet-context.xml ���Ͽ� ���ǵ�
//   component-scan �±׿� ���ؼ� �ڵ����� ������ �� ��ü�� �����Ǵ� Ŭ����
@Controller
public class HelloController {
	@Autowired
	private Member member;
	
	// 2. @RequestMapping �� ����� �޼ҵ��� ����
	// - @RequestMapping ������̼��� Ŭ���̾�Ʈ�� �� ��û�� ó���ϱ� ���� �޼ҵ��� ����ο� �ۼ�
	// - @RequestMapping ������̼ǿ��� Ŭ���̾�Ʈ�� ��û �޼ҵ�, ��û URL ������ ����մϴ�.
	// - @RequestMapping ������̼��� ����� �޼ҵ�� �Ϲ���(�ݵ�ô� �ƴ�)���� String Ÿ���� �����ϸ�, 
	//   String Ÿ���� ������ �� �������� ������ �ǹ��մϴ�.
	// - ���ϵǴ� ���ڿ��� servlet-context.xml ���Ͽ� ���ǵ� InternalResourceViewResolver Ŭ������ ���� 
	//   prefix, suffix �� ������ �ڵ����� ���ڿ� ���յ˴ϴ�.
	//   ���� hello ��� ���ڿ��� �����ϴ� ��� prefix ������ ���ʿ� ���յǰ� suffix ������ ���ʿ� ����
	//   "hello"  ->  "/WEB-INF/views/hello.jsp"
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@RequestMapping("/hello1")
	public String hello1(Model model,
			@RequestParam(value="name",required=false) String name) {
		model.addAttribute("greeting","�ȳ��ϼ����," + name);
		model.addAttribute("member",member);
		return "hello1";
	}
	
}
