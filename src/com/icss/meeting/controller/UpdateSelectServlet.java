package com.icss.meeting.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.meeting.service.EmployeeService;


/**
 * Servlet implementation class UpdateSelectServlet
 */
@WebServlet("/UpdateSelectServlet")
public class UpdateSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		EmployeeService es = new EmployeeService();
		String employeename =request.getParameter("name");
		byte[] a=employeename.getBytes("ISO-8859-1");
		String newemp=new String(a,"UTF-8");
		
		//String employeename =(String)session.getAttribute("name");
		es.updateSelectByNameService(newemp);
		request.setAttribute("employeename", newemp);
		request.getRequestDispatcher("updatePass.jsp").forward(request, response);
		

	}

}
