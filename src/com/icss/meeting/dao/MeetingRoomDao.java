package com.icss.meeting.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.icss.meeting.util.DBUtil;
import com.icss.meeting.vo.MeetingRoom;

public class MeetingRoomDao {

	/*******************
	 * 符传统
	 * 
	 */
	// 查询一个会议室名称
	public String viewOneMeetingRoom(int id) throws SQLException {
		String sql = "select * from meeting WHERE roomid= ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		String roomName = null;
		while (rs.next()) {
			roomName = rs.getString(1);
		}
		rs.close();
		ps.close();
		DBUtil.close();
		return roomName;

	}

	// 通过ID查询会议室信息
	public MeetingRoom selectByRoomid(int id) throws SQLException {
		/* ArrayList<MeetingRoom> mrList=new ArrayList<MeetingRoom>(); */
		String sql = "select * from meetingroom WHERE roomid= ?";
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		MeetingRoom mr = new MeetingRoom();
		while (rs.next()) {

			mr.setRoomid(rs.getInt(1));
			mr.setRoomnum(rs.getInt(2));
			mr.setRoomname(rs.getString(3));
			mr.setCapacity(rs.getInt(4));
			mr.setStatus(rs.getString(5));
			mr.setDescription(rs.getString(6));
			

		}
		rs.close();
		ps.close();
		DBUtil.close();
		return mr;
	}
	// 查询开放的会议室

