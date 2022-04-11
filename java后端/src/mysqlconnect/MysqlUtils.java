package mysqlconnect;

import java.io.InputStream;
import java.util.Properties;

import java.sql.*;

public class MysqlUtils {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	
	public static Statement getStatement() {
		try {
			InputStream is=MysqlUtils.class.getClassLoader().getResourceAsStream("db.properties");
			
			Properties p=new Properties();
			
			p.load(is);
			driver = p.getProperty("driver");
			url=p.getProperty("url");
			username=p.getProperty("username");
			password=p.getProperty("password");				
			Class.forName(driver);
			System.out.println("成功加载驱动!");
			Connection conn=DriverManager.getConnection(url, username, password);
			Statement stmt=conn.createStatement();
			System.out.println("成功连接数据库!");
			return stmt;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(Connection conn, Statement statement, ResultSet result) {
		try {
			if(result!=null) {
				result.close();
				result=null;
			}
			if(statement!=null) {
				statement.close();
				statement=null;
			}
			if(conn!=null) {
				conn.close();
				conn=null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
