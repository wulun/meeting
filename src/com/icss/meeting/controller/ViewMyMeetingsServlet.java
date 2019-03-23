package com.icss.meeting.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.meeting.service.EmployeeService;

import com.icss.meeting.service.MeetingRoomService;
import com.icss.meeting.service.MeetingService;

import com.icss.meeting.vo.Meeting;
import com.sun.javafx.collections.MappingChange.Map;

/**
 * Servlet implementation class ViewMyMeetingsServlet
 */
@WebServlet("/ViewMyMeetingsServlet")
public class ViewMyMeetingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewMyMeetingsServlet() {
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
		HttpSession session = request.getSession();
		int id=(Integer) session.getAttribute("employeeid");
		
		//获得当前用户ID     因为还没有session 所以先自定义
//		int id=8;
		MeetingService ms=new MeetingService();
		MeetingRoomService mrs=new MeetingRoomService();
		//获取我将要参加的会议信息
		ArrayList<Meeting> mList=ms.selectMyMeetingInfoService(id);
		ArrayList<String[]> nameList=new ArrayList<String[]>();
		EmployeeService emps=new EmployeeService();
		//循环会议列表
		for(Meeting m :mList){
			//获取
			String empName=emps.selectByIdService(m.getReservationistid()).getEmployeename();
			String roomName=mrs.selectByRoomidService(m.getRoomid()).getRoomname();
			//把获取的数据放入一个数组中  然后放入集合中
			nameList.add(new String[]{empName,roomName});
		}
		HashMap<Meeting, String[]> map=new HashMap<Meeting,String[]>();
		
		for(int i=0;i<mList.size();i++){
			map.put(mList.get(i),nameList.get(i));
		}
		request.setAttribute("map",map);
		request.getRequestDispatcher("mymeetings.jsp").forward(request, response);
		
		
	}

}
