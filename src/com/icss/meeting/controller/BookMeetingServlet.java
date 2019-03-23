package com.icss.meeting.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.meeting.dao.DepartmentDao;
import com.icss.meeting.dao.MeetingDao;
import com.icss.meeting.dao.MeetingRoomDao;
import com.icss.meeting.service.DepartmentService;
import com.icss.meeting.service.MeetingRoomService;
import com.icss.meeting.service.MeetingService;
import com.icss.meeting.vo.Department;
import com.icss.meeting.vo.Meeting;
import com.icss.meeting.vo.MeetingRoom;

/**
 * Servlet implementation class BookMeetingServlet
 */
@WebServlet("/BookMeetingServlet")
public class BookMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookMeetingServlet() {
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
		/*String code = request.getParameter("code");
			if(code!=null&&code.equals("preare")){
				MeetingRoomDao roomDao = new MeetingRoomDao();
				DepartmentDAO deptDao = new DepartmentDAO();
				List<MeetingRoom> roomList = null;
				try {
					roomList = roomDao.selectAllMeetingRooms();
				}
				catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("roomsList", roomList);
				for(MeetingRoom mr:roomList){
					System.out.println(mr);
				}
				List<Department> deptList = null;
				try {
					deptList = deptDao.selectAll();
				}
				catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(Department pt:deptList){
					System.out.println(pt);
				}
				request.setAttribute("deptsList", deptList);
				
				request.getRequestDispatcher("bookmeeting.jsp").forward(request, response);
			}*/
		
		String code=request.getParameter("code");
		if(code!=null&&code.equals("prepare")){
			MeetingRoomDao roomDao=new MeetingRoomDao();
			DepartmentDao deptDao=new DepartmentDao();
			
			List<MeetingRoom> roomsList = null;
			try {
				roomsList = roomDao.selectAllMeetingRooms();
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<Department> deptsList = null;
			try {
				deptsList = deptDao.selectAll();
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("roomsList", roomsList);
			request.setAttribute("deptsList", deptsList);
			
			request.getRequestDispatcher("bookmeeting.jsp").forward(request, response);
		}
		
		if(code!=null&&code.equals("book")){
		/*	  MeetingService meetingService=new MeetingService(); */
			  MeetingDao md=new MeetingDao();
			  String meetingname=request.getParameter("meetingname");
			  int roomid=Integer.parseInt(request.getParameter("roomid"));
			  byte[] a=meetingname.getBytes("ISO-8859-1");
			  String newmeetingname=new String(a,"UTF-8");
			  HttpSession session=request.getSession();
			  int reservationistid=(Integer) session.getAttribute("employeeid");
			  
			  int numofparticipants=Integer.parseInt(request.getParameter("numofparticipants"));
			  Timestamp starttime=Timestamp.valueOf(request.getParameter("starttime"));
			  Timestamp endtime=Timestamp.valueOf(request.getParameter("endtime"));
			  Date date =new Date();
			  Timestamp reservationtime=new Timestamp(date.getTime());
			  String status="0";
			  String description=request.getParameter("description");
			  Meeting meeting=new Meeting();
			  meeting.setMeetingname(newmeetingname);
			  meeting.setRoomid(roomid);
			  meeting.setReservationistid(reservationistid);
			  meeting.setNumberofparticipants(numofparticipants);
			  meeting.setStarttime(starttime.toString());
			  meeting.setEndtime(endtime.toString());
			  meeting.setReservationtime(reservationtime.toString());
		
			  meeting.setDescription(description);
			  meeting.setStatus(status);
			  String[] employeesid=request.getParameterValues("selSelectedEmployees");
			  List<Integer> employeesidList=new ArrayList<Integer>();
			  for(String s:employeesid){
				  employeesidList.add(Integer.parseInt(s));
			  }
			  try {
				md.insert(meeting);
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*  meetingService.bookMeetingSec(meeting, employeesidList);*/
			  request.getRequestDispatcher("ViewMyBookingServlet").forward(request, response);
		  }
		}

}
