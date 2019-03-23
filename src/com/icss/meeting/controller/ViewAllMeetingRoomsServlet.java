package com.icss.meeting.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.meeting.service.MeetingRoomService;
import com.icss.meeting.vo.MeetingRoom;

/**
 * Servlet implementation class ViewAllMeetingRoomsServlet
 */
@WebServlet("/ViewAllMeetingRoomsServlet")
public class ViewAllMeetingRoomsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllMeetingRoomsServlet() {
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
		MeetingRoomService ms = new MeetingRoomService();
		List<MeetingRoom> list = null;
		try {
			list = ms.viewAllMeetingRoomS();
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("meetingroomsList",list);
		request.getRequestDispatcher("allmeetingrooms.jsp").forward(request,response);
	}

}