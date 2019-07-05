package com.tje.starter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.tje.jdbc.pool.DataSourcePoolConfig;
import com.tje.jdbc.dao.*;
import com.tje.jdbc.model.*;
public class JDBCStarter {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = 
				new AnnotationConfigApplicationContext(
						DataSourcePoolConfig.class);
		
		MemberDAO dao = ctx.getBean("getMemberDAO",MemberDAO.class);
		System.out.println(dao.count());
		
		Member source = new Member();
		source.setEmail("tje304@tje.com");
		Member member = dao.selectByEmail(source);
		System.out.println(member.getName());
		
		member.setName("ABC");
		member.setPassword("DEF");
		dao.update(member);
		
		member.setEmail("keyHadscer1112222@test.ggg");
		member.setPassword("321");
		member.setName("welcome");
		System.out.println(dao.insert2(member));
		ctx.close();
		
	}

}
