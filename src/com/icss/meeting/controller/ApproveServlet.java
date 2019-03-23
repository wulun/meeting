package com.icss.meeting.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.meeting.service.MeetingService;

/**
 * Servlet implementation class ApproveServlet
 */
@WebServlet("/ApproveServlet")
public class ApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveServlet() {
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
		String employeeid = request.getParameter("employeeid");
		
		int empid = Integer.parseInt(employeeid);
		String oper = request.getParameter("oper");
		MeetingService ms = new MeetingService();
		if(oper!=null&&oper.equals("yes")){
			ms.updateStatusService(empid, "1");
			request.getRequestDispatcher("ViewAllEmployeesServlet?code=approve").forward(request, response);
		}
		if(oper!=null&&oper.equals("no")){
			ms.updateStatusService(empid, "2");
			request.getRequestDispatcher("ViewAllEmployeesServlet?code=approve").forward(request, response);
		}
		if(oper!=null&&oper.equals("close")){
			ms.updateStatusService(empid, "2");
			request.getRequestDispatcher("SearchEmployeesServlet").forward(request, response);
		}
		
	}

}
