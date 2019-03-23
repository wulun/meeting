package com.icss.meeting.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.meeting.service.EmployeeService;

/**
 * Servlet implementation class UpdatePassServlet
 */
@WebServlet("/UpdatePassServlet")
public class UpdatePassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePassServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String employeename=request.getParameter("employeename");
		String password=request.getParameter("pwd");
		EmployeeService es= new EmployeeService();
		byte[] pwd=password.getBytes("UTF-8");
		Pattern p=Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m=p.matcher("employeename");
		System.out.println(pwd.length);
		if(pwd.length<6 ||pwd.length>12){
			request.setAttribute("msg", "密码必须为6位到12位!");
			request.setAttribute("employeename", employeename);
			request.getRequestDispatcher("updatePass.jsp").forward(request, response);
			
		}
		
		else if(password!=null && password!=""){
		try {
			es.updatePassService(employeename, password);
			request.setAttribute("msg","修改成功,请重新登录!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	

}
