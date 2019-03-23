package com.icss.meeting.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.meeting.service.MeetingRoomService;
import com.icss.meeting.service.MeetingService;
import com.icss.meeting.vo.Meeting;

/**
 * Servlet implementation class ViewMyBookingServlet
 */
@WebServlet("/ViewMyBookingServlet")
public class ViewMyBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewMyBookingServlet() {
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
		//��õ�ǰ�û���ID
		request.setCharacterEncoding("UTF-8");
		HttpSession session =request.getSession();
		int  reserid=(Integer)session.getAttribute("employeeid");  
		
	/*	int reserid=8; */ //���ﻹû��session �������һ��id����
		MeetingRoomService mrs=new MeetingRoomService();
		ArrayList<Meeting> mtList=new ArrayList<Meeting>();
		//��ѯ��Ԥ�������л���
		MeetingService ms=new MeetingService();
		mtList=ms.selectMyBookAllMeetingByReserIdService(reserid);    
		//��õ���ӦID��roomname
		ArrayList<String> stList=new ArrayList<String>();
		for(Meeting mt:mtList){
			 //��õ���ӦID��roomname  ͨ��ID��ѯ��һ��meeting����
			stList.add(mrs.selectByRoomidService(mt.getRoomid()).getRoomname());  
		}
		Map<Meeting,String> map=new HashMap<Meeting,String>();
		for(int i=0;i<mtList.size();i++){
			//�ѵõ���name����map�� 
			map.put(mtList.get(i),stList.get(i));
		}
		//�ѻ�ȡ����ֵ    ����ǰ̨
		request.setAttribute("map",map);
		request.getRequestDispatcher("mybookings.jsp").forward(request, response);
	}

}
