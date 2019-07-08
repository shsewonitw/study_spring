package com.tje.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.webapp.repository.*;
import com.tje.webapp.model.*;

@Service
public class MessageSearchByIdService {
	@Autowired
	private MessageDAO messageDAO;
	
	public Object service(Object args) {
		return messageDAO.selectById((Message)args);
	}
}
