package com.tje.webapp.dao;

import com.tje.webapp.model.*;
import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public CommentDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	
	
	public int selectCount(SimpleRecipe obj) {
		String sql = "select count(*) from comment where recipe_id = ?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class , obj.getRecipe_id());
	}
	
	public int last_insert_id() {
		String sql = "select last_insert_id()";
		return this.jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public List<Comment> selectWhereRecipeId(Comment obj){
		String sql = "select * from comment where recipe_id = ? order by write_time desc";
		List<Comment> commentList = this.jdbcTemplate.query(sql, new RowMapper<Comment>() {
			@Override
			public Comment mapRow(ResultSet rs, int arg1) throws SQLException {
				Comment comment = new Comment(
						rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getDate(5));
				return comment;
			}
		}, obj.getRecipe_id());
		
		return commentList.isEmpty() ? null : commentList;
	}
	
	
	public boolean insert(Comment obj) {
		boolean result = false;
		String sql = "insert into Comment values (null,?,?,?,now())";
		result = this.jdbcTemplate.update(sql, obj.getRecipe_id(),obj.getMember_id(), obj.getContent()) == 0 ? false : true;
		return result;
	}
	
	
	public boolean delete(Connection conn, Comment obj) {
		boolean result = false;
		String sql = "delete from comment where comment_id = ? and member_id = ?";

		result = this.jdbcTemplate.update(sql,obj.getComment_id(),obj.getMember_id()) == 0 ? false : true;
		
		return result;
	}
	

}
