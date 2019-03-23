package com.icss.meeting.service;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.icss.meeting.dao.MeetingDao;
import com.icss.meeting.dao.MeetingParticipantsDao;
import com.icss.meeting.vo.Department;
import com.icss.meeting.vo.Employee;
import com.icss.meeting.vo.Meeting;


public class MeetingService {
	
	/********
	 * 符传统
	 * 
	 * 
	 */
	//通过人员ID 查询 参加了什么会议
	////查询我预定的会议
	public ArrayList<Meeting> selectMyBookAllMeetingByReserIdService( int reserid){
		MeetingDao md=new MeetingDao();
		 ArrayList<Meeting> list=null;
		try {
			list=md.selectMyBookAllMeetingByReserIdDao(reserid);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	//通过meetingID查询  会议信息
	public  Meeting selectMeetingByIdService(int meetingid){
		MeetingDao md=new MeetingDao();
		Meeting m=null;
		try { 
			m=md.selectMeetingByIdDao(meetingid);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
		
	}
	//取消会议
	public void updateMeetingDao(int id){
		MeetingDao md=new MeetingDao();
		
		try {
			md.updateMeetingDao(id, "1",new Timestamp(System.currentTimeMillis()));
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	////查询我将要参加的会议
	public ArrayList<Meeting> selectMyMeetingInfoService(int employeeid){
		
		ArrayList<Meeting> mList =new ArrayList<Meeting>();
		MeetingDao md=new MeetingDao();
		try {
			mList=md.selectMyMeetingInfoDao(employeeid);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
		
	}
	
	/****************
	 * 刘益龙
	 */
	//添加部门
		public void AddDepartmentService(String departmentname){
			MeetingDao md = new MeetingDao();
			try {
				md.AddDepartmentDAO(departmentname);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//查询所有部门
		public ArrayList<Department> selectAllDepartmentService(){
			MeetingDao md = new MeetingDao();
			ArrayList<Department> list = new ArrayList<Department>();
			try {
				 list = md.selectAllDepartmentDAO();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return list;	
		}
		//删除部门
		public void deleteDepatementByIdService(int departmentid) throws SQLException{
			MeetingDao md = new MeetingDao();
				md.deleteDepatementByIdDAO(departmentid);
			}
		//注册审批 状态
		public ArrayList<Employee> selectEmployeeByStatusService(){
			MeetingDao md = new MeetingDao();
			ArrayList<Employee> list = new ArrayList<Employee>();
			try {
				list = md.selectEmployeeByStatusDAO();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
			
		}
		//审批
		public void updateStatusService(int employeeid,String status) {
			MeetingDao md = new MeetingDao();
			try {
				md.updateStatusDAO(employeeid, status);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//搜索
		public ArrayList<Employee> selectEmployeeByNUSService(String employeename,String username,String status,int current,int pagesize){
			MeetingDao md = new MeetingDao();
			ArrayList<Employee> list = new ArrayList<Employee>();
			try {
				list = md.selectEmployeeByNUSDAO(employeename, username, status, current, pagesize);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		//数据的总条数
		public int getCountService(String employeename,String username,String status){
			MeetingDao md = new MeetingDao();
			int num=0;
			try {
				num=md.getCountDAO( employeename,username,status);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return num;
		}
		//总页数
		public int getTotalService(int pagesize,String employeename,String username,String status){
			MeetingDao md = new MeetingDao();
			int total = 0; 
			try {
			 total = md.getTotalDAO(pagesize,employeename,username,status);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return total;
		}
	/*	//关闭账号
		public String updateStatusEmployeeService(String username){
			MeetingDao md = new MeetingDao();
			String sta=null;
			try {
			sta = md.updateStatusEmployeeDAO(username);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return sta;
		}*/
		
		/*******
		 * 姚辛
		 */	
		
		public void bookMeetingSec(Meeting meeting,List<Integer> employeeidList){
			MeetingDao md=new MeetingDao();
		    MeetingParticipantsDao parDao=new MeetingParticipantsDao();
			int meetingid = 0;
			/*try {
				meetingid = md.insertSec(meeting);
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			for(Integer employeeid:employeeidList){
				parDao.insert(meetingid, employeeid);
			}
		}
		
		

}
