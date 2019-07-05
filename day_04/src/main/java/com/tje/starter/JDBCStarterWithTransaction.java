package com.tje.starter;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.tje.jdbc.pool.*;
import com.tje.services.TransactionTestService;
import com.tje.jdbc.dao.*;
import com.tje.jdbc.model.*;

public class JDBCStarterWithTransaction {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = 
				new AnnotationConfigApplicationContext(
						DataSourcePoolConfig.class);
		
		TransactionTestService ttService = 
				ctx.getBean("ttService", TransactionTestService.class);
		
		ttService.service();
		
		ctx.close();
	}

}









