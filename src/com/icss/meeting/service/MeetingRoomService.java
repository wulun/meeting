package com.icss.meeting.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.icss.meeting.dao.MeetingRoomDao;
import com.icss.meeting.vo.MeetingRoom;

public class MeetingRoomService {
	/**********
	 * ����ͳ
	 */
	//ͨ��ID��ѯ��������Ϣ
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
	 * Ҧ��
	 */
	private MeetingRoomDao md = new MeetingRoomDao();
	
	//����ID��ѯ������
	public List<MeetingRoom> viewAllMeetingRoomS() throws ClassNotFoundException, SQLException{
		return md.selectAllMeetingRooms();
	}
	
	//��ѯ���л�����
	public MeetingRoom viewAllMeetingRoom(int roomid) throws ClassNotFoundException, SQLException{
		return md.selectByRoomid(roomid);
	}
	
	//����ID���»�����
	public void addMeetingRoom(int roomnum, int capacity, String status,  String roomname1,
			String description1) throws ClassNotFoundException, SQLException{
		md.insert(roomnum,capacity,status,roomname1,description1);
	}
	
	//����ID���»�����״̬
	public void updateMeetingRoom(int roomid, int roomnum, String roomname1, int capacity, String status, String description1) throws ClassNotFoundException, SQLException{
		md.updateMeetingRoom(roomid,roomnum,roomname1,capacity,status,description1);
	}

	

	
}
