package com.tje.webapp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tje.webapp.model.Member;

@Controller
public class RegisterController {
	
	 @RequestMapping("/register/step1")
	public String handleStep1() {
		 
		 
		 return "register/step1";
	 }
	 
 	// @RequestMapping ������̼��� method�� �������� �ʤ��� ���
	 // ���ǵ� URL ��û�� �޼ҵ�� ������� �����մϴ�.
 	@RequestMapping("/register/step2")
 	// ��û �Ķ���� ������ ���� ó��
 	
 	// 1. HttpServletRequest ��ü�� ���� ó��
 	// - �޼ҵ��� �Ű������� HttpServletRequest ��ü�� �����ϸ�
 	//   Dispatcher ������ ���� ����� ��, ���� ���޹��� �� ����
 	// public String submit(HttpServletRequest request) {
 	
 	// 2. @RequestParam ������̼��� ����Ͽ� �Ķ���� ������ ������ ���� �Ҵ�(����ȯ������ �ʿ����)
	// public String submit( @RequestParam(value = "name") String name, @RequestParam(value = "age") int age, @RequestParam(value = "agree", defaultValue="false" ) boolean agree ) {
	 	
 	
 	// 3. Ŀ�ǵ� ��ü�� ����� �Ķ���� ���� ����
 	// - ��û �Ķ������ name���� �Ű������� ������ Ŭ������ ����ʵ����
 	//   ������ ��� �ش� ��ü�� �����Ͽ� ����ʵ�� ���� �����Ͽ� ��ȯ�ϴ� ���
 	// - Ŀ�ǵ� ��ü�� Model ��ü�� �߰��Ǿ� JSP�� ���� ���������� ������ �������� ����� �� �ֽ��ϴ�.
 	public String submit( Member member ) {
 	
 		System.out.printf("���̵� : %s\n",member.getId());
 		System.out.printf("�̸� : %s\n", member.getName());
 		System.out.printf("���� : %d\n", member.getAge());
 		System.out.printf("���ǿ��� : %b\n", member.isAgree());
 		
 		/*
 		System.out.printf("�̸� : %s\n", name);
 		System.out.printf("���� : %d\n", age);
 		System.out.printf("���ǿ��� : %b\n", agree);
 		*/
 		
 		/*
 		System.out.println(request.getParameter("agree"));
 		Boolean b = Boolean.valueOf(request.getParameter("agree"));
 		System.out.println(b);
 		*/
		 return "register/step2";
	 }
	
}
