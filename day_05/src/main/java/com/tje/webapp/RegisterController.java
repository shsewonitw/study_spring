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
	 
 	// @RequestMapping 어노테이션은 method가 지정되지 않ㄴ은 경우
	 // 정의된 URL 요청을 메소드와 관계없이 실행합니다.
 	@RequestMapping("/register/step2")
 	// 요청 파라미터 추출을 위한 처리
 	
 	// 1. HttpServletRequest 객체를 통해 처리
 	// - 메소드의 매개변수로 HttpServletRequest 객체를 선언하면
 	//   Dispatcher 서블릿을 통해 실행될 때, 값을 전달받을 수 있음
 	// public String submit(HttpServletRequest request) {
 	
 	// 2. @RequestParam 어노테이션을 사용하여 파라미터 정보를 변수에 직접 할당(형변환과정이 필요없음)
	// public String submit( @RequestParam(value = "name") String name, @RequestParam(value = "age") int age, @RequestParam(value = "agree", defaultValue="false" ) boolean agree ) {
	 	
 	
 	// 3. 커맨드 객체를 사용한 파라미터 정보 추출
 	// - 요청 파라미터의 name값과 매개변수로 지정된 클래스의 멤버필드명이
 	//   동일한 경우 해당 객체를 생성하여 멤버필드로 값을 설정하여 반환하는 기능
 	// - 커맨드 객체는 Model 객체로 추가되어 JSP와 같은 페이지에서 별도의 설정없이 사용할 수 있습니다.
 	public String submit( Member member ) {
 	
 		System.out.printf("아이디 : %s\n",member.getId());
 		System.out.printf("이름 : %s\n", member.getName());
 		System.out.printf("나이 : %d\n", member.getAge());
 		System.out.printf("동의여부 : %b\n", member.isAgree());
 		
 		/*
 		System.out.printf("이름 : %s\n", name);
 		System.out.printf("나이 : %d\n", age);
 		System.out.printf("동의여부 : %b\n", agree);
 		*/
 		
 		/*
 		System.out.println(request.getParameter("agree"));
 		Boolean b = Boolean.valueOf(request.getParameter("agree"));
 		System.out.println(b);
 		*/
		 return "register/step2";
	 }
	
}
