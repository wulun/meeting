package com.icss.meeting.vo;

public class MeetingParticipants {
	private int meetingid;    //�����ұ�ʶ
	private int employeeid;	  //Ա����ʶ
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
