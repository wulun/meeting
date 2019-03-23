package com.icss.meeting.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.icss.meeting.dao.MeetingParticipantsDao;
import com.icss.meeting.util.DBUtil;
import com.icss.meeting.vo.Employee;
import com.icss.meeting.vo.Meeting;

public class MeetingParticipantsService {

	//��ѯ���вμӸû������
	public ArrayList<Employee> selectAllEmployeeByMeetingIdService(int meetingid){
		MeetingParticipantsDao mpd=new MeetingParticipantsDao();
		ArrayList<Employee> eList=null;
		try {
			eList=mpd.selectAllEmployeeByMeetingIdDao(meetingid);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eList;
	}
//	 ��ѯĳԱ����Ҫ��μӣ�������ȡ���Ļ���
	 public ArrayList<Meeting> selectAllCanceledMeetingsService(int participantsid) {
		
		 ArrayList<Meeting> meetingslist=new ArrayList<Meeting>();
		 MeetingParticipantsDao mpd=new MeetingParticipantsDao();
		 try {
			 meetingslist=mpd.selectAllCanceledMeetingsDao(participantsid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return meetingslist;
	 }

	// ��ѯĳԱ���������μӵ����л���
	public ArrayList<Meeting> selectAllNewMeetingsService(int participantsid) {

		ArrayList<Meeting> meetingslist = new ArrayList<Meeting>();
		MeetingParticipantsDao mpd=new MeetingParticipantsDao();
		try {
			meetingslist=mpd.selectAllNewMeetingsDao(participantsid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return meetingslist;
	}
}

