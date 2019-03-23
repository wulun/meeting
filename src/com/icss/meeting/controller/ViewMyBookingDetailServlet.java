package com.icss.meeting.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.meeting.service.MeetingParticipantsService;
import com.icss.meeting.service.MeetingService;
import com.icss.meeting.vo.Employee;
import com.icss.meeting.vo.Meeting;


/**
 * Servlet implementation class ViewMyBookingDetailServlet
 */
@WebServlet("/ViewMyBookingDetailServlet")
public class ViewMyBookingDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewMyBookingDetailServlet() {
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
		request.setCharacterEncoding("UTF-8");
		//获得前台传过来的meetingid
		String mtid=request.getParameter("meetingid");
		int id=Integer.parseInt(mtid);
		MeetingService ms=new MeetingService();
		MeetingParticipantsService mps=new MeetingParticipantsService();
		//通过ID查询会议
		Meeting meeting=ms.selectMeetingByIdService(id);
		System.out.println(meeting.getMeetingid());
		List<Employee>  employeeList=mps.selectAllEmployeeByMeetingIdService(meeting.getMeetingid());
		//向前台传值
		request.setAttribute("meeting", meeting);
		request.setAttribute("employeesList", employeeList);
		request.getRequestDispatcher("mybookingdetail.jsp").forward(request, response);


		
		
		
		
	}

}
