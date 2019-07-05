package com.tje.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tje.services.*;

// services.xml 파일의 스프링 컨테이너 설정을
// 아래의 ServicesConfig를 사용하여 정의한 후,
// MainWithAnnotation 클래스를 사용하여 실행하세요.

@Configuration
public class MemberDAOConfig {
	
	@Bean(name = "memberDAO")
	public MemberDAO getMemberDAO() {
		return new MemberDAO();
	}	
	
}

















