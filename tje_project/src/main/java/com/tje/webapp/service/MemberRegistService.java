package com.tje.webapp.service;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.tje.webapp.dao.*;
import com.tje.webapp.model.*;

@org.springframework.stereotype.Service
public class MemberRegistService implements Service {
	@Autowired
	private MemberDAO memberDAO;
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		Member member = (Member)values.get("member");
				
		result.put("result", 
				memberDAO.insert(member));
		
		return result;
	}
}