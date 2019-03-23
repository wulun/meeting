package com.icss.meeting.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.icss.meeting.dao.MeetingRoomDao;
import com.icss.meeting.vo.MeetingRoom;

public class MeetingRoomService {
	/**********
	 * 符传统
	 */
	//通过ID查询会议室信息
	public MeetingRoom  selectByRoomidService(int id){
		MeetingRoomDao mrd=new MeetingRoomDao();
		MeetingRoom mr =null;
		try {
			mr=mrd.selectByRoomid(id);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mr;
		
	}
	/******
	 * 姚辛
	 */
	private MeetingRoomDao md = new MeetingRoomDao();
	
	//根据ID查询会议室
	public List<MeetingRoom> viewAllMeetingRoomS() throws ClassNotFoundException, SQLException{
		return md.selectAllMeetingRooms();
	}
	
	//查询所有会议室
	public MeetingRoom viewAllMeetingRoom(int roomid) throws ClassNotFoundException, SQLException{
		return md.selectByRoomid(roomid);
	}
	
	//根据ID更新会议室
	public void addMeetingRoom(int roomnum, int capacity, String status,  String roomname1,
			String description1) throws ClassNotFoundException, SQLException{
		md.insert(roomnum,capacity,status,roomname1,description1);
	}
	
	//根据ID更新会议室状态
	public void updateMeetingRoom(int roomid, int roomnum, String roomname1, int capacity, String status, String description1) throws ClassNotFoundException, SQLException{
		md.updateMeetingRoom(roomid,roomnum,roomname1,capacity,status,description1);
	}

	

	
}
