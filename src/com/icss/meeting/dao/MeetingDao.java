package com.icss.meeting.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.Current;

import com.icss.meeting.util.DBUtil;
import com.icss.meeting.vo.Department;
import com.icss.meeting.vo.Employee;
import com.icss.meeting.vo.Meeting;

public class MeetingDao {
	
	/******************************************************
	 * 
	 * 
	 * 
	 * 符传统
	 */
	// 通过预订者id 查询该人预订的会议
	// 查询我预定的会议
	public ArrayList<Meeting> selectMyBookAllMeetingByReserIdDao(int reserid) throws SQLException {
		String sql = "select * from meeting WHERE reservationistid= ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ArrayList<Meeting> list = new ArrayList<Meeting>();
		ps.setInt(1, reserid);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Meeting mt = new Meeting();
			mt.setMeetingid(rs.getInt(1));
			mt.setMeetingname(rs.getString(2));
			mt.setRoomid(rs.getInt(3));
			mt.setReservationistid(rs.getInt(4));
			mt.setNumberofparticipants(rs.getInt(5));
			mt.setStarttime(rs.getString(6));
			mt.setEndtime(rs.getString(7));
			mt.setReservationtime(rs.getString(8));
			mt.setCanceledtime(rs.getString(9));
			mt.setDescription(rs.getString(10));
			mt.setStatus(rs.getString(11));
			list.add(mt);

		}
		rs.close();
		ps.close();
		DBUtil.close();
		return list;
	}

	// 通过会议ID查询会议
	public Meeting selectMeetingByIdDao(int id) throws SQLException {
		String sql = "select * from meeting WHERE meetingid= ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);

		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Meeting mt = new Meeting();
		while (rs.next()) {

			mt.setMeetingid(rs.getInt(1));
			mt.setMeetingname(rs.getString(2));
			mt.setRoomid(rs.getInt(3));
			mt.setReservationistid(rs.getInt(4));
			mt.setNumberofparticipants(rs.getInt(5));
			mt.setStarttime(rs.getString(6));
			mt.setEndtime(rs.getString(7));
			mt.setReservationtime(rs.getString(8));
			mt.setCanceledtime(rs.getString(9));
			mt.setDescription(rs.getString(10));
			mt.setStatus(rs.getString(11));

		}
		rs.close();
		ps.close();
		DBUtil.close();
		return mt;

	}

	// 根据meetingid 修改 状态 时间
	// timestamp 时间戳 一般用来计数 可以用来获取当前的系统时间 有两种用法
	// 1 Date date =new Date();
	// Timestamp tt=new Timestamp(date.getTime());
	// 2 Timestamp tt=new Timestamp(System.currentTimeMillis());
	public void updateMeetingDao(int id, String status, Timestamp time) throws SQLException {

		String sql = "update meeting set status =? ,canceledtime =? where meetingid=?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setString(1, status);
		ps.setTimestamp(2, time);
		ps.setInt(3, id);
		ps.executeUpdate();
		ps.close();

		DBUtil.close();

	}

	// 查询我将要参加的会议
	public ArrayList<Meeting> selectMyMeetingInfoDao(int employeeid) throws SQLException {
		String sql = "SELECT m.meetingname,m.roomid,m.starttime,m.endtime,m.reservationtime,m.reservationistid,"
				+ "m.meetingid FROM employee e INNER JOIN  meetingparticipants mp ON e.employeeid=mp.employeeid "
				+ " INNER JOIN  meeting m ON m.meetingid=mp.meetingid " + " WHERE e.employeeid=? and m.status='0'";
		ArrayList<Meeting> mList = new ArrayList<Meeting>();
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, employeeid);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Meeting m = new Meeting();
			m.setMeetingname(rs.getString(1));
			m.setRoomid(rs.getInt(2));
			m.setStarttime(rs.getString(3));
			m.setEndtime(rs.getString(4));
			m.setReservationtime(rs.getString(5));
			m.setReservationistid(rs.getInt(6));
			m.setMeetingid(rs.getInt(7));
			mList.add(m);

		}
		rs.close();
		ps.close();
		DBUtil.close();
		return mList;
	}
	/******************************************************
	 * 
	 * 
	 * 
	 * 郑鸿志
	 */

	/******************************************************
	 * 
	 * 
	 * 
	 * 姚辛
	 */
	// 向表meeting中插入记录，其中status使用默认值0
	public int insert(Meeting meeting) throws SQLException, ClassNotFoundException {
		System.out.println(meeting);
		int meetingid = 0;
		PreparedStatement ps1 = DBUtil.getConnection().prepareStatement("select max(meetingid) from meeting");
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(
				"insert into meeting(meetingid,meetingname,roomid,reservationistid,numberofparticipants,starttime,endtime,reservationtime,description,status) values(meeting_id_seq.nextval,?,?,?,?,?,?,?,?,?)");

		ps.setString(1, meeting.getMeetingname());
		ps.setInt(2, meeting.getRoomid());
		ps.setInt(3, meeting.getReservationistid());
		ps.setInt(4, meeting.getNumberofparticipants());
		System.out.println(meeting.getStarttime());
		ps.setTimestamp(5, Timestamp.valueOf(meeting.getStarttime()));
		ps.setTimestamp(6, Timestamp.valueOf(meeting.getEndtime()));
		ps.setTimestamp(7,Timestamp.valueOf(meeting.getReservationtime()));
		ps.setString(8, meeting.getDescription());
		ps.setString(9, "0");
		ps.executeUpdate();
		ResultSet rs = ps1.executeQuery();
		if (rs.next()) {
			meetingid = rs.getInt(1);
		}
		rs.close();
		ps.close();
		DBUtil.close();
		return meetingid;
	}

	// 根据meetingid，更新会议的状态，删除时间
	public void update(int meetingid, String status, Timestamp canceledtime) throws SQLException, ClassNotFoundException {
		PreparedStatement ps = DBUtil.getConnection().prepareStatement("update meeting set status=?,canceledtime=? where meetingid");
		ps.setString(1, status);
		ps.setTimestamp(2, canceledtime);
		ps.executeUpdate();
		ps.close();
		DBUtil.close();
	}

	// 查询某员工预定的所有会议
	public List<Meeting> selectAllMeetingsByReserId(int reservationistid) throws ClassNotFoundException, SQLException {
		PreparedStatement ps = DBUtil.getConnection().prepareStatement("select * from meeting where reservationistid=?");
		List<Meeting> meetingslist = new ArrayList<Meeting>();
		Meeting meeting = null;
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			meeting = new Meeting();
			meeting.setMeetingid(rs.getInt("meetingid"));
			meeting.setMeetingname(rs.getString("meetingname"));
			meeting.setRoomid(rs.getInt("roomid"));
			meeting.setReservationistid(rs.getInt("reservationistid"));
			meeting.setNumberofparticipants(rs.getInt("numberofparticipants"));
			meeting.setStarttime(rs.getString("starttime"));
			meeting.setEndtime(rs.getString("endtime"));
			meeting.setReservationtime(rs.getString("reservationtime"));
			meeting.setCanceledtime(rs.getString("canceledtime"));
			meeting.setDescription(rs.getString("description"));
			meeting.setStatus(rs.getString("status"));
			meetingslist.add(meeting);
		}
		return meetingslist;
	}
	//
	/*public int insertSec(Meeting meeting) throws SQLException{
		  int meetingid=0;
		
		  String sql="insert into meeting"
				  +
					"(meetingname,roomid,reservationistid,numberofparticipants,starttime,endtime,reservationtime,canceledtime,description,status)" +
					" values(meeting_id_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(sql);
			pstmt.setString(1, meeting.getMeetingname());
			pstmt.setInt(2, meeting.getRoomid());
			pstmt.setInt(3, meeting.getReservationistid());
			pstmt.setInt(4, meeting.getNumberofparticipants());
			pstmt.setString(5, meeting.getStarttime());
			pstmt.setString(6, meeting.getEndtime());
			pstmt.setString(7, meeting.getReservationtime());
			pstmt.setString(8, meeting.getCanceledtime());
			pstmt.setString(9,meeting.getDescription());
			pstmt.setString(10, "0");
			pstmt.executeUpdate();	
			
			ResultSet rs=pstmt.executeQuery("select max(meetingid) from meeting");
			if(rs.next()){
				meetingid=rs.getInt(1);
			}
		
		  return meetingid;
	  }*/
	/******************************************************
	 * 
	 * 
	 * 
	 * 刘益龙
	 */

	// 添加部门
	public void AddDepartmentDAO(String departmentname) throws SQLException {
		PreparedStatement ps = DBUtil.getConnection().prepareStatement("insert into department values(dept_seq.nextval,?)");
		ps.setString(1, departmentname);
		ps.executeUpdate();
		ps.close();
		DBUtil.close();

	}

	// 查询所有部门
	public ArrayList<Department> selectAllDepartmentDAO() throws SQLException {
		PreparedStatement ps = DBUtil.getConnection().prepareStatement("select * from department");
		ResultSet rs = ps.executeQuery();
		ArrayList<Department> list = new ArrayList<Department>();
		while (rs.next()) {
			Department dt = new Department();
			dt.setDepartmentid(rs.getInt(1));
			dt.setDepartmentname(rs.getString(2));
			list.add(dt);
		}
		rs.close();
		ps.close();
		DBUtil.close();
		return list;
	}

	// 删除部门
	public void deleteDepatementByIdDAO(int departmentid) throws SQLException {
		PreparedStatement ps = DBUtil.getConnection().prepareStatement("delete from department where departmentid=?");
		ps.setInt(1, departmentid);
		ps.executeUpdate();
		ps.close();
		DBUtil.close();
	}

	// 注册审批之查询
	public ArrayList<Employee> selectEmployeeByStatusDAO() throws SQLException {
		PreparedStatement ps = DBUtil.getConnection().prepareStatement("select * from employee WHERE role = '2' and status = '0'");
		ResultSet rs = ps.executeQuery();
		ArrayList<Employee> list = new ArrayList<Employee>();
		while (rs.next()) {
			Employee emp = new Employee();
			emp.setEmployeeid(rs.getInt(1));
			emp.setEmployeename(rs.getString(2));
			emp.setUsername(rs.getString(3));
			emp.setPhone(rs.getString(4));
			emp.setEmail(rs.getString(5));
			emp.setStatus(rs.getString(6));
			emp.setDepartmentid(rs.getInt(7));
			emp.setPassword(rs.getString(8));
			emp.setRole(rs.getString(9));
			list.add(emp);
		}
		rs.close();
		ps.close();
		DBUtil.close();
		return list;
	}

	// 审批
	public void updateStatusDAO(int employeeid, String status) throws SQLException {
		PreparedStatement ps = DBUtil.getConnection().prepareStatement("update employee set status = ? where employeeid=?");
		ps.setString(1, status);
		ps.setInt(2, employeeid);
		ps.executeUpdate();
		ps.close();
		DBUtil.close();
	}

	// 搜索
	public ArrayList<Employee> selectEmployeeByNUSDAO(String employeename, String username, String status, int current, int pagesize)
			throws SQLException {
		String sql = null;
		String empname, uname, sta;
		if (employeename == null || employeename.equals("")) {
			empname = "";
		}
		else {
			empname = "and employeename = '" + employeename + "'";
		}
		if (username == null || username.equals("")) {
			uname = "";
		}
		else {
			uname = "and username='" + username + "'";
		}
		if (status.equals("0") || status.equals("1") || status.equals("2")) {
			sta = "and status = '" + status + "'";
		}
		else {
			sta = "";
		}
		sql = "select * from (select u.*,rownum rn from employee u where role='2' and rownum<=" + current * pagesize + empname + uname + sta
				+ ") where rn >" + (current - 1) * pagesize;
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ArrayList<Employee> list = new ArrayList<Employee>();
		while (rs.next()) {
			Employee emp = new Employee();
			emp.setEmployeeid(rs.getInt(1));
			emp.setEmployeename(rs.getString(2));
			emp.setUsername(rs.getString(3));
			emp.setPhone(rs.getString(4));
			emp.setEmail(rs.getString(5));
			emp.setStatus(rs.getString(6));
			emp.setDepartmentid(rs.getInt(7));
			emp.setPassword(rs.getString(8));
			emp.setRole(rs.getString(9));
			list.add(emp);
		}
		rs.close();
		ps.close();
		DBUtil.close();
		return list;
	}

	//总页数
			public int getTotalDAO(int pagesize,String employeename,String username,String status) throws SQLException{
				String sql = null;
				String empname,uname,sta;
				if(employeename==null||employeename.equals("")){
					empname = "";
				}else{
					empname = "and employeename = '"+employeename+"'";
				}
				if(username==null||username.equals("")){
					uname = "";
				}else{
					uname = "and username='"+username+"'";
				}
				if(status.equals("0")||status.equals("1")||status.equals("2")){
					sta = "and status = '"+status+"'";
				}else{
					sta = "";
				}
				sql="select count(*) c from employee where role='2'"+empname+uname+sta;
				PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				int total=0;
				if(rs.next()){
					int num = rs.getInt("c");
					total = num % pagesize == 0? num/pagesize : num/pagesize+1;
				}
				rs.close();
				ps.close();
				DBUtil.close();
				return total;
				}
			//数据的总条数
			public int getCountDAO(String employeename,String username,String status) throws SQLException{
				String sql = null;
				String empname,uname,sta;
				if(employeename==null||employeename.equals("")){
					empname = "";
				}else{
					empname = "and employeename = '"+employeename+"'";
				}
				if(username==null||username.equals("")){
					uname = "";
				}else{
					uname = "and username='"+username+"'";
				}
				if(status.equals("0")||status.equals("1")||status.equals("2")){
					sta = "and status = '"+status+"'";
				}else{
					sta = "";
				}
				sql="select count(*) c from employee where role='2'"+empname+uname+sta;
				PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				int num=0;
				if(rs.next()){
					num = rs.getInt("c");
				}
				rs.close();
				ps.close();
				DBUtil.close();
				return num;
				}

	/*
	 * // 关闭账号 public String updateStatusEmployeeDAO(String username) throws
	 * SQLException { String status = "2"; PreparedStatement ps =
	 * DBUtil.getConnection().prepareStatement(
	 * "update employee set status = 2 where username=? "); ps.setString(1,
	 * username); ps.executeUpdate(); ps.close(); DBUtil.close(); return status;
	 * }
	 */

}
