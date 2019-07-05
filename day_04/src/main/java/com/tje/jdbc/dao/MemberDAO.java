package com.tje.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.tje.jdbc.model.*;

public class MemberDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public MemberDAO(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}
	
	/*
	public int count() {
		List<Integer> result = jdbcTemplate.query(
				"select count(*) from member",
				new RowMapper<Integer>() {
					@Override
					public Integer mapRow(ResultSet arg0, int arg1) throws SQLException {
						Integer count = arg0.getInt(1);
						return count;
					}
				});
		return result.get(0);
	}
	*/
	
	public int count() {
		return this.jdbcTemplate.queryForObject("select count(*) from member", Integer.class);
	}
	
	public Member selectByEmail(Member model) {
		// JDBC를 사용해서 쿼리를 수행하는 과정
		// 1. 커넥션 객체 반환
		// 2. 커넥션 객체를 사용하여 쿼리를 수행할 수 있는 클래스의 객체 생성
		//   - Statement, PreparedStatement ...
		// 3. 쿼리 수행
		// 4. 쿼리의 수행 결과를 ResultSet 타입의 객체로 반환
		// 5. 반복문을 수행하며 ResultSet 내부를 순회하며 
		//    모델클래스의 객체로 생성하여 리스트를 생성
		
		// jdbcTemplate 클래스의 query 메소드
		// 1번째 매개변수는 수행할 sql문을 작성
		//  - 파라미터가 필요한 부분에 ? 를 사용하여 작성
		// 2번째 매개변수는 해당 쿼리를 수행한 후, ResultSet 내부를 순회하며 
		// 처리할 RowMapper 객체를 전달
		// while(rs.next())
		// 		generateObject(rs);  <-- RowMapper 객체가 수행할 작업
		// 3번째 이후의 매개변수는 ? 자리를 대체할 변수
		List<Member> results = jdbcTemplate.query(
				"select * from member where email = ?",
				new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member member = new Member(
								rs.getInt(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								rs.getTimestamp(5));
						return member;
					}
				},model.getEmail());
		return results.isEmpty() ? null : results.get(0);
	}
	
	// queryForObject를 사용
	public Member selectByEmail2(Member model) {
		Member result = jdbcTemplate.queryForObject("select * from member where email = ?", 
				new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member member = new Member(
								rs.getInt(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								rs.getTimestamp(5));
						return member;
					}
				},model.getEmail());
		return result;
	}
	
	public int insert(Member model) {
		int result = this.jdbcTemplate.update("insert into member values ( null, ? , ?, ? , now() )",
				model.getEmail(),model.getPassword(),model.getName());
		return result;
	}
	
	public int insert2(Member model) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				PreparedStatement pstmt = arg0.prepareStatement("insert into member values (null, ? , ? , ? , now())", new String[] {"id"});
				pstmt.setString(1, model.getEmail());
				pstmt.setString(2, model.getPassword());
				pstmt.setString(3, model.getName());
				return pstmt;
			}
		},keyHolder);
		return keyHolder.getKey().intValue();
		
	}
	
	public int update(Member model) {
		int result = this.jdbcTemplate.update("update member set name = ? , password = ? where email = ?",
				model.getName(),model.getPassword(),model.getEmail());
		return result;
	}
	
	public List<Member> selectAll(){
		List<Member> results = jdbcTemplate.query(
				"select * from member",
				new MemberRowMapper());
		return results.isEmpty() ? null : results;
	}
}
