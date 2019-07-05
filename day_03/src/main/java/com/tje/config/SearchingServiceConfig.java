package com.tje.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tje.services.*;

// services.xml 파일의 스프링 컨테이너 설정을
// 아래의 ServicesConfig를 사용하여 정의한 후,
// MainWithAnnotation 클래스를 사용하여 실행하세요.

@Configuration
public class SearchingServiceConfig {

	@Resource(name = "memberDAO")
	private MemberDAO memberDAO;
		
	@Bean(name = "ss")
	public SearchingService getSearchingService() {		
		return new SearchingService();
	}
	
}

















