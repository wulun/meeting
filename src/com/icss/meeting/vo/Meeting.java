package com.icss.meeting.vo;

public class Meeting {
	private int meetingid;   			
	private String meetingname;
	private int roomid;
	private int reservationistid;       //预定者标识
	private int numberofparticipants;   //参会人员个数
	private String starttime;
	private String endtime;
	private String reservationtime;    //预定时间
	private String canceledtime;       //取消时间
	private String description;   		//描述
	private String status;                //状态  
	public int getMeetingid() {
		return meetingid;
	}
	public void setMeetingid(int meetingid) {
		this.meetingid = meetingid;
	}
	public String getMeetingname() {
		return meetingname;
	}
	public void setMeetingname(String meetingname) {
		this.meetingname = meetingname;
	}
	public int getRoomid() {
		return roomid;
	}
	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}
	public int getReservationistid() {
		return reservationistid;
	}
	public void setReservationistid(int reservationistid) {
		this.reservationistid = reservationistid;
	}
	public int getNumberofparticipants() {
		return numberofparticipants;
	}
	public void setNumberofparticipants(int numberofparticipants) {
		this.numberofparticipants = numberofparticipants;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getReservationtime() {
		return reservationtime;
	}
	public void setReservationtime(String reservationtime) {
		this.reservationtime = reservationtime;
	}
	public String getCanceledtime() {
		return canceledtime;
	}
	public void setCanceledtime(String canceledtime) {
		this.canceledtime = canceledtime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Meeting [meetingid=" + meetingid + ", meetingname=" + meetingname + ", roomid=" + roomid + ", reservationistid="
				+ reservationistid + ", numberofparticipants=" + numberofparticipants + ", starttime=" + starttime + ", endtime=" + endtime
				+ ", reservationtime=" + reservationtime + ", canceledtime=" + canceledtime + ", description=" + description + ", status="
				+ status + "]";
	}
	

}
