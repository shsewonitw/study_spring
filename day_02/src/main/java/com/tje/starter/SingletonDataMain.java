package com.tje.starter;

import com.tje.config.*;
import com.tje.model.*;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
// AnnotationConfigApplicationContext 클래스
// - 클래스 파일을 사용하여 스프링 컨테이너를 정의한 경우
//   해당 클래스 파일을 사용하여 스프링 컨테이너를 생성할 수 있는 클래스

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SingletonDataMain {
	public static void main(String[] args) {
		String configLocation = "classpath:conf/singleton.xml";
		AbstractApplicationContext ctx = 
				new GenericXmlApplicationContext(configLocation);
		
		SingletonData s1 = ctx.getBean("s1",SingletonData.class);
//		SingletonData s2 = ctx.getBean("s2",SingletonData.class);
		SingletonData s3 = ctx.getBean("s3",SingletonData.class);
		System.out.println(s1.getNumber());
//		System.out.println(s2.getNumber());
		System.out.println(s3.getNumber());
	}
}
