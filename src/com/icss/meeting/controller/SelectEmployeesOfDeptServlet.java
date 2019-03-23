package com.icss.meeting.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.icss.meeting.dao.EmployeeDao;
import com.icss.meeting.vo.Employee;
@WebServlet("/SelectEmployeesOfDeptServlet")
public class SelectEmployeesOfDeptServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int departmentid = Integer.parseInt(request.getParameter("departmentid"));
		System.out.println("departmentid " + departmentid);
		EmployeeDao dao = new EmployeeDao();
		List<Employee> employeesList;
		try {
			employeesList = dao.selectEmployeesByDept(departmentid);

			// ����ѯ�õ���Ա����Ϣ����XML�ĵ��ĸ�ʽ���ص������
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter out = response.getWriter();
			response.setHeader("Cache-Control", "no-cache");
			out.println("<?xml version='1.0' encoding='" + "utf-8" + "' ?>");

			// ����XML�淶���и��ڵ㣬�������������
			out.println("<employees>");
			for (Employee e : employeesList) {
				out.println("<option>");
				out.println("<value>" + e.getEmployeeid() + "</value>");
				out.println("<text>" + e.getEmployeename() + "</text>");
				out.println("</option>");

			}
			out.println("</employees>");
			out.close();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
