package tool;

import java.util.List;
import java.util.Map;

import list.person;
import mysqlconnect.MysqlUtils;

public class personOP {
		public static void additionperson(person p,MysqlUtils dbutil) throws Exception{
		 	String sql;
		 	if(p.getAge()==null || p.getTeleno()=="") {
		 		if(p.getAge()==null && p.getTeleno()=="") {
		 		 sql = "insert into person(username,name) values('"+p.getUsername()+"', '"+p.getName()+"')";
		 		 }
		 		else if(p.getTeleno()=="" && p.getAge()!=null){
		 		 sql=	"insert into person(username,name,age) values('"+p.getUsername()+"', '"+p.getName()+"', '"+p.getAge()+"')";
		 		}
		 		else {
		 			sql=	"insert into person(username,name,teleno) values('"+p.getUsername()+"', '"+p.getName()+"', '"+p.getTeleno()+"')";
		 		}
		 	}
		 	else {
		 		 sql=	"insert into person(username,name,age,teleno) values('"+p.getUsername()+"', '"+p.getName()+"','"+p.getAge()+"', '"+p.getTeleno()+"')";
		 	}
		 	
	        
	        Object[] obj = {p.getUsername(),p.getName(),p.getAge(),p.getTeleno()};
	        dbutil.executemysql(sql);
	    }
		
		 public static void addOrModifyPerson(person p,MysqlUtils dbutil) throws Exception{
		        String sql_1 = "select * from person where username=?";
		        List<Map<String,Object>> userlist,personlist;
		        personlist = dbutil.query(sql_1,p.getUsername());
		        if(personlist.isEmpty()){//person表中不存在该username
		            String sql_2 = "select * from users where username=?";
		            userlist = dbutil.query(sql_2,p.getUsername());
		            if(userlist.isEmpty()){//user表中也不存在该username,
		                String sql_3 = "insert into users(username,pass) values('"+p.getUsername()+"','"+"888888"+"')";
		                Object[] obj = {p.getUsername(), "888888"};//默认的passwaod为888888
		                dbutil.executemysql(sql_3,obj);
		            }
		            additionperson(p, dbutil);
		            /*
		            String sql_4 = "insert into person(username,name,age,teleno) values('"+p.getUsername()+"', '"+p.getName()+"', '"+p.getAge()+"', '"+p.getTeleno()+"')";
		            Object[] obj = {p.getUsername(),p.getName(),p.getAge(),p.getTeleno()};
		            dbutil.executemysql(sql_4,obj);*/
		        }
		        else{
		        	
		        	String sql_5 = "update person set name='"+p.getName()+"',age='"+p.getAge()+"',teleno='"+p.getTeleno()+"' where username='"+p.getUsername()+"'";
		        	if(p.getAge()==null || p.getTeleno()=="") {
				 		if(p.getAge()==null && p.getTeleno()=="") {
				 		 sql_5 ="update person set name='"+p.getName()+"' where username='"+p.getUsername()+"'";
				 		 }
				 		else if(p.getTeleno()=="" && p.getAge()!=null){
				 		 sql_5=	 "update person set name='"+p.getName()+"',age='"+p.getAge()+"' where username='"+p.getUsername()+"'";
				 		}
				 		else {
				 			sql_5= "update person set name='"+p.getName()+"',teleno='"+p.getTeleno()+"' where username='"+p.getUsername()+"'";
				 		}
				 	}
				 	else {
				 		 sql_5=	 "update person set name='"+p.getName()+"',age='"+p.getAge()+"',teleno='"+p.getTeleno()+"' where username='"+p.getUsername()+"'";
				 	}	
		           
		            Object[] obj = {p.getName(),p.getAge(),p.getTeleno(),p.getUsername()};
		            dbutil.executemysql(sql_5);
		        }
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
}
