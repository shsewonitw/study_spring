package com.tje.webapp.controller;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tje.webapp.dao.*;
import com.tje.webapp.model.*;

@Controller
public class CommentController {
	@Autowired
	private CommentDAO commentDAO;
	
	@GetMapping("/auth/comment_delete.do")
	public String commentDeleteForm(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member login_member = (Member)session.getAttribute("login_member");
		if(login_member == null)
			return "error";
		
		String strDeleteCommentId = request.getParameter("deleteCommentId");
		if(strDeleteCommentId.length() < 1)
			return "error";
		int deleteCommentId = Integer.parseInt(strDeleteCommentId);
		
			Comment comment = new Comment();
			comment.setComment_id(deleteCommentId);
			comment.setMember_id(login_member.getMember_id());
			boolean flag = commentDAO.delete(comment);
			
			if(!flag) {
//				response.setContentType("text/plane;charset=utf-8");
//				PrintWriter out = response.getWriter();
//				out.println(false);
//				out.flush();
//				out.close();
//				return null;
				return "false";
			}
			
//			response.setContentType("text/plane;charset=utf-8");
//			PrintWriter out = response.getWriter();
//			out.println(deleteCommentId);
//			out.flush();
//			out.close();
			return String.valueOf(deleteCommentId);
		
		
		
	}
}
