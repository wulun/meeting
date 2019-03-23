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

import com.icss.meeting.dao.DepartmentDao;
import com.icss.meeting.service.MeetingService;
import com.icss.meeting.vo.Department;

/**
 * Servlet implementation class ViewDepartmentsServlet
 */
@WebServlet("/ViewDepartmentsServlet")
public class ViewDepartmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewDepartmentsServlet() {
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

		DepartmentDao dao=new DepartmentDao();
		
		List<Department> departmentsList = null;
		try {
			departmentsList = dao.selectAll();
			for(Department dep:departmentsList){
				System.out.println(dep);
			}
			request.setAttribute("departmentsList", departmentsList);
			request.getRequestDispatcher("registers.jsp").forward(request, response);
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*String code=request.getParameter("code");
		if(code!=null&&code.equals("regist")){
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}*/
	}

}
