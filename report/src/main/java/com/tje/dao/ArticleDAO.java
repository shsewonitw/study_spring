package com.tje.dao;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.tje.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ArticleDAO {
	
	private JdbcTemplate jdbcTemplate;

	public ArticleDAO(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}
	
	public Article selectOne(Article obj) {
		return this.jdbcTemplate.queryForObject(
				"select * from article where article_id = ?", 
				Article.class,
				obj.getArticle_id());
	}
	
	public List<Article> selectAll() {
		List<Article> results =  this.jdbcTemplate.query(
				"select * from article",new RowMapper<Article>(){
					@Override
					public Article mapRow(ResultSet rs, int arg1) throws SQLException {
						Article article = new Article(
								rs.getInt(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								rs.getString(5),
								rs.getTimestamp(6),
								rs.getTimestamp(7),
								rs.getInt(8));
						return article;
					}
				});
		return results.isEmpty() ? null : results;
	}
	
	public int insert(Article obj) {
		return this.jdbcTemplate.update(
				"insert into article values (null,?,?,?,?,now(),now(),0)",
				obj.getMember_id(), obj.getName(),obj.getTitle(),obj.getContent());
	}
	
	public int delete(Article obj) {
		return this.jdbcTemplate.update(
				"delete from article where article_id = ?",
				obj.getArticle_id());
	}
		
	public int updateReadCount(Article obj) {
		return this.jdbcTemplate.update(
				"update article set read_count = read_count + 1 where article_id = ?",
				obj.getArticle_id());
	}	
	
}
