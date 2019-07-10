package com.tje.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.tje.webapp.repository.*;
import com.tje.webapp.model.*;

@Service
public class MessageSearchByReceiverService {
	@Autowired
	private MessageDAO messageDAO;
	
	public Object service(Object args) {
		HashMap<String, Object> values = (HashMap<String, Object>)args;
		int curPageNo = (Integer)values.get("curPageNo");
		Message message = (Message)values.get("message");
		return messageDAO.selectByReceiver(curPageNo, message);
	}
}
