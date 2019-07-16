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
public class ThumbsUpDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public ThumbsUpDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	class ThumbsUpRowMapper implements RowMapper<ThumbsUp> {
		@Override
		public ThumbsUp mapRow(ResultSet rs, int arg1) throws SQLException {
			ThumbsUp thumbsUp = new ThumbsUp(rs.getInt(1), rs.getString(2));
			return thumbsUp;
		}
	}
	
	public boolean dupleCheck(ThumbsUp obj) {
		boolean result = false;
		String sql = "select count(*) from thumbsup where recipe_id = ? and member_id = ?";
		result = this.jdbcTemplate.update(sql,obj.getRecipe_id(),obj.getMember_id()) == 0 ? false : true;
		return result;
	}
	
	public boolean insert(ThumbsUp obj) {
		boolean result = false;
		String sql = "insert into thumbsUp values (?,?)";
		result = this.jdbcTemplate.update(sql,obj.getRecipe_id(),obj.getMember_id()) == 0 ? false : true;
		return result;
	}
	
	public boolean delete(ThumbsUp obj) {
		boolean result = false;
		String sql = "delete from thumbsUp where recipe_id = ? and member_id = ?";
		result = this.jdbcTemplate.update(sql,obj.getRecipe_id(),obj.getMember_id()) == 0 ? false : true;
		return result;
	}
	
	public int selectCountWhereRecipeId(ThumbsUp obj) {
		String sql = "select count(*) from thumbsup where recipe_id = ?";
		return this.jdbcTemplate.queryForObject(sql, Integer.class, obj.getRecipe_id());
	}
	
	
	
}
