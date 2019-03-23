package com.icss.meeting.service;

import java.sql.SQLException;
import java.util.List;

import com.icss.meeting.dao.DepartmentDao;
import com.icss.meeting.vo.Department;

public class DepartmentService {
	private DepartmentDao dao=new DepartmentDao();

	public List<Department> viewAllDepartments() throws ClassNotFoundException, SQLException{
		return dao.selectAll();
	}
	
	public void addDepartment(String departmentname) throws ClassNotFoundException, SQLException{
		dao.insert(departmentname);
	}
	
	public void deleteDepartment(int departmentid) throws ClassNotFoundException, SQLException{
		dao.delete(departmentid);
	}
}
