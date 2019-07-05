package com.tje.starter;

import com.tje.config.*;
import com.tje.model.*;

import org.springframework.context.support.AbstractApplicationContext;
// AnnotationConfigApplicationContext 클래스
// - 클래스 파일을 사용하여 스프링 컨테이너를 정의한 경우
//   해당 클래스 파일을 사용하여 스프링 컨테이너를 생성할 수 있는 클래스
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SingletonMain {
	public static void main(String[] args) {
		AbstractApplicationContext ctx = 
				new AnnotationConfigApplicationContext(PersonConfig.class);
		
		
		// 스프링 컨테이너는 싱글턴 패턴으로 스프링 빈 객체를 관리합니다.
		
		// 스프링 컨테이너로부터 동일한 빈 객체를 반환받는 예제
		// - 메소드를 호출하여 반환된 결과를 받는 것과 같아보이지만
		//   실제로는 단 하나의 Dog 클래스의 객체가 생성되어 
		//   d1, d2는 모두 동일한 Dog 클래스의 객체를 참조하고 있는 상태
		Dog d1 = ctx.getBean("getDog",Dog.class);
		Dog d2 = ctx.getBean("getDog",Dog.class);
		
		System.out.println(d1);
		System.out.println(d2);
		
		if(d1 == d2)
			System.out.println("동일한 객체 입니다.");
		if(d1.equals(d2))
			System.out.println("동일한 객체 입니다.");
		
	}
}
