package com.icss.meeting.listener;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.icss.meeting.dao.CounterDao;

public class CounterListener implements ServletContextListener{
//�����Ķ�������ʱ�Զ�����
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//���ServletContext ����
		ServletContext ctxt = arg0.getServletContext();
		//��ȡǰ̨��¼�ĵ�¼����
		int visitcount = Integer.parseInt(ctxt.getAttribute("visitcount").toString());
		CounterDao dao = new CounterDao();
		try {
			dao.update(visitcount);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//�����Ķ��󴴽�ʱ�Զ�����
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		CounterDao dao = new CounterDao();
		int visitcount = 0;
		try {
			visitcount = dao.select();
		}
		catch (SQLException e) { 	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServletContext ctxt = arg0.getServletContext();
		ctxt.setAttribute("visitcount", visitcount);
		
	}
	

}
