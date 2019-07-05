package com.tje.dbcp;

import java.sql.*;
import java.util.*;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistService {	
	
	@Resource(name = "memberDAO")
	private DAO dao;
	
	public RegistService() {
	}	
	
	public void service(HashMap<String, Object> values) {
		Connection conn = (Connection)values.get("conn");
		Member member = (Member)values.get("member");		
		values.put("result", this.dao.insert(conn, member));
	}

}






