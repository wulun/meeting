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
 * Servlet implementation class UpdateMeetingRoomServlet
 */
@WebServlet("/UpdateMeetingRoomServlet")
public class UpdateMeetingRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMeetingRoomServlet() {
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
		int roomnum = Integer.parseInt(request.getParameter("roomnum"));
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		String roomname = request.getParameter("roomname");
		String status = request.getParameter("status");
		String description = request.getParameter("description");
		MeetingRoomService ms = new MeetingRoomService();
		byte[] a = roomname.getBytes("ISO-8859-1");
		String roomname1 = new String(a,"UTF-8");
		byte[] b = description.getBytes("ISO-8859-1");
		String description1 = new String(b,"UTF-8");
		//roomid,roomnum,roomname,capacity,status,description
		try {
			ms.updateMeetingRoom(roomid,roomnum,roomname1,capacity,status,description1);
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("ViewAllMeetingRoomsServlet").forward(request,response);
	}

}
