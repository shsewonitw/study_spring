package com.tje.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.webapp.repository.*;
import com.tje.webapp.model.*;

@Service
public class MemberInsertService {
	@Autowired
	private MemberDAO memberDAO;
	
	public Object service(Object args) {
		return memberDAO.insert((Member)args) == 0 ? false : true;
	}
}
