package com.icss.meeting.service;

import java.sql.SQLException;
import java.util.List;

import com.icss.meeting.dao.EmployeeDao;
import com.icss.meeting.vo.Employee;

public class EmployeeService {
	
	/*******************
	 * ����ͳ
	 * @param id
	 * @return
	 */
		//ͨ���û�����ѯ������Employee����
	 public Employee selectByIdService(int id){
		 EmployeeDao empdao=new EmployeeDao();
		 Employee emp=null;
		 try {
			emp=empdao.selectByIdDao(id);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	 }
	 
	 /**********
	  * ֣��־
	  */
	 
	 
	//����dao��
		private EmployeeDao dao = new EmployeeDao();
		
		private Employee loginedEmployee = new Employee();
		
		//�û��������������ȷ����¼ʧ�ܣ��û���������ȷ���ٿ�status��ֵ�����ҽ���status=1ʱ��¼�ɹ�
		//flag=3���û������벻��ȷ��flag=1����¼�ɹ���flag=0��ע���������������У�flag=2��ע��������ûͨ����
		public int login(String username,String pwd){
			int flag = 3;
			Employee emp = null;
			try {
				emp = dao.selectByNamePwd(username, pwd);
			}
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(emp!=null){
				
				loginedEmployee = emp;
				String status = emp.getStatus();
				if(status!=null&&status.equals("1")){
					flag = 1;
				}
				if(status!=null&&status.equals("0")){
					flag = 0;
				}
				if(status!=null&&status.equals("2")){
					flag = 2;
				}
			}
			return flag;
		}
		
		public Employee getLoginedEmployee(){
			return loginedEmployee;
		}
		
		public static void main(String[] args){
			EmployeeService service = new EmployeeService();
			int flag = service.login("wangmin", "1");
			System.out.println(flag);
		}
		// �޸� ֮ǰ�Ĳ�ѯ
		 public Employee updateSelectByNameService(String employeeName){
			 Employee emp=null;
			 try {
				  emp=  dao.updateSelectByName(employeeName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 return emp;
		 }
		//�޸�����
		 public void updatePassService(String employeename,String password) throws SQLException{
			 dao.updatePassDAO(employeename, password);
			 
		 }


		/*****************
		 * Ҧ��
		 */
	/*	Service�����DAO��
		private EmployeeDAO dao=new EmployeeDAO();
		
//		�����¼�ɹ����Employee����
		private Employee loginedEmployee;*/
//		����ҳ��	
		private int countOfPages;
//		�������м�¼����
		private int countOfEmployees;
//		����ÿһҳ��¼��
		private int pageSize=3;
		
/*//		��¼�߼�����DAO�еĲ�ѯ���������ݲ�ѯ���Ľ��������4�����õ�ֵ
//		0��������ˣ���¼ʧ�ܣ�1����¼�ɹ�  2�����δͨ������¼ʧ��  3���û�����������󣬵�¼ʧ��
		public int login(String username,String pwd) throws ClassNotFoundException, SQLException{
			int flag=3;
			Employee e=dao.selectByNamePwd(username, pwd);
			if(e!=null){
				loginedEmployee=e;
				String status=e.getStatus();
				if(status!=null&&status.equals("1")){
					flag=1;
				}
				
				if(status!=null&&status.equals("0")){
					flag=0;
				}
				
				if(status!=null&&status.equals("2")){
					flag=2;
				}
			}
			return flag;
		}
		*/
		
		
/*//		���ص�¼�ɹ����Ա������
		public Employee getLoginedEmployee(){
			return loginedEmployee;
		}*/
		
//		ע�Ṧ�ܣ�����˺������ڣ�ע��ʧ�ܣ�����0������ע��ɹ�������1
		public int regist(Employee employee) throws ClassNotFoundException, SQLException{
			int flag=0;
			Employee e=dao.selectByUsername(employee.getUsername());
			System.out.println(e);
			if(e==null){
				flag=1;
				dao.insert(employee);
			}
			return flag;
		}
		
//		��ѯ���м�¼����
		public List<Employee> searchEmployees(String employeename,String username,String status) throws ClassNotFoundException, SQLException{
			List<Employee> list=dao.selectEmployeesByNameStatus(employeename, username, status);
			countOfEmployees=list.size();
			return list;
		}
		
/*//		��ѯÿһҳ�����ݼ���
		public List<Employee> searchEmployeesOfOnePage(String employeename,String username,String status,int start,int end) throws ClassNotFoundException, SQLException{
			return dao.selectEmployeesOfOnePage(employeename, username, status, start, end);
		}*/
		
//		������ҳ��
		public int getCountOfPages(){
			countOfPages=(countOfEmployees%pageSize==0)?countOfEmployees/pageSize:countOfEmployees/pageSize+1;
			return this.countOfPages;
		}
		
//		�������м�¼����
		public int getCountOfEmployees(){
			return this.countOfEmployees;
		}
		
//		����ÿҳ�ļ�¼������Ĭ��Ϊ3
		public int getPageSize(){
			return this.pageSize;
		}
		
/*//		��д����������з���
		public static void main(String[] args) throws ClassNotFoundException, SQLException {
			EmployeeService service =new EmployeeService();
//			int flag=service.login("wangxh", "1");
//			System.out.println(flag);
//			System.out.println(service.getLoginedEmployee());
			
			System.out.println(service.regist(new Employee("������","huangml002","1",1,"13567898765","huangml@qq.com","0","2")));

		}*/
		
}
