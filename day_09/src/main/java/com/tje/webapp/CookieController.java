package com.tje.webapp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tje.webapp.model.Member;

@Controller
@RequestMapping("/login")
public class CookieController {
	
	@GetMapping()
	public String form(			
			@ModelAttribute("member") Member member,
			@CookieValue(value="rememberID", required = false) Cookie rememberIDCookie) {		
		if( rememberIDCookie != null )
			member.setRememberID(true);
		
		return "loginForm";
	}
	
	@PostMapping()
	public String submit(			
			@ModelAttribute("member") Member member,
			HttpServletResponse response) {
		
		Cookie cookie = new Cookie("rememberID", member.getId());
		if( !member.isRememberID() )
			cookie.setMaxAge(0);
		
		response.addCookie(cookie);
		return "loginSubmit";
	}

}












