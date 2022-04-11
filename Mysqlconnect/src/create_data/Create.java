package create_data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import execute.excute;
import mysqlconnect.MysqlUtils;

public class Create{
	public static Connection mConnect;

	public Create(MysqlUtils utils) {
		this.mConnect = utils.returncon();
	}
	
	public static void createTable(String sql) throws Exception {
		PreparedStatement createStatement = null;
		try {
			createStatement = mConnect.prepareStatement(sql);
			createStatement.execute();
			if (sql=="CREATE TABLE person(" + "username varchar(10) not null,"
			+ "name varchar(20) not null primary key," + "age int(4)," + "teleno char(11)" + ")charset=gbk;") {
				System.out.println("创建person表成功!");
			}
			else {
				
				System.out.println("创建users表成功!");
			}
		} catch (SQLException e) {
			if(e.getMessage()=="Table 'person' already exists" || e.getMessage()=="Table 'users' already exists") {
				excute.delete();
				createTable(sql);
			}
		} finally {
			try {
				createStatement.close();
			} catch (SQLException e) {
				System.out.println("关闭表异常:" + e.getMessage());
			}
		}
	}

}

