package com.icss.meeting.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.icss.meeting.util.DBUtil;
import com.icss.meeting.vo.Employee;

public class EmployeeDao {
	/*******************************************
	 * 
	 * 符传统
	 */

	// 通过用户名查询，返回Employee对象
	public Employee selectByIdDao(int id) throws SQLException {

		Employee employee = null;

		PreparedStatement ps = null;
		String sql = "select * from employee where employeeid=?";
		ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next() == true) {
			employee = new Employee();
			employee.setEmployeeid(rs.getInt(1));
			employee.setEmployeename(rs.getString(2));
			employee.setUsername(rs.getString(3));
			employee.setPhone(rs.getString(4));
			employee.setEmail(rs.getString(5));
			employee.setStatus(rs.getString(6));
			employee.setDepartmentid(rs.getInt(7));
			employee.setPassword(rs.getString(8));
			employee.setRole(rs.getString(9));
		}
		rs.close();
		ps.close();
		DBUtil.close();

		return employee;
	}

	/*******************************************
	 * 
	 * 郑鸿志
	 */

	public Employee selectByNamePwd(String username, String pwd) throws SQLException {
		Employee employee = null;
		String sql = "select * from employee where username='" + username + "' and password= '" + pwd + "'";
		PreparedStatement st = DBUtil.getConnection().prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if (rs.next() == true) {
			employee = new Employee();
			employee.setEmployeeid(rs.getInt("employeeid"));
			employee.setEmployeename(rs.getString("employeename"));
			employee.setUsername(rs.getString("username"));
			employee.setPhone(rs.getString("phone"));
			employee.setEmail(rs.getString("email"));
			employee.setStatus(rs.getString("status"));
			employee.setDepartmentid(rs.getInt("status"));
			employee.setPassword(rs.getString("password"));
			employee.setRole(rs.getString("role"));
		}
		rs.close();
		st.close();
		DBUtil.close();
		return employee;

	}

	// 向employee中插入记录，其中status和role使用默认值
	public void updateStatus(int employeeid, String status) throws SQLException {

		String sql = "update employee set status = '" + status + "'where employeeid=" + employeeid;

		PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(sql);
		pstmt.executeQuery();
		pstmt.close();
		DBUtil.close();
	}

	/*******************************************
	 * 
	 * 姚辛
	 */
	/*
	 * public Employee selectByNamePwd(String username,String pwd) throws
	 * ClassNotFoundException, SQLException{ conn=DBUtil.getConnection();
	 * Employee employee=null;
	 * 
	 * try { PreparedStatement st=null; String sql=
	 * "select * from employee where username='"+username+"' and  password='"
	 * +pwd+"'"; st = conn.prepareStatement(sql); ResultSet rs
	 * =st.executeQuery(sql); if(rs.next()==true){ employee=new Employee();
	 * employee.setEmployeeid(rs.getInt("employeeid"));
	 * employee.setEmployeename(rs.getString("employeename"));
	 * employee.setUsername(rs.getString("username"));
	 * employee.setPhone(rs.getString("phone"));
	 * employee.setEmail(rs.getString("email"));
	 * employee.setStatus(rs.getString("status"));
	 * employee.setDepartmentid(rs.getInt("departmentid"));
	 * employee.setPassword(rs.getString("password"));
	 * employee.setRole(rs.getString("role")); } } catch (SQLException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }finally{
	 * DBUtil.close(); } return employee; }
	 */

	// 查询用户名
	public Employee selectByUsername(String username) throws ClassNotFoundException, SQLException {
		Employee employee = null;
		String sql = "select * from employee where username='" + username + "'";
		PreparedStatement st = DBUtil.getConnection().prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if (rs.next() == true) {
			employee = new Employee();
			employee.setEmployeeid(rs.getInt("employeeid"));
			employee.setEmployeename(rs.getString("employeename"));
			employee.setUsername(rs.getString("username"));
			employee.setPhone(rs.getString("phone"));
			employee.setEmail(rs.getString("email"));
			employee.setStatus(rs.getString("status"));
			employee.setDepartmentid(rs.getInt("departmentid"));
			employee.setPassword(rs.getString("password"));
			employee.setRole(rs.getString("role"));
		}
		rs.close();
		st.close();
		DBUtil.close();
		return employee;
	}

	//查询所有正在审核的员工信息，返回到集合中。
	public Employee selectById(int id) throws ClassNotFoundException, SQLException {

		Employee employee = null;
		String sql = "select * from employee where employeeid=" + id;
		PreparedStatement st = DBUtil.getConnection().prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if (rs.next() == true) {
			employee = new Employee();
			employee.setEmployeeid(rs.getInt("employeeid"));
			employee.setEmployeename(rs.getString("employeename"));
			employee.setUsername(rs.getString("username"));
			employee.setPhone(rs.getString("phone"));
			employee.setEmail(rs.getString("email"));
			employee.setStatus(rs.getString("status"));
			employee.setDepartmentid(rs.getInt("departmentid"));
			employee.setPassword(rs.getString("password"));
			employee.setRole(rs.getString("role"));
		}
		return employee;

	}

	// 根据部门查询当前部门的员工
	public List<Employee> selectAllEmployee() throws ClassNotFoundException, SQLException {
		List<Employee> employeeslist = new ArrayList<Employee>();
		Employee employee = null;
		String sql = "select * from employee where role='2' and status='0'";
		PreparedStatement st = DBUtil.getConnection().prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			employee = new Employee();
			employee.setEmployeeid(rs.getInt("employeeid"));
			employee.setEmployeename(rs.getString("employeename"));
			employee.setUsername(rs.getString("username"));
			employee.setPhone(rs.getString("phone"));
			employee.setEmail(rs.getString("email"));
			employee.setStatus(rs.getString("status"));
			employee.setDepartmentid(rs.getInt("departmentid"));
			employee.setPassword(rs.getString("password"));
			employee.setRole(rs.getString("role"));
			employeeslist.add(employee);
		}
		return employeeslist;
	}

	//根据姓名、用户名、状态， 查询所有员工信息，返回到集合中。
	public List<Employee> selectEmployeesByDept(int departmentid) throws ClassNotFoundException, SQLException {
		List<Employee> employeeslist = new ArrayList<Employee>();
		Employee employee = null;
		String sql = "select * from employee where departmentid=" + departmentid;
		PreparedStatement st = DBUtil.getConnection().prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			employee = new Employee();
			employee.setEmployeeid(rs.getInt("employeeid"));
			employee.setEmployeename(rs.getString("employeename"));
			employee.setUsername(rs.getString("username"));
			employee.setPhone(rs.getString("phone"));
			employee.setEmail(rs.getString("email"));
			employee.setStatus(rs.getString("status"));
			employee.setDepartmentid(rs.getInt("departmentid"));
			employee.setPassword(rs.getString("password"));
			employee.setRole(rs.getString("role"));
			employeeslist.add(employee);
		}
		rs.close();
		st.close();
		DBUtil.close();
		return employeeslist;
	}

	// 根据姓名、用户名、状态， 查询当前页的员工信息，返回到集合中。
	public List<Employee> selectEmployeesByNameStatus(String employeename, String username, String status)
			throws ClassNotFoundException, SQLException {
		List<Employee> employeeslist = new ArrayList<Employee>();
		Employee employee = null;

		PreparedStatement st = null;
		String sql = null;
		String usernamesql, employeenamesql, statussql;

		if (employeename == null || employeename.equals("")) {
			employeenamesql = "";
		}
		else {
			employeenamesql = " and employeename='" + employeename + "'";
		}

		if (username == null || username.equals("")) {
			usernamesql = "";
		}
		else {
			usernamesql = " and username='" + username + "'";
		}

		if (status == null || status.equals("") || status.equals("3")) {
			statussql = "";
		}
		else {
			statussql = " and status='" + status + "'";
		}

		sql = "select * from Employee where role='2' " + usernamesql + employeenamesql + statussql;

		st = DBUtil.getConnection().prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			employee = new Employee();
			employee.setEmployeeid(rs.getInt("employeeid"));
			employee.setEmployeename(rs.getString("employeename"));
			employee.setUsername(rs.getString("username"));
			employee.setPhone(rs.getString("phone"));
			employee.setEmail(rs.getString("email"));
			employee.setStatus(rs.getString("status"));
			employee.setDepartmentid(rs.getInt("departmentid"));
			employee.setPassword(rs.getString("password"));
			employee.setRole(rs.getString("role"));
			employeeslist.add(employee);
		}
		rs.close();
		st.close();
		DBUtil.close();

		return employeeslist;
	}

	/*//limit是MySQL中用来分页查询的，第一个int参数表示开始的索引，从0开始，第二个参数表示要查询的条数
	public List<Employee> selectEmployeesOfOnePage(String employeename, String username, String status, int start, int end)
			throws ClassNotFoundException, SQLException {
		List<Employee> employeeslist = new ArrayList<Employee>();
		Employee employee = null;

		PreparedStatement st = null;
		String sql = null;
		String usernamesql, employeenamesql, statussql;

		if (employeename == null || employeename.equals("")) {
			employeenamesql = "";
		}
		else {
			employeenamesql = " and employeename='" + employeename + "'";
		}

		if (username == null || username.equals("")) {
			usernamesql = "";
		}
		else {
			usernamesql = " and username='" + username + "'";
		}

		if (status == null || status.equals("") || status.equals("3")) {
			statussql = "";
		}
		else {
			statussql = " and status='" + status + "'";
		}

		//
		sql = "select * from Employee where role='2' " + usernamesql + employeenamesql + statussql + " limit " + start + " ," + end;

		st = DBUtil.getConnection().prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			employee = new Employee();
			employee.setEmployeeid(rs.getInt("employeeid"));
			employee.setEmployeename(rs.getString("employeename"));
			employee.setUsername(rs.getString("username"));
			employee.setPhone(rs.getString("phone"));
			employee.setEmail(rs.getString("email"));
			employee.setStatus(rs.getString("status"));
			employee.setDepartmentid(rs.getInt("departmentid"));
			employee.setPassword(rs.getString("password"));
			employee.setRole(rs.getString("role"));
			employeeslist.add(employee);
		}
		rs.close();
		st.close();
		DBUtil.close();

		return employeeslist;
	}*/

