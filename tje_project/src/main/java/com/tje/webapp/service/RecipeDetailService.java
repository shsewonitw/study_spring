package com.tje.webapp.service;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.webapp.dao.*;
import com.tje.webapp.model.*;

@Service
public class RecipeDetailService {
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