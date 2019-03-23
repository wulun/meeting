package com.icss.meeting.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.meeting.service.MeetingParticipantsService;
import com.icss.meeting.service.MeetingService;
import com.icss.meeting.vo.Employee;
import com.icss.meeting.vo.Meeting;

/**
 * Servlet implementation class MyNotificationServlet
 */
@WebServlet("/MyNotificationServlet")
public class MyNotificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyNotificationServlet() {
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
		//��ȡ������Ҫ�μ�������Ϣ
		//��ȡ��ǰsession����
		HttpSession session =request.getSession();
		int employeeid=(Integer)session.getAttribute("employeeid");
//		int id=8;
		MeetingParticipantsService mps=new MeetingParticipantsService();
		MeetingService ms=new MeetingService();
		ArrayList<Meeting> mList=mps.selectAllNewMeetingsService(employeeid);
		ArrayList<String> nameList=new ArrayList<String>();
		for(Meeting m:mList) {
			nameList.add(ms.selectMeetingByIdService(m.getMeetingid()).getMeetingname());
		}
		Map<Meeting,String> servenMap =new HashMap<Meeting,String>();
		for(int i=0;i<mList.size();i++) {
			servenMap.put(mList.get(i), nameList.get(i));
		}
		request.setAttribute("meetingsMap",servenMap );
		
		//��ȡҪ�μӵ�ȡ���˵Ļ���
		MeetingParticipantsService mpsSec=new MeetingParticipantsService();
		MeetingService msSec=new MeetingService();
		ArrayList<Meeting> mListSec=mpsSec.selectAllCanceledMeetingsService(employeeid);
		ArrayList<String> nameListSec=new ArrayList<String>();
		for(Meeting m:mListSec) {
			nameListSec.add(msSec.selectMeetingByIdService(m.getMeetingid()).getMeetingname());
		}
		Map<Meeting,String> map =new HashMap<Meeting,String>();
		for(int i=0;i<mListSec.size();i++) {
			map.put(mListSec.get(i), nameListSec.get(i));
		}
		request.setAttribute("cancelMeetingsMap", map);
		request.getRequestDispatcher("mynotification.jsp").forward(request, response);
	}

}
