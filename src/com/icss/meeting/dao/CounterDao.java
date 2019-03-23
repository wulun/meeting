package com.icss.meeting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.icss.meeting.util.DBUtil;

public class CounterDao {
	
	//修改count
	public void update(int visitcount) throws SQLException{
		String sql="update counter set visitcount=?";
		PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(sql);
		pstmt.setInt(1, visitcount);
		pstmt.executeQuery();
		
		
	}
	//查询里面的count值
	public int select() throws SQLException{
		int visitcount = 0;
		
		String sql="select * from counter";
		PreparedStatement pstmt = DBUtil.getConnection().prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			visitcount = rs.getInt("visitcount");
		}
		rs.close();
		pstmt.close();
		return visitcount;
	}
	
		
}
