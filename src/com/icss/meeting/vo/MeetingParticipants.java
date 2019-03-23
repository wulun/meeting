package com.icss.meeting.vo;

public class MeetingParticipants {
	private int meetingid;    //会议室标识
	private int employeeid;	  //员工标识
	public int getMeetingid() {
		return meetingid;
	}
	public void setMeetingid(int meetingid) {
		this.meetingid = meetingid;
	}
	public int getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}
	@Override
	public String toString() {
		return "MeetingParticipants [meetingid=" + meetingid + ", employeeid=" + employeeid + "]";
	}
	

}
