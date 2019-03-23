package com.icss.meeting.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.meeting.dao.EmployeeDao;
import com.icss.meeting.service.MeetingService;
import com.icss.meeting.vo.Employee;

/**
 * Servlet implementation class ViewAllEmployeesServlet
 */
@WebServlet("/ViewAllEmployeesServlet")
public class ViewAllEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllEmployeesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		//×¢²áÉóÅú
		request.setCharacterEncoding("UTF-8");
		String code = request.getParameter("code");
		ArrayList<Employee> list = new ArrayList<Employee>();
		MeetingService ms = new MeetingService();
		list = ms.selectEmployeeByStatusService();
		request.setAttribute("employeesList", list);
		if(code!=null&&code.equals("approve")){
				request.getRequestDispatcher("approveaccount.jsp").forward(request, response);
		}
	}

}
