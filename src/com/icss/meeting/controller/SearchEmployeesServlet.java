package com.icss.meeting.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.meeting.service.MeetingService;
import com.icss.meeting.vo.Employee;

/**
 * Servlet implementation class SearchEmployeesServlet
 */
@WebServlet("/SearchEmployeesServlet")
public class SearchEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEmployeesServlet() {
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
		String employeename = request.getParameter("employeename");
		String username = request.getParameter("username");
		String status = request.getParameter("status");
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		MeetingService ms = new MeetingService();
		//count ����������
		int count = ms.getCountService(employeename, username, status);
		//pagesize ÿҳ��ʾ����
		int pagesize = 4;
		//total ��ҳ��
		int total = ms.getTotalService(pagesize, employeename, username, status);
		String cur = request.getParameter("pageNum");
		int curr = 1;
	
		curr = Integer.parseInt(cur);
		if (curr <=0) {
			request.setAttribute("msg", "ҳ�벻��Ϊ�㣡");
			curr = 1;
		}
		if (curr > total) {
			
				curr = total;
			}
			employeeList = ms.selectEmployeeByNUSService(employeename, username, status, curr, pagesize);
			request.setAttribute("pageNum", curr);
			request.setAttribute("countOfPages", total);
			request.setAttribute("countOfEmployees", count);
			request.setAttribute("employeesList", employeeList);
			request.setAttribute("search", "1");
			request.getRequestDispatcher("searchemployees.jsp").forward(request, response);
		
		
	}

}
