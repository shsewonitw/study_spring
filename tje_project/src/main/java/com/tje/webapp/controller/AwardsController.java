package com.tje.webapp.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tje.webapp.dao.*;
import com.tje.webapp.model.*;

@Controller
public class AwardsController {
	@Autowired
	private SimpleRecipeDAO simpleRecipeDAO;
	
	@GetMapping("/awards.do")
	public String awardsForm(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member login_member = (Member)session.getAttribute("login_member");
		if(login_member == null) 
			return "error";
		
			List<SimpleRecipe> bestRecipeList_beginner = simpleRecipeDAO.selectBeginnerBest();
			List<SimpleRecipe> bestRecipeList_intermediate = simpleRecipeDAO.selectIntermediateBest();
			List<SimpleRecipe> bestRecipeList_advanced = simpleRecipeDAO.selectAdvancedBest();
			
			request.setAttribute("bestRecipeList_beginner", bestRecipeList_beginner);
			request.setAttribute("bestRecipeList_intermediate", bestRecipeList_intermediate);
			request.setAttribute("bestRecipeList_advanced", bestRecipeList_advanced);
			System.out.println(11111);

		return "forms/awards";
	}
	
}
