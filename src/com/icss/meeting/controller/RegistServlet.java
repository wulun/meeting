package com.icss.meeting.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.meeting.dao.DepartmentDao;
import com.icss.meeting.service.EmployeeService;
import com.icss.meeting.vo.Department;
import com.icss.meeting.vo.Employee;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
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
		request.setCharacterEncoding("UTF-8");

		String employeename = request.getParameter("employeename");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int deptid = Integer.parseInt(request.getParameter("deptid"));
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		byte[] a =employeename.getBytes("UTF-8");		
		if(a.length<20){
			Employee employee = new Employee();
			employee.setEmployeename(employeename);
			employee.setUsername(username);
			employee.setPassword(password);
			employee.setDepartmentid(deptid);
			employee.setEmail(email);
			employee.setPhone(phone);
			employee.setStatus("0");
			employee.setRole("2");
			EmployeeService service = new EmployeeService();
			int flag = 0;
			try {
				flag = service.regist(employee);
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(flag==1){
				request.setAttribute("msg", "注册成功，正在审核");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else{
				request.setAttribute("msg", "用户名已存在，请重新注册");
				DepartmentDao dao = new DepartmentDao();
				List<Department> departmentsList = null;
				try {
					departmentsList = dao.selectAll();
				}
				catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("departmentsList", departmentsList);
				request.getRequestDispatcher("registers.jsp").forward(request, response);
			}
		}else{
			request.setAttribute("msg", "用户名过长,请重新输入!");
			DepartmentDao dao = new DepartmentDao();
			List<Department> departmentsList = null;
			try {
				departmentsList = dao.selectAll();
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("departmentsList", departmentsList);
			request.getRequestDispatcher("registers.jsp").forward(request, response);
		}
		
	}

}