	/*
	 * // 查询所有会议室 public List<MeetingRoom> selectAllOpenMeetingRooms() throws
	 * ClassNotFoundException, SQLException { List<MeetingRoom> list = new
	 * ArrayList<MeetingRoom>(); PreparedStatement ps =
	 * DBUtil.getConnection().prepareStatement("select * from meetingroom");
	 * ResultSet rs = ps.executeQuery(); while (rs.next()) { MeetingRoom mt =
	 * new MeetingRoom();
	 * mt.setRoomid(Integer.parseInt(rs.getString("roomid")));
	 * mt.setRoomnum(Integer.parseInt(rs.getString("roomnum")));
	 * mt.setCapacity(Integer.parseInt(rs.getString("capacity")));
	 * mt.setRoomname(rs.getString("roomname"));
	 * mt.setStatus(rs.getString("status"));
	 * mt.setDescription(rs.getString("description")); list.add(mt); }
	 * ps.close(); rs.close(); DBUtil.close();
	 * 
	 * return list; }
	 */
	/*************
	 * 姚辛
	 */
	// 添加会议室
	public void insert(int roomnum, int capacity, String status, String roomname1, String description1)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = DBUtil.getConnection().prepareStatement("insert into meetingroom values(stu5_id_seq.nextval,?,?,?,?,?)");
		ps.setInt(1, roomnum);
		ps.setString(2, roomname1);
		ps.setInt(3, capacity);
		ps.setString(4, status);
		ps.setString(5, description1);
		ps.executeUpdate();
		ps.close();
		DBUtil.close();
	}
	/*
	 * // 根据ID查询会议室 public MeetingRoom selectByRoomid(int roomid) throws
	 * ClassNotFoundException, SQLException { PreparedStatement ps =
	 * DBUtil.getConnection().prepareStatement(
	 * "select * from meetingroom where roomid = ?"); ps.setInt(1, roomid);
	 * ResultSet rs = ps.executeQuery(); MeetingRoom mt = new MeetingRoom();
	 * while (rs.next()) {
	 * mt.setRoomid(Integer.parseInt(rs.getString("roomid")));
	 * mt.setRoomnum(Integer.parseInt(rs.getString("roomnum")));
	 * mt.setCapacity(Integer.parseInt(rs.getString("capacity")));
	 * mt.setRoomname(rs.getString("roomname"));
	 * mt.setStatus(rs.getString("status"));
	 * mt.setDescription(rs.getString("description")); } ps.close(); rs.close();
	 * DBUtil.close(); return mt; }
	 */

	// 查询所有会议室
	public List<MeetingRoom> selectAllMeetingRooms() throws ClassNotFoundException, SQLException {
		List<MeetingRoom> list = new ArrayList<MeetingRoom>();
		PreparedStatement ps = DBUtil.getConnection().prepareStatement("select * from meetingroom");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			MeetingRoom mt = new MeetingRoom();
			mt.setRoomid(Integer.parseInt(rs.getString("roomid")));
			mt.setRoomnum(Integer.parseInt(rs.getString("roomnum")));
			mt.setCapacity(Integer.parseInt(rs.getString("capacity")));
			mt.setRoomname(rs.getString("roomname"));
			mt.setStatus(rs.getString("status"));
			mt.setDescription(rs.getString("description"));
			list.add(mt);
		}
		ps.close();
		rs.close();
		DBUtil.close();

		return list;
	}

	// 根据ID更新会议室
	public void updateMeetingRoom(int roomid, int roomnum, String roomname1, int capacity, String status, String description1)
			throws ClassNotFoundException, SQLException {
		PreparedStatement ps = DBUtil.getConnection().prepareStatement(
				"update meetingroom set roomnum=?,roomname=?,capacity=?,status=?,description=? where roomid = '" + roomid + "'");
		ps.setInt(1, roomnum);
		ps.setString(2, roomname1);
		ps.setInt(3, capacity);
		ps.setString(4, status);
		ps.setString(5, description1);
		ps.executeUpdate();
		ps.close();
		DBUtil.close();
	}

	// 根据ID更新会议室状态
	public void updateMeetingRoomStatus(int roomid, String status) throws ClassNotFoundException, SQLException {
		PreparedStatement ps = DBUtil.getConnection().prepareStatement("update meetingroom set status = ? where roomid = ?");
		ps.setString(1, status);
		ps.executeUpdate();
		ps.close();
		DBUtil.close();
	}

	public static void main(String[] args) {
		MeetingRoomDao md = new MeetingRoomDao();
		// MeetingRoom meetingroom = new
		// MeetingRoom(5,3301,"第XXX会议室",120,"1","最小会议室???");
		// md.insert(meetingroom);
		// md.updateMeetingroom(meetingroom);
		// md.updateMeetingroomStatus(5, "100");
		try {
			System.out.println(md.selectByRoomid(6));
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<MeetingRoom> list = null;
		try {
			list = md.selectAllMeetingRooms();
		}
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (MeetingRoom room : list) {
			System.out.println(room);
		}

	}

	// 通过时间查询所有会议室
	public List<MeetingRoom> selectMeetingRoomsByTime(java.sql.Timestamp starttime, java.sql.Timestamp endtime)
			throws ClassNotFoundException, SQLException {
		List<MeetingRoom> list = new ArrayList<MeetingRoom>();
		String start = starttime.toString();
		String end = endtime.toString();
		MeetingRoom meetingroom = null;
		String sql = "select *  from meetingroom " + "where  meetingroom.roomid not in" + " (select roomid  from meeting "
				+ "where (starttime<'" + start + "' and endtime >'" + end + "')" + " or (starttime>'" + start + "' and starttime <'" + end
				+ "')" + "or(endtime>'" + start + "' and endtime <'" + end + "') and status='0')";

		PreparedStatement st = DBUtil.getConnection().prepareStatement(sql);
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			meetingroom = new MeetingRoom();
			meetingroom.setRoomid(Integer.parseInt(rs.getString("roomid")));
			meetingroom.setRoomnum(Integer.parseInt(rs.getString("roomnum")));
			meetingroom.setCapacity(Integer.parseInt(rs.getString("capacity")));
			meetingroom.setRoomname(rs.getString("roomname"));
			meetingroom.setStatus(rs.getString("status"));
			meetingroom.setDescription(rs.getString("description"));

			list.add(meetingroom);
		}
	 System.out.println("MeetingROOM "+list.size());return list;
}

}
