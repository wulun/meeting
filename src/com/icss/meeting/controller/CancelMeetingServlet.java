package com.icss.meeting.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.meeting.service.MeetingService;

/**
 * Servlet implementation class CancelMeetingServlet
 */
@WebServlet("/CancelMeetingServlet")
public class CancelMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelMeetingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//取消会议
		request.setCharacterEncoding("UTF-8");
		MeetingService ms=new MeetingService();
		int meetingid=Integer.parseInt(request.getParameter("meetingid"));
		ms.updateMeetingDao(meetingid);
		request.getRequestDispatcher("ViewMyBookingServlet").forward(request, response);
	}

}
