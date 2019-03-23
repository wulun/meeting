package com.icss.meeting.service;

import java.sql.SQLException;
import java.util.List;

import com.icss.meeting.dao.EmployeeDao;
import com.icss.meeting.vo.Employee;

public class EmployeeService {
	
	/*******************
	 * 符传统
	 * @param id
	 * @return
	 */
		//通过用户名查询，返回Employee对象
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
	  * 郑鸿志
	  */
	 
	 
	//关联dao类
		private EmployeeDao dao = new EmployeeDao();
		
		private Employee loginedEmployee = new Employee();
		
		//用户名密码如果不正确，登录失败；用户名密码正确，再看status的值，当且仅当status=1时登录成功
		//flag=3：用户名密码不正确；flag=1：登录成功；flag=0：注册过，但正在审核中；flag=2：注册过，审核没通过。
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
		// 修改 之前的查询
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
		//修改密码
		 public void updatePassService(String employeename,String password) throws SQLException{
			 dao.updatePassDAO(employeename, password);
			 
		 }


		/*****************
		 * 姚辛
		 */
	/*	Service类关联DAO类
		private EmployeeDAO dao=new EmployeeDAO();
		
//		保存登录成功后的Employee对象
		private Employee loginedEmployee;*/
//		保存页数	
		private int countOfPages;
//		保存所有记录数量
		private int countOfEmployees;
//		保存每一页记录数
		private int pageSize=3;
		
/*//		登录逻辑调用DAO中的查询方法，根据查询到的结果，返回4个不用的值
//		0：正在审核，登录失败；1：登录成功  2：审核未通过，登录失败  3：用户名或密码错误，登录失败
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
		
		
/*//		返回登录成功后的员工对象
		public Employee getLoginedEmployee(){
			return loginedEmployee;
		}*/
		
//		注册功能，如果账号名存在，注册失败，返回0，否则注册成功，返回1
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
		
//		查询所有记录集合
		public List<Employee> searchEmployees(String employeename,String username,String status) throws ClassNotFoundException, SQLException{
			List<Employee> list=dao.selectEmployeesByNameStatus(employeename, username, status);
			countOfEmployees=list.size();
			return list;
		}
		
/*//		查询每一页的数据集合
		public List<Employee> searchEmployeesOfOnePage(String employeename,String username,String status,int start,int end) throws ClassNotFoundException, SQLException{
			return dao.selectEmployeesOfOnePage(employeename, username, status, start, end);
		}*/
		
//		返回总页数
		public int getCountOfPages(){
			countOfPages=(countOfEmployees%pageSize==0)?countOfEmployees/pageSize:countOfEmployees/pageSize+1;
			return this.countOfPages;
		}
		
//		返回所有记录条数
		public int getCountOfEmployees(){
			return this.countOfEmployees;
		}
		
//		返回每页的记录条数，默认为3
		public int getPageSize(){
			return this.pageSize;
		}
		
/*//		编写代码测试类中方法
		public static void main(String[] args) throws ClassNotFoundException, SQLException {
			EmployeeService service =new EmployeeService();
//			int flag=service.login("wangxh", "1");
//			System.out.println(flag);
//			System.out.println(service.getLoginedEmployee());
			
			System.out.println(service.regist(new Employee("黄美玲","huangml002","1",1,"13567898765","huangml@qq.com","0","2")));

		}*/
		
}
