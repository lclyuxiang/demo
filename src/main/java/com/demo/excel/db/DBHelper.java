package com.demo.excel.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.demo.excel.util.Constants;

public class DBHelper {
	private Connection conn = null;  
	private PreparedStatement pst = null;//执行动态SQL语句。通常通过PreparedStatement实例实现 
    
    public DBHelper(){
    	try {
    		//指定连接类型  
			Class.forName(Constants.DRIVER_NAME);
			//获取连接 
			conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void execute(final String sql,ExecuteStatemet exe) {
    	exe.executeSQL(sql);
    	close();
    }
    
    public PreparedStatement getPreparedStatement(String sql){
    	try {
			pst = conn.prepareStatement(sql);
			return pst;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public void close(){
    	try {
			if (pst != null && !pst.isClosed()) {
				pst.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
