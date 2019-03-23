package com.icss.meeting.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.meeting.service.MeetingRoomService;
import com.icss.meeting.vo.MeetingRoom;

/**
 * Servlet implementation class ViewOneMeetingRoomServlet
 */
@WebServlet("/ViewOneMeetingRoomServlet")
public class ViewOneMeetingRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewOneMeetingRoomServlet() {
        super();
        
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
		int roomid = Integer.parseInt(request.getParameter("roomid"));
		MeetingRoomService ms = new MeetingRoomService();
		MeetingRoom room = null;
		try {
			room = ms.viewAllMeetingRoom(roomid);
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("room",room);
		
		request.getRequestDispatcher("meetingroomdetail.jsp").forward(request,response);
	}

}