package com.tje.pool;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import com.tje.dao.*;


@Configuration
public class DataSourcePoolConfig {
	
	@Value("${JDBC_DRIVER}")
	private String driver;
	@Value("${JDBC_URL}")
	private String url;
	@Value("${JDBC_USER}")
	private String user;
	@Value("${JDBC_PASSWORD}")
	private String password;
	@Value("${INIT_SIZE}")
	private int init_size;
	@Value("${MAX_ACTIVE}")
	private int max_active;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer config = new PropertySourcesPlaceholderConfigurer();
		config.setLocation(new ClassPathResource("jdbc.properties"));
		return config;
	}
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(password);
		ds.setInitialSize(init_size);
		ds.setMaxActive(max_active);
		return ds;
	}
	
	@Bean
	public MemberDAO getMemberDAO() {
		return new MemberDAO(dataSource());
	}
	
	@Bean
	public CommentDAO getCommentDAO() {
		return new CommentDAO(dataSource());
	}
	
	@Bean
	public ArticleDAO getArticleDAO() {
		return new ArticleDAO(dataSource());
	}
}
