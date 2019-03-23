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
	 * ����ͳ
	 * 
	 * 
	 */
	//ͨ����ԱID ��ѯ �μ���ʲô����
	////��ѯ��Ԥ���Ļ���
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
	//ͨ��meetingID��ѯ  ������Ϣ
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
	//ȡ������
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
	////��ѯ�ҽ�Ҫ�μӵĻ���
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
	 * ������
	 */
	//��Ӳ���
		public void AddDepartmentService(String departmentname){
			MeetingDao md = new MeetingDao();
			try {
				md.AddDepartmentDAO(departmentname);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//��ѯ���в���
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
		//ɾ������
		public void deleteDepatementByIdService(int departmentid) throws SQLException{
			MeetingDao md = new MeetingDao();
				md.deleteDepatementByIdDAO(departmentid);
			}
		//ע������ ״̬
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
		//����
		public void updateStatusService(int employeeid,String status) {
			MeetingDao md = new MeetingDao();
			try {
				md.updateStatusDAO(employeeid, status);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//����
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
		//���ݵ�������
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
		//��ҳ��
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
	/*	//�ر��˺�
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
		 * Ҧ��
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
