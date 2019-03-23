package com.icss.meeting.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.icss.meeting.dao.EmployeeDao;
import com.icss.meeting.vo.Employee;

public class ValidateUsernameServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag=true;
		String message="";
		EmployeeDao dao=new EmployeeDao();
		Employee emp = null;
		try {
			emp = dao.selectByUsername(request.getParameter("username"));
		}
		catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(emp==null){
			message="用户名可以使用";
		}else{
			flag=false;
			message="用户名已经存在，请选择使用其他用户名";
		}
		
		 response.setContentType("text/xml;charset=gb2312");
		 PrintWriter out = response.getWriter();  
		 response.setHeader("Cache-Control","no-cache");
	     out.println("<?xml version='1.0' encoding='"+"gb2312"+"' ?>");
	     out.println("<response>");
	     out.println("<passed>" + Boolean.toString(flag) + "</passed>");
	     out.println("<message>" + message + "</message>");
	     out.println("</response>");
	     out.close();
		
	}

}
