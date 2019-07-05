package com.tje.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.tje.jdbc.model.Member;

public class MemberRowMapper implements RowMapper<Member> {
	

	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = new Member(
				rs.getInt(1), 
				rs.getString(2), 
				rs.getString(3), 
				rs.getString(4), 
				rs.getTimestamp(5));
		return member;
	}
			
}
