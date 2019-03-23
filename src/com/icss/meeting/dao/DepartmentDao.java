package com.icss.meeting.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.icss.meeting.util.DBUtil;
import com.icss.meeting.vo.Department;

public class DepartmentDao {
	/**********
	 * ������
	 */
	// ��ѯ���в�����Ϣ�����ص�������
	public List<Department> selectAll() throws ClassNotFoundException, SQLException {
		PreparedStatement ps = DBUtil.getConnection().prepareStatement("select * from department");
		List<Department> departmentsList = new ArrayList<Department>();
		ResultSet rs = ps.executeQuery();
		Department department;
		while (rs.next()) {
			department = new Department();
			department.setDepartmentid(rs.getInt("departmentid"));
			department.setDepartmentname(rs.getString("departmentname"));
			departmentsList.add(department);
		}
		rs.close();
		ps.close();
		DBUtil.close();
		return departmentsList;
	}

	// ���department�в����¼
	public void insert(String departmentname) throws SQLException, ClassNotFoundException {
		PreparedStatement ps = DBUtil.getConnection()
				.prepareStatement("insert into department (departmentname) values(?)");
		ps.setString(1, departmentname);
		ps.executeUpdate();
		ps.close();
		DBUtil.close();
	}

	// ����idɾ��һ������
	public void delete(int departmentid) throws ClassNotFoundException, SQLException {
		PreparedStatement ps = DBUtil.getConnection().prepareStatement
				("delete from department where departmentid=?");
			ps.setInt(1, departmentid);
			ps.executeUpdate();
			ps.close();
			DBUtil.close();
		}
	
}
