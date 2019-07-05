package com.tje.dao;

import java.sql.*;
import java.util.*;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.tje.model.*;



public class CommentDAO {
	private JdbcTemplate jdbcTemplate;
	
	public CommentDAO(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}
	
	public int selectCount(Comment obj) {		
		return this.jdbcTemplate.queryForObject(
				"select count(*) from comment where article_id = ?", 
				Integer.class,
				obj.getArticle_id());		
	}
	
	public Comment selectOne(Comment obj) {
		return this.jdbcTemplate.queryForObject(
				"select * from comment where comment_id = ?", 
				new RowMapper<Comment>() {					
					public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
						Comment comment = new Comment(
								rs.getInt(1),
								rs.getInt(2),
								rs.getString(3),
								rs.getString(4),
								rs.getTimestamp(5));						
						return comment;
					}
				},
				obj.getComment_id());
	}
	
	public List<Comment> selectAll(Comment obj) {
		List<Comment> results = this.jdbcTemplate.query("select * from comment where article_id = ? order by write_time asc", new RowMapper<Comment>() {
			@Override
			public Comment mapRow(ResultSet rs, int arg1) throws SQLException {
				Comment comment = new Comment(
						rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getString(4),
						rs.getTimestamp(5));
				return comment;
			}
		},obj.getArticle_id());
		
		return results.isEmpty() ? null : results;
	}	
	
	public int insert(Comment obj) {
		return this.jdbcTemplate.update("insert into comment values (null,?,?,?,now())",obj.getArticle_id(), obj.getMember_id(),obj.getContent());
	}
		
	public int delete(Comment obj) {
		return this.jdbcTemplate.update("delete from comment where comment_id = ?",obj.getComment_id());
	}
	
}













