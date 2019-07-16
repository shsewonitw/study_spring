package com.tje.webapp.service;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.tje.webapp.dao.*;
import com.tje.webapp.model.*;

@org.springframework.stereotype.Service
public class RecipeDetailService implements Service {
	@Autowired
	private RecipeDAO recipeDAO;
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		Recipe model = (Recipe)values.get("model");
				
	
		result.put("result", 
				recipeDAO.selectOne(model));
		
		return result;
	}
}