package com.wy.reptile.common.connection.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DruidPool {
	private  static DataSource dataSource = null;
	static{
		
		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("url", "jdbc:mysql://localhost:3306/reptile?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
    	map.put("username", "root");
    	map.put("password", "123456");
    	map.put("filters", "stat");
    	map.put("validationQuery", "SELECT 1");
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
			dataSource =  DruidDataSourceFactory.createDataSource(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
}
