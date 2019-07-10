package com.tje.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.tje.webapp.model.*;
import com.tje.webapp.repository.*;
import com.tje.webapp.setting.*;

@Service
public class MessageCountBySenderService {
	
	@Autowired
	private MessageDAO messageDAO;
	@Autowired
	private PagingInfo pagingInfo;
	
	public Object service(Object args) {	
		int totalCount = 
				messageDAO.selectBySenderCount((Message)args);
		int totalPageCount = 
				totalCount / pagingInfo.getPagingSize() + 
				(totalCount % pagingInfo.getPagingSize() == 0 ? 0 : 1);
		
		HashMap<String, Integer> result = 
				new HashMap<String, Integer>();
		result.put("totalCount", totalCount);
		result.put("totalPageCount", totalPageCount);
		
		return result;
	}
	
}














