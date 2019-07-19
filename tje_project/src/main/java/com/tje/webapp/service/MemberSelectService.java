package com.tje.webapp.service;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.webapp.dao.*;
import com.tje.webapp.model.*;

@Service
public class MemberSelectService {

	@Autowired(required = true)
	private MemberDAO memberDAO;
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		Member member = (Member)values.get("member");
		System.out.println("memberDAO in service : "+memberDAO);
		if( member.getMember_id() != null ) {
			result.put("result", 
					memberDAO.selectOneWhereId(member));
		} else if ( member.getNickname() != null ) {
			result.put("result", 
					memberDAO.selectOneWhereNickName(member));
		} else if ( member.getEmail() != null ) {
			result.put("result", 
					memberDAO.selectOneWhereEmail(member));
		}
	
		
		return result;
	}
}
