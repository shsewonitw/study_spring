package com.tje.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.webapp.repository.*;
import com.tje.webapp.model.*;

import java.util.*;

@Service
public class MemberLoginService {
	@Autowired
	private MemberDAO memberDAO;
	
	public Object service(Object args) {
		ArrayList<Member> input = (ArrayList<Member>)args;
		// 사용자가 요청객체에 전달한 정보 
		Member source = input.get(0);
		// 사용자가 요청객체에 전달한 ID에 해당되는 데이터베이스 정보
		Member info = input.get(1);
		
		return source.getPassword().equals(info.getPassword());
	}
}
