package com.icss.meeting.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.icss.meeting.util.DBUtil;
import com.icss.meeting.vo.Employee;
import com.icss.meeting.vo.Meeting;
import com.icss.meeting.vo.MeetingRoom;

public class MeetingParticipantsDao {

	/******+
	 *符传统
	 */
	//参加某个会议的所有员工
	public ArrayList<Employee> selectAllEmployeeByMeetingIdDao(int meetingid) throws SQLException{
		ArrayList<Employee> eList=new ArrayList<Employee>();
		String sql="SELECT  * FROM employee e INNER JOIN meetingparticipants mp ON e.employeeid=mp.employeeid "+
" WHERE mp.meetingid=?";
		PreparedStatement ps=DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1,meetingid);
		ResultSet rs =ps.executeQuery();
		while(rs.next()){
			Employee e=new Employee();
			e.setEmployeeid(rs.getInt(1));
			e.setEmployeename(rs.getString(2));
			e.setUsername(rs.getString(3));
			e.setPhone(rs.getString(4));
			e.setEmail(rs.getString(5));
			e.setStatus(rs.getString(6));
			e.setDepartmentid(rs.getInt(7));
			e.setPassword(rs.getString(8));
			e.setRole(rs.getString(9));
			eList.add(e);
		}
		rs.close();
		ps.close();
		DBUtil.close();
		return eList;
	}
	// 查询某员工最近七天参加的所有会议
	public ArrayList<Meeting> selectAllNewMeetingsDao(int participantsid) throws SQLException{
		
		ArrayList<Meeting> meetingslist=new ArrayList<Meeting>();
		Meeting meeting=null;	
		//状态为 0表示可以参加  为1 表示已经取消
		String sql="select * from meeting m INNER JOIN meetingparticipants mp  ON m.meetingid=mp.meetingid "+
		" WHERE mp.employeeid=? and m.starttime>=sysdate-1  and m.starttime< sysdate+7 and m.status='0'";
		PreparedStatement ps= DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, participantsid);
		ResultSet rs =ps.executeQuery();
		while(rs.next()){
			meeting=new Meeting();
			meeting.setMeetingid(rs.getInt(1));
			meeting.setMeetingname(rs.getString(2));
			meeting.setRoomid(rs.getInt(3));
			meeting.setReservationistid(rs.getInt(4));
			meeting.setNumberofparticipants(rs.getInt(5));
			meeting.setStarttime(rs.getString(6));
			meeting.setEndtime(rs.getString(7));
			meeting.setReservationtime(rs.getString(8));
			meeting.setCanceledtime(rs.getString(9));
			meeting.setDescription(rs.getString(10));
			meeting.setStatus(rs.getString(11));
			meetingslist.add(meeting);
		}
		rs.close();
		ps.close();
		DBUtil.close();
	
		 return meetingslist;
	 }
	 
//	 查询某员工被要求参加，但是又取消的会议
	 public ArrayList<Meeting> selectAllCanceledMeetingsDao(int participantsid) throws SQLException{
		
		ArrayList<Meeting> meetingslist=new ArrayList<Meeting>();
		Meeting meeting=null;	
		String sql="select * from meeting m INNER JOIN meetingparticipants mp ON m.meetingid=mp.meetingid "+
		" WHERE mp.employeeid=? and m.starttime >= sysdate-1 and m.status='1'";	
		PreparedStatement ps=DBUtil.getConnection().prepareStatement(sql);
	 	ps.setInt(1, participantsid);
	 						 		
		ResultSet rs =ps.executeQuery();
		while(rs.next()){
			meeting=new Meeting();
			meeting.setMeetingid(rs.getInt(1));
			meeting.setMeetingname(rs.getString(2));
			meeting.setRoomid(rs.getInt(3));
			meeting.setReservationistid(rs.getInt(4));
			meeting.setNumberofparticipants(rs.getInt(5));
			meeting.setStarttime(rs.getString(6));
			meeting.setEndtime(rs.getString(7));
			meeting.setReservationtime(rs.getString(8));
			meeting.setCanceledtime(rs.getString(9));
			meeting.setDescription(rs.getString(10));
			meeting.setStatus(rs.getString(11));
			meetingslist.add(meeting);
		}
		rs.close();
		ps.close();
		DBUtil.close();
		 return meetingslist;
	 }
	 

	/******
	 * 姚辛
	 */
	 //向表meetingparticipants中插入记录
	  public void insert(int meetingid,int employeeid){
		  
		  String sql="insert into meetingparticipants values(?,?)";	
			PreparedStatement pstmt = null;
			try {
				pstmt = DBUtil.getConnection().prepareStatement(sql);
			}
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				pstmt.setInt(1, meetingid);
			}
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				pstmt.setInt(2, employeeid);
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				pstmt.executeUpdate();
				pstmt.close();
				DBUtil.close();
				
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		
			
		
	  }
	
}
