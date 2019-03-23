package com.icss.meeting.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.meeting.service.EmployeeService;
import com.icss.meeting.vo.Employee;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
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
		String username = request.getParameter("username");
		String password = request.getParameter("pwd");
		String timelength = request.getParameter("timelength");
		int days = 0;
		if(timelength!=null){
			days = Integer.parseInt(timelength);
		}
		if(days!=0){
			//将用户名和密码作为cookie对象保存
			Cookie usernamecookie = new Cookie("username",username);
			Cookie passwordcookie = new Cookie("password",password);
			usernamecookie.setMaxAge(days*60);
			passwordcookie.setMaxAge(days*60);
			response.addCookie(usernamecookie);
			response.addCookie(passwordcookie);
		}
		
		
		EmployeeService service = new EmployeeService();
		int flag = service.login(username, password);
		
		if(flag == 1){
			ServletContext ctxt = this.getServletContext();
			int visitcount;
			if(ctxt.getAttribute("visitcount")==null){
				visitcount=0;
			}else{
				visitcount = Integer.parseInt(ctxt.getAttribute("visitcount").toString());
			}
			visitcount++;
			ctxt.setAttribute("visitcount", visitcount);
			//获得会话对象
			HttpSession session = request.getSession();
			Employee loginedEmployee = service.getLoginedEmployee();
			//把登录成功的员工姓名保存到会话中
			session.setAttribute("employeename", loginedEmployee.getEmployeename());
			session.setAttribute("employeeid", loginedEmployee.getEmployeeid());
			
			//根据角色，跳转到不同的首页面 
			String role = loginedEmployee.getRole();
			if(role.equals("1")){
				request.getRequestDispatcher("adminindex.jsp").forward(request, response);
			}
			if(role.equals("2")){
				request.getRequestDispatcher("employeeindex.jsp").forward(request, response);
			}
		}else{
			if(flag == 0){
				request.setAttribute("msg", "正在审核，请耐心等待");
			}
			if(flag == 2){
				request.setAttribute("msg", "审核未通过，请核实后重新注册");
			}
			if(flag == 3){
				request.setAttribute("msg", "用户名或密码错误，请重试 ");
			}
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}

}
