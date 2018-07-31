package com.wy.reptile.mhxycbg.role;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;
import com.wy.reptile.common.connection.pool.DBUtil;
import com.wy.reptile.common.connection.pool.DruidPool;

public class Insert {

	private static LinkedBlockingQueue<RoleList> queue = new LinkedBlockingQueue<RoleList>();

	public static void roleList(String eid, int type) {
		queue.offer(new RoleList(eid, type));
	}

	private static class RoleList {
		String eid;
		int type;

		RoleList(String eid, int type) {
			this.eid = eid;
			this.type = type;
		}
	}

	static {
		new Thread(new Runnable() {
			public void run() {
				RoleList roleList ;
				try {
					while((roleList = queue.take())!=null){
						String sql =String.format("insert ignore into mhxy_cbg_list values (null,'%s',%d)", roleList.eid,roleList.type);
						insert(sql);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public static void insert(String sql) {
		Connection con = null;
		Statement stat = null;
		try {
			con = DruidPool.getConnection();
			stat = con.createStatement();
			stat.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeStatement(stat);
			DBUtil.closeConnection(con);
		}
	}
}
