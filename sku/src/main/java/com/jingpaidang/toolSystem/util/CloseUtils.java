package com.jingpaidang.toolSystem.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CloseUtils {

	private CloseUtils() {}
	
	
	
	public static void release(ResultSet rs, Statement stmt, Connection conn) {
		
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs = null;
		}
		
		if(stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	/*	try {
			if(rs!=null)
				rs.close();
		} finally {
			try {
				if(stmt!=null)
					stmt.close();
			} finally {
				if(conn!=null)
					conn.close(); 
			}
		}*/
	}
	
}
