package com.tje.jdbc.pool;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.tje.jdbc.dao.MemberDAO;
import com.tje.services.TransactionTestService;

@Configuration
// 현재 자바 클래스에서 생성되는 DataSource를 통해서
// 제공되는 커넥션 객체들에 대해서 트랜잭션을 처리할 수 있도록
// 설정하는 어노테이션
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
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		/*
		   XML 설정에서 사용한 다음과 같은 라인을 클래스에서 지정하는 메소드
		   <context:property-placeHolder location="classpath:/jdbc.properties"/>
		 */
		PropertySourcesPlaceholderConfigurer config = 
				new PropertySourcesPlaceholderConfigurer();
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
	public TransactionTestService ttService() {
		return new TransactionTestService(getMemberDAO());
	}
}
