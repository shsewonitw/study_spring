package com.tje.webapp.dao;

import com.tje.webapp.model.*;
import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public MemberDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Member selectOneWhereId(Member obj) {
		
		String sql = "select * from member where member_id = ?";
		
		return this.jdbcTemplate.query(sql, new ResultSetExtractor<Member>() {
			@Override
			public Member extractData(ResultSet rs) throws SQLException, DataAccessException {
				Member member = null;
				if(rs.next()) {
					member = new Member(
							rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getDate(6));
				}
				return member;
			}
		}, obj.getMember_id());
		
	}
	
	public Member selectOneWhereNickName(Member obj) {
		String sql = "select * from member where nickname = ?";
		return this.jdbcTemplate.query(sql,new ResultSetExtractor<Member>() {
			@Override
			public Member extractData(ResultSet rs) throws SQLException, DataAccessException {
				Member member = null;
				if(rs.next()) {
					member = new Member(
							rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getDate(6));
				}
				return member;
			}
		},obj.getNickname());
	}
	
	public Member selectOneWhereEmail(Member obj) {
		String sql = "select * from member where email = ?";
		return this.jdbcTemplate.query(sql, new ResultSetExtractor<Member>() {
			@Override
			public Member extractData(ResultSet rs) throws SQLException, DataAccessException {
				Member member = null;
				if(rs.next()) {
					member = new Member(
							rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getDate(6));
				}
				return member;
			}
		},obj.getEmail());
		
	}
	
	public List<Member> selectAll() {
		String sql = "select * from member";
		List<Member> result = this.jdbcTemplate.query(sql, new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int arg1) throws SQLException {
				Member member = new Member(
						rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getDate(6));
				return member;
			}
		});
		return result.isEmpty() ? null : result;
	}
	
	public boolean insert(Member obj) {
		boolean result = false;
		String sql = "insert into member values (?,?,?,?,now(),null)";
		
		result = this.jdbcTemplate.update(sql, obj.getMember_id(),obj.getPassword(),obj.getNickname(),obj.getEmail()) == 0 ? false : true;
		
		return result;
	}
	
	
	public boolean update(Member obj) {
		boolean result = false;
		String sql = "update member set password = ? , nickname = ? , email = ? where member_id = ?";
		
		result = this.jdbcTemplate.update(sql, obj.getPassword(),obj.getNickname(),obj.getEmail(),obj.getMember_id()) == 0 ? false : true;
		
		return result;
	}
	
}
