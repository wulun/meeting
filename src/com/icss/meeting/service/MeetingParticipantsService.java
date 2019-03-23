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

	//查询所有参加该会议的人
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
//	 查询某员工被要求参加，但是又取消的会议
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

	// 查询某员工最近七天参加的所有会议
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

