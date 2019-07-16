package com.tje.webapp.dao;

import com.tje.webapp.model.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RecipeDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public RecipeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	class RecipeRowMapper implements RowMapper<Recipe>{
		@Override
		public Recipe mapRow(ResultSet rs, int arg1) throws SQLException {
			Recipe recipe = new Recipe(
					rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getDate(6),rs.getDate(7), rs.getInt(8), rs.getInt(9));
			return recipe;
		}
	}
	
	public boolean insert(Recipe obj) {
		boolean result = false;
		String sql = "insert into recipe values (null,?,?,?,?,now(),now(),0,0)";
		
		result = this.jdbcTemplate.update(sql, obj.getMember_id(),obj.getCategory(),obj.getTitle(),obj.getContent()) == 0 ? false : true;

		return result;
	}
	
	
	public int last_insert_id() {
		String sql = "select last_insert_id()";
		return this.jdbcTemplate.queryForObject(sql,Integer.class);
	}
	
	public Recipe selectOne(Recipe obj) {
		String sql = "select * from recipe where recipe_id = ?";
		
		return this.jdbcTemplate.queryForObject(sql, new RecipeRowMapper(), obj.getRecipe_id());
	}
	
	public boolean plusLike_count(Recipe obj) {
		boolean result = false;
		String sql = "update Recipe set like_count = like_count + 1 where recipe_id = ?";

		result = this.jdbcTemplate.update(sql,obj.getRecipe_id()) == 0 ? false : true;

		return result;
	}
	
	public boolean minusLike_count(Connection conn, Recipe obj) {
		boolean result = false;
		String sql = "update Recipe set like_count = like_count - 1 where recipe_id = ?";
		result = this.jdbcTemplate.update(sql,obj.getRecipe_id()) == 0 ? false : true;
		return result;
	}
}
