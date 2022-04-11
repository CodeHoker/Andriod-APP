package mysqlconnect;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import java.sql.*;

public class MysqlUtils {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	private Connection con= null;
	public MysqlUtils() {
		con=getConnection();
	}
	public Connection returncon(){
		return con;
	}
	public static Connection getConnection() {
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
			return conn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public int executemysql(String sql,Object... params){
        int rlt = 0;
        try{
         
          Statement createStatement = con.createStatement();
           // putParams(pstmt,params);
            rlt = createStatement.executeUpdate(sql);
            createStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rlt;
    }
    //放置参数
    private void putParams(PreparedStatement pstmt, Object[] params) throws SQLException{
        if(params!=null){
            for(int i=0;i<params.length;++i){
                
                if(params[i] instanceof String) pstmt.setString(i+1,(String)params[i]);
                else if(params[i] instanceof Integer) pstmt.setInt(i+1,(Integer)params[i]);
                else if(params[i] == null) pstmt.setNull(i+1, Types.INTEGER);
            }
        }
    }
    //查询调用这个函数
    public List<Map<String,Object>> query(String sql,Object... params){
        PreparedStatement pstmt = null;
        List<Map<String,Object>> list = null;
        try{
            pstmt = con.prepareStatement(sql);
            putParams(pstmt,params);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            String[] keys = new String[rsmd.getColumnCount()];
            for(int i=1;i<=rsmd.getColumnCount();++i){
                keys[i-1] = rsmd.getColumnLabel(i);
            }
            list = new ArrayList<Map<String,Object>>();
            while(rs.next()){
                Map<String,Object> map = new HashMap<String,Object>();
                for(int i=0;i<keys.length;++i){
                    map.put(keys[i],rs.getObject(keys[i]));
                }
                list.add(map);
            }
            rs.close();
            pstmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
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
