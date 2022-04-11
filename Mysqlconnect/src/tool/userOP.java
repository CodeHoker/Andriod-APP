package tool;

import list.user;
import mysqlconnect.MysqlUtils;

public class userOP {
	public static void additionuser(user u,MysqlUtils utils) {
		String sql = "insert into users(username,pass) values('"+u.getUsername()+"', '"+u.getPassword()+"')";
        Object[] obj = {u.getUsername(),u.getPassword()};
        utils.executemysql(sql);

	}
	public static void DeluserByUsername(String username,MysqlUtils util) throws Exception {
        String sql = "delete from users where username like '"+username+"'";
        util.executemysql(sql);
        }
	public static void dropuser(MysqlUtils util) throws Exception{
        String sql = "drop table users";
        util.executemysql(sql);
    }
}
