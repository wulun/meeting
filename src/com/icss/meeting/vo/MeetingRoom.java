package com.icss.meeting.vo;

public class MeetingRoom { 
	private int roomid;    //会议室标识	
	private int roomnum;   //会议室门牌号
	private String roomname;  //会议室名
	private int capacity;   //会议室容量  人数
	private String status;   //0：启用  1：停用
	private String description;
	
	public int getRoomid() {
		return roomid;
	}
	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}
	public int getRoomnum() {
		return roomnum;
	}
	public void setRoomnum(int roomnum) {
		this.roomnum = roomnum;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "MeetingRoom [roomid=" + roomid + ", roomnum=" + roomnum + ", roomname=" + roomname + ", capacity=" + capacity + ", status="
				+ status + "]";
	}
	
	
}
