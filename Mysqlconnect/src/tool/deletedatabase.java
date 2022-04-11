package tool;

import mysqlconnect.MysqlUtils;

public class deletedatabase {
	//°´ÕÕusernameÉ¾³ýÊý¾Ý
	 public static void DeluserByUsername(String username,MysqlUtils util) throws Exception {
        String sql = "delete from users where username like '"+username+"'";
        util.executemysql(sql);
        }
	 public static void DelpersonByUsername(String username,MysqlUtils util) throws Exception{
	        String sql = "delete from person where username like '"+username+"'";
	        util.executemysql(sql);
	    }
	 public static void DelpersonByname(String name,MysqlUtils util) throws Exception{
	        String sql = "delete from person where name like '"+name+"'";
	        util.executemysql(sql);
	    }
	 public static void DelpersonByteleno(String teleno,MysqlUtils util) throws Exception{
	        String sql = "delete from person where teleno like '"+teleno+"'";
	        util.executemysql(sql);
	    }
	 public static void dropperson(MysqlUtils util) throws Exception{
	        String sql = "drop table person";
	        util.executemysql(sql);
	    }
	 public static void dropuser(MysqlUtils util) throws Exception{
	        String sql = "drop table users";
	        util.executemysql(sql);
	    }

}
