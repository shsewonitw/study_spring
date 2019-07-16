package com.tje.webapp.service;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import com.tje.webapp.dao.*;
import com.tje.webapp.model.*;

@org.springframework.stereotype.Service
public class LastInsertIdService implements Service{
	
	@Autowired
	private RecipeDAO recipeDAO;
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		result.put("result", 
				recipeDAO.last_insert_id());
	
		return result;
	}
}







