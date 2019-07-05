package com.tje.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MappingController {
	// Ŭ���̾�Ʈ�� ��û�� ó���ϱ� ���� �޼ҵ��� ����
	// - ��û URL�� ����
	// - @RequestMapping : ��û �޼ҵ忡 ���� ���� ��û�� ó���� �� �ִ� �޼ҵ��� ����ο� �����ϴ� ������̼�
	// - @GetMapping : GET ����� ��û�� ó���� �� �ִ� �޼ҵ��� ����ο� �����ϴ� ������̼�
	// - @PostMapping : POST ����� ��û�� ó���� �� �ִ� �޼ҵ��� ����ο� �����ϴ� ������̼� 
	

	// /request_1 �� ��û�� GET ������� ���޵Ǵ� ��� ����� �޼ҵ带 �����ϴ� @RequestMapping ������̼�
	@RequestMapping(method = RequestMethod.GET , value = "/request_1")
	public void request_1() {
		System.out.println("request_1 ����");
	}
	
	// /request_2 �� ��û�� GET ������� ���޵Ǵ� ��� ����� �޼ҵ带 �����ϴ� @GetMapping ������̼�
	@GetMapping(value = "/request_2")
	public void request_2() {
		System.out.println("request_2 ����");
	}
	
	// /request_3 �� ��û�� POST ������� ���޵Ǵ� ��� ����� �޼ҵ带 �����ϴ� @PostMapping ������̼�
	@PostMapping(value = "/request_3")	
	public void request_3() {
		System.out.println("request_3 ����");
	}
}