/*	//更新员工
	public void updateStatus(int employeeid, String status) throws ClassNotFoundException, SQLException {
		conn = DBUtil.getConnection();
		String sql = "update employee set status='" + status + "'where employeeid=" + employeeid;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtil.close();
		}
	}
*/
	//向表employee中插入记录，其中status和role使用默认值
	public void insert(Employee employee) throws ClassNotFoundException, SQLException {
		
		String sql = "insert into employee" + "(employeeid,employeename,username,password,phone,email,departmentid,status,role)"
				+ " values(emp_id_seq.nextval,?,?,?,?,?,?,?,?)";
		
			PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(sql);
			
			pstmt.setString(1, employee.getEmployeename());
			pstmt.setString(2, employee.getUsername());
			pstmt.setString(3, employee.getPassword());
			pstmt.setString(4, employee.getPhone());
			pstmt.setString(5, employee.getEmail());
			pstmt.setInt(6, employee.getDepartmentid());
			//
			pstmt.setString(7, "0");
			//
			pstmt.setString(8, "2");
			pstmt.executeUpdate();
			pstmt.close();
			DBUtil.close();
		
	}

	/*******************************************
	 * 
	 * 郑鸿志
	 */
	// 修改 之前的查询
		 public Employee updateSelectByName(String employeename) throws SQLException{
			 PreparedStatement ps = DBUtil.getConnection().prepareStatement
					 ("select * from employee where employeename=?");
			 ps.setString(1,employeename);
			 ResultSet rs =ps.executeQuery();
			 Employee emp =new Employee();
			 while(rs.next()){
				 String name=rs.getString(2);
				 emp.setEmployeename(name);
			 }
			rs.close();
			ps.close();
			DBUtil.close();
			return emp;	
		 }
		
		//修改密码
		 public void updatePassDAO(String employeename,String password) throws SQLException{
			 PreparedStatement ps = DBUtil.getConnection().prepareStatement
					 ("update employee set password=? where employeename=?");
			 ps.setString(1, password);
			 ps.setString(2, employeename);
			 ps.executeUpdate();
			 ps.close();
			 DBUtil.close();
		 }

	

}
