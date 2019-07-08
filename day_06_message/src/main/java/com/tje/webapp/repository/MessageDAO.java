package com.tje.webapp.repository;

import java.sql.*;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.tje.webapp.model.*;

@Repository
public class MessageDAO {
	private JdbcTemplate jdbcTemplate;
	
	// 생성자 호출에 필요한 DataSource 타입의 객체를
	// 스프링 컨테이너에서 검색하여 자동으로 DI 처리
	@Autowired
	public MessageDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	class MessageRowMapper implements RowMapper<Message>{
		 @Override
		public Message mapRow(ResultSet rs, int arg1) throws SQLException {
			 Message message = new Message(
						rs.getInt(1),	// message_id
						rs.getString(2),	// sender
						rs.getString(3),	// receiver
						rs.getString(4),	// content
						rs.getTimestamp(5),	// send_time
						rs.getTimestamp(6));	// receive_time
				return message;
		}
	}
	
public Message selectById(Message model) {
		
		return this.jdbcTemplate.queryForObject(
				"select * from message where message_id = ?",
				new MessageRowMapper(), 
				model.getMessage_id());
	}
	
public List<Message> selectBySender(Message model) {
	List<Message> result = this.jdbcTemplate.query(
			"select * from message where sender = ?",
			new MessageRowMapper(),model.getSender() );
	
	return result.isEmpty() ? null : result;
}

public List<Message> selectByReceiver(Message model) {
	List<Message> result = this.jdbcTemplate.query(
			"select * from message where receiver = ?",
			new MessageRowMapper(),model.getReceiver() );
	
	return result.isEmpty() ? null : result;
}

	public int insert(Message model) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.jdbcTemplate.update(new PreparedStatementCreator() {			
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = 
					con.prepareStatement(
						"insert into member values (null, ?, ?, ?,now(), null)", 
						 new String[]{"message_id"});
				pstmt.setString(1, model.getSender());
				pstmt.setString(2, model.getReceiver());
				pstmt.setString(3, model.getContent());
				return pstmt;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();		
		
	}
	
	
	public int updateReceiveTime(Message model) {
		return this.jdbcTemplate.update(
				"update message set receive_time = now() where message_id = ?", 
				model.getMessage_id());
	}
}
