package com.tje.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class PersonWithAnnotation {
	private String name;
	// Pet 타입의 객체를 스프링 컨테이너를 통해서 주입받음
	// - 일반적으로 생성자, 프로퍼티를 사용하여 값을 주입받음
	// 반면, 명시적인(생성자, Setter메소드)의 사용없이 
	// DI를 구현하기 위해서 제공되는 어노테이션 기반의 방법
	// - @Autowired 어노테이션
	// - 현재 스프링 컨테이너 내부에 @Autowired가 적용된 멤버필드와 동일한 타입의 객체가 존재한다면 자동으로 DI가 구현되도록 만듬
	

	// - Autowired 를 적용하여 자동으로 DI를 구현하는 경우
	//   해당 타입의 객체는 스프링 컨테이너에 단 하나만 존재해야합니다.
	//   (스프링 컨테이너 생성 시, 런타임 에러발생)
	@Autowired
	// - @Autowired와 같이 사용되며, 만약 스프링 컨테이너 내부에 
	//   @Autowired로 지정된 타입의 객체가 다수개 존재하는 경우 
	//   특정 객체만을 자동으로 DI할 수 있도록 제어하는 어노테이션
	@Qualifier("happy")
	private Pet pet;

	
	public PersonWithAnnotation() {
	}

	public PersonWithAnnotation(String name, Pet pet) {
		this.name = name;
		this.pet = pet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}
	
	
}
