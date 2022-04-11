package execute;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import create_data.Create;
import list.person;
import list.user;

import java.sql.*;

import mysqlconnect.*;
import print.PrintDatabase;
import tool.adddatabase;
import tool.deletedatabase;
public class excute{
	public static Connection conn;
	//�������ݿ�����
	public static String createpersons = "CREATE TABLE person(" + "username varchar(10) not null,"
			+ "name varchar(20) not null primary key," + "age int(4)," + "teleno char(11)" + ")charset=gbk;";

	public static String createusers = "CREATE TABLE users(" + "username varchar(10) primary key,"
			+ "password varchar(20) not null" + ")charset=gbk;";
	public static String username;
	public static String password;
	public static String name;
	public static String teleno;
	public static Integer age;
	//�������ݿ�����
	static MysqlUtils Utils = new MysqlUtils();
	public static void step_1() throws Exception {
		//step1
		System.out.println("STEP---1:");
		System.out.println("------------------------------------------------");
		Create database = new Create(Utils);
		database.createTable(createpersons);
		database.createTable(createusers);
		System.out.println("������ɹ���");
		PrintDatabase.printTable(Utils);
		System.out.println("------------------------------------------------");
		System.out.println("\n");
	}
	public static void step_2() throws Exception {
		//step2
		System.out.println("STEP---2:");
		System.out.println("------------------------------------------------");
		adddatabase.additionuser(new user("ly","123456"), Utils);
		adddatabase.additionuser(new user("liming","345678"), Utils);
		adddatabase.additionuser(new user("test","11111"), Utils);
		adddatabase.additionuser(new user("test1","12345"), Utils);
		adddatabase.additionperson(new person("ly","����"), Utils);
		adddatabase.additionperson(new person("liming","����",25), Utils);
		adddatabase.additionperson(new person("test","�����û�",20,"13388449933"), Utils);
		PrintDatabase.printTable(Utils);
		System.out.println("------------------------------------------------");
		System.out.println("\n");
	}
	public static void step_3() throws Exception {
		//step3
		System.out.println("STEP---3:");
		System.out.println("------------------------------------------------");
		adddatabase.addOrModifyPerson(new person("ly","����"),Utils);
        adddatabase.addOrModifyPerson(new person("test2","�����û�2"),Utils);
        adddatabase.addOrModifyPerson(new person("test1","�����û�1",33),Utils);
        adddatabase.addOrModifyPerson(new person("test","����",23,"18877009966"),Utils);
        adddatabase.addOrModifyPerson(new person("admin","admin"),Utils);
        PrintDatabase.printTable(Utils);
        System.out.println("------------------------------------------------");
        System.out.println("\n");
	}
	public static void delete() throws Exception {
		deletedatabase.dropuser(Utils);
		deletedatabase.dropperson(Utils);
	}
	public static void step_4() throws Exception {
		//step4
		 //step4
        System.out.println("STEP---4:");
        System.out.println("------------------------------------------------");
        deletedatabase.DeluserByUsername("test%", Utils);
        deletedatabase.DelpersonByUsername("test%", Utils);
        PrintDatabase.printTable(Utils);
        System.out.println("------------------------------------------------");
	}
	public static void main(String[] args) throws Exception{
		System.out.println("ʵ�鿪ʼ��");
		int m=0;
		delete();
		Scanner sc = new Scanner(System.in);
		step_1();
		step_2();
		step_3();
		step_4();
		delete();
		Create database = new Create(Utils);
		database.createTable(createpersons);
		database.createTable(createusers);
		System.out.println("������ɹ���");
		while(m!=7) {
			System.out.println("���������ָ�");
			m=sc.nextInt();
			if(m==1) {
				System.out.println("����users��");
				System.out.print("username��");
				String user1=sc.next();
				System.out.print("password: ");
				String user2=sc.next();
				adddatabase.additionuser(new user(user1,user2),Utils);
				System.out.print("����users��ɹ�\n");
				PrintDatabase.printTable(Utils);
			}
			if(m==2) {
				System.out.println("����person��");
				System.out.print("username��");
				String user1=sc.next();
				System.out.print("name: ");
				String user2=sc.next();
				System.out.print("age: ");
				Integer age1=sc.nextInt();
				if (age1==-1) {
					age1=null;
				}
				System.out.print("teleno��");
				String teleno1=sc.next();
				if(teleno1=="null") {
					teleno1="";
				}
				if(age1==null && teleno1=="") {
					adddatabase.addOrModifyPerson(new person(user1,user2),Utils);
				}
				if(age1!=null&&teleno1=="") {
					adddatabase.addOrModifyPerson(new person(user1,user2,age1),Utils);
				}
				if(age1!=null && teleno1!=""){
				adddatabase.addOrModifyPerson(new person(user1,user2,age1,teleno1),Utils);
				}
				System.out.print("����person��ɹ�\n");
				PrintDatabase.printTable(Utils);
			}
			if(m==3) {
				System.out.println("ɾ����Ӧusername��user�����ݣ�");
				System.out.print("username��");
				String user1=sc.next();
				deletedatabase.DeluserByUsername(user1, Utils);
				System.out.printf("ɾ��%s�ɹ�\n",user1);
				PrintDatabase.printTable(Utils);
			}
			if(m==4) {
				System.out.println("ɾ����Ӧusername��person�����ݣ�");
				System.out.print("username��");
				String user1=sc.next();
				deletedatabase.DelpersonByUsername(user1, Utils);
				System.out.printf("ɾ��%s�ɹ�\n",user1);
				PrintDatabase.printTable(Utils);
			}
			if(m==5) {
				System.out.println("ɾ����Ӧname��person�����ݣ�");
				System.out.print("name��");
				String user1=sc.next();
				deletedatabase.DelpersonByname(user1, Utils);
				System.out.printf("ɾ��%s�ɹ�\n",user1);
				PrintDatabase.printTable(Utils);
			}
			if(m==6) {
				System.out.println("ɾ����Ӧteleno��person�����ݣ�");
				System.out.print("teleno��");
				String user1=sc.next();
				deletedatabase.DelpersonByteleno(user1, Utils);
				System.out.printf("ɾ��%s�ɹ�\n",user1);
				PrintDatabase.printTable(Utils);
			}
			if(m==7) {
				delete();
				System.out.println("�˳�\n");
				break;
			}		
		}
	}

}
