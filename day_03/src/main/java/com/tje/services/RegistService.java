package com.tje.services;

import java.sql.*;
import java.util.*;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

public class RegistService {
	
	// jsr250-api 에 관련된 dependency 를 추가해야만 
	// 사용할 수 있는 어노테이션
	@Resource(name = "memberDAO")
	private DAO dao;
	
	public RegistService() {
	}
	
	//@Autowired	
	public RegistService(DAO dao) {
		this.dao = dao;
	}
	
	public void service(HashMap<String, Object> values) {
		Connection conn = (Connection)values.get("conn");
		Member member = (Member)values.get("member");		
		values.put("result", this.dao.insert(conn, member));
	}

}






