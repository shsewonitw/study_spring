package com.tje.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.tje.model.*;

public class MemberDAO {

	private JdbcTemplate jdbcTemplate;

	public MemberDAO(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}
	
	
	
	public Member selectOne(Member obj) {
		return this.jdbcTemplate.queryForObject("select * from member where member_id = ?", Member.class,obj.getMember_id());
	}
	
	
	public List<Member> selectAll() {
		List<Member> result = this.jdbcTemplate.query("select * from member",new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int arg1) throws SQLException {
				Member member = new Member(
						rs.getString("member_id"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("tel"),
						rs.getString("address"),
						rs.getTimestamp("last_access_time"));
				return member;
			}
		});
		
		
		return result;
	}
	

	
	public int insert(Member obj) {
		return this.jdbcTemplate.update(
				"insert into member values (?,?,?,?,?,null)",
				obj.getMember_id());
	}
		
	public int updateLastAT(Member obj) {
		return this.jdbcTemplate.update(
				"update member set last_access_time = now() where member_id = ?",
				obj.getMember_id());
	}
	
	public int updatePassword(Member obj) {
		String sql = "update member set password = ? where member_id = ?";
		return this.jdbcTemplate.update(
				"update member set password = ? where member_id = ?",
				obj.getPassword(),obj.getMember_id());
	}
}
