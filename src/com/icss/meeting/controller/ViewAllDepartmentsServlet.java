package com.icss.meeting.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.meeting.service.MeetingService;
import com.icss.meeting.vo.Department;

/**
 * Servlet implementation class SelectAllDepartmentServlet
 */
@WebServlet("/ViewAllDepartmentsServlet")
public class ViewAllDepartmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllDepartmentsServlet() {
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
		request.setCharacterEncoding("UTF-8");
		ArrayList<Department> departmentsList = new ArrayList<Department>();
		MeetingService ms = new MeetingService();
		departmentsList = ms.selectAllDepartmentService();
		request.setAttribute("departmentsList", departmentsList); 
		request.getRequestDispatcher("departments.jsp").forward(request, response);
	}

}
