package com.icss.meeting.service;

public class Counter {
	private int visitcount;

	public int getVisitcount() {
		return visitcount;
	}

	public void setVisitcount(int visitcount) {
		this.visitcount = visitcount;
	}

	@Override
	public String toString() {
		return "Counter [visitcount=" + visitcount + "]";
	}
	

}
