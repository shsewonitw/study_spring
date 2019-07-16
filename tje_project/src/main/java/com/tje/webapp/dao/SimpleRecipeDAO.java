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
public class SimpleRecipeDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public SimpleRecipeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	class SimpleRecipeRowMapper implements RowMapper<SimpleRecipe>{
		@Override
		public SimpleRecipe mapRow(ResultSet rs, int arg1) throws SQLException {
			SimpleRecipe simpleRecipe = new SimpleRecipe(
					rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getTimestamp(6),rs.getInt(7),rs.getInt(8));
			return simpleRecipe;
		}
	}
	
	// beginner 카테고리에서 좋아요 많이 받은 게시물들
	public List<SimpleRecipe> selectBeginnerBest() {
		String sql = "select * from simpleRecipe where category = 'beginner' and like_count = (select max(like_count) from simpleRecipe where category = 'beginner')";
		List<SimpleRecipe> result = this.jdbcTemplate.query(sql,new SimpleRecipeRowMapper());
		return result.isEmpty() ? null : result;
	}
	
	// advanced 카테고리에서 좋아요 많이 받은 게시물들
	public List<SimpleRecipe> selectAdvancedBest() {
		String sql = "select * from simpleRecipe where category = 'advanced' and like_count = (select max(like_count) from simpleRecipe where category = 'advanced')";
		List<SimpleRecipe> result = this.jdbcTemplate.query(sql,new SimpleRecipeRowMapper());
		return result.isEmpty() ? null : result;
	}
	
	// intermediate 카테고리에서 좋아요 많이 받은 게시물들
	public List<SimpleRecipe> selectIntermediateBest() {
		String sql = "select * from simpleRecipe where category = 'intermediate' and like_count = (select max(like_count) from simpleRecipe where category = 'intermediate')";
		List<SimpleRecipe> result = this.jdbcTemplate.query(sql,new SimpleRecipeRowMapper());
		return result.isEmpty() ? null : result;
	}
	
	// 내가 좋아요한 게시물 쿼리
	public List<SimpleRecipe> selectLikeList(SimpleRecipe model) {
		String sql = "select * from simpleRecipe where recipe_id in (select recipe_id from thumbsup where member_id = ?) order by write_time desc";
		List<SimpleRecipe> result = this.jdbcTemplate.query(sql,new SimpleRecipeRowMapper(),model.getMember_id());
		
		return result.isEmpty() ? null : result;
	}
	
	public SimpleRecipe selectOne(Recipe model) {
		String sql = "select * from simplerecipe where recipe_id = ?";
		return this.jdbcTemplate.queryForObject(sql,new SimpleRecipeRowMapper(),model.getRecipe_id());
	}
	
	
	public List<SimpleRecipe> selectAll() {
		String sql = "select * from simpleRecipe order by write_time desc";
		List<SimpleRecipe> result = this.jdbcTemplate.query(sql, new SimpleRecipeRowMapper());
		return result.isEmpty() ? null : result;
	}
	
	
	public List<SimpleRecipe> selectFromMember_id(SimpleRecipe obj) {
		String sql = "select * from simpleRecipe where member_id = ? order by write_time desc";
		List<SimpleRecipe> result = this.jdbcTemplate.query(sql, new SimpleRecipeRowMapper(),obj.getMember_id());
		return result.isEmpty() ? null : result;
	}
	
	public List<SimpleRecipe> selectFromCategory(SimpleRecipe obj) {
		String sql = "select * from simpleRecipe where category = ? order by write_time desc";
		List<SimpleRecipe> result = this.jdbcTemplate.query(sql, new SimpleRecipeRowMapper(),obj.getCategory());		
		return result.isEmpty() ? null : result;
	}
	
	public boolean plusRead_count(Recipe model) {
		Boolean result = false;
		String sql = "update recipe set read_count = read_count + 1 where recipe_id = ?";
		result = this.jdbcTemplate.update(sql,model.getRecipe_id()) == 0 ? false : true;
		return result;
	}
	
}
