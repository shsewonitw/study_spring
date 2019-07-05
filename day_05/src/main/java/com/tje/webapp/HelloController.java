package com.tje.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.DispatcherServlet;

import com.tje.webapp.model.Member;

// 스프링 컨트롤러 생성하는 방법
// 1. @Controller 어노테이션을 클래스 정의부에 선언
// - @Controller 어노테이션은 스프링 컨트롤러 클래스를 선언할 때 사용
// - 웹 서버의 로딩 시, servlet-context.xml 파일에 정의된
//   component-scan 태그에 의해서 자동으로 스프링 빈 객체로 생성되는 클래스
@Controller
public class HelloController {
	@Autowired
	private Member member;
	
	// 2. @RequestMapping 이 적용된 메소드의 선언
	// - @RequestMapping 어노테이션은 클라이언트의 웹 요청을 처리하기 위한 메소드의 선언부에 작성
	// - @RequestMapping 어노테이션에는 클라이언트의 요청 메소드, 요청 URL 정보를 기술합니다.
	// - @RequestMapping 어노테이션이 적용된 메소드는 일반적(반드시는 아님)으로 String 타입을 리턴하며, 
	//   String 타입은 포워딩 될 페이지의 정보를 의미합니다.
	// - 리턴되는 문자열은 servlet-context.xml 파일에 정의된 InternalResourceViewResolver 클래스의 빈의 
	//   prefix, suffix 의 정보와 자동으로 문자열 결합됩니다.
	//   만약 hello 라는 문자열을 리턴하는 경우 prefix 정보가 앞쪽에 결합되고 suffix 정보가 뒤쪽에 결합
	//   "hello"  ->  "/WEB-INF/views/hello.jsp"
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@RequestMapping("/hello1")
	public String hello1(Model model,
			@RequestParam(value="name",required=false) String name) {
		model.addAttribute("greeting","안녕하세요ㅕ," + name);
		model.addAttribute("member",member);
		return "hello1";
	}
	
}
