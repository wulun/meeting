package com.icss.meeting.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.meeting.service.MeetingService;
import com.icss.meeting.vo.Department;

/**
 * Servlet implementation class AddDeleteDepartmentServlet
 */
@WebServlet("/AddDeleteDepartmentServlet")
public class AddDeleteDepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDeleteDepartmentServlet() {
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
		String deptid = request.getParameter("departmentid");
		int departmentid = Integer.parseInt(deptid);
		MeetingService ms = new MeetingService();
		try {
			ms.deleteDepatementByIdService(departmentid);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("ViewAllDepartmentsServlet").forward(request, response);
	}

}
