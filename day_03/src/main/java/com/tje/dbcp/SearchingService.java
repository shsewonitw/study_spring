package com.tje.dbcp;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchingService {
	@Autowired
	private DAO dao;
	
	public SearchingService() {
	}
		
	public void service(HashMap<String, Object> values) {
		Connection conn = (Connection)values.get("conn");
		Member member = (Member)values.get("member");		
		values.put("result", this.dao.select(conn, member));
	}
}
