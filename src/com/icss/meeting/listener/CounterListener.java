package com.icss.meeting.listener;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.icss.meeting.dao.CounterDao;

public class CounterListener implements ServletContextListener{
//上下文对象销毁时自动调用
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//获得ServletContext 对象
		ServletContext ctxt = arg0.getServletContext();
		//获取前台记录的登录次数
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
//上下文对象创建时自动调用
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
