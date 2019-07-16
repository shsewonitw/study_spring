package com.tje.webapp.service;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.tje.webapp.dao.*;
import com.tje.webapp.model.*;

@org.springframework.stereotype.Service
public class RecipeListService implements Service {
	@Autowired
	private SimpleRecipeDAO simpleRecipeDAO;
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		
				
		result.put("result", 
				simpleRecipeDAO.selectAll());
		
		return result;
	}
}