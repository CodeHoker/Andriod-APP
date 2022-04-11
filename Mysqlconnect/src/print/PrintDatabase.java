package print;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mysqlconnect.MysqlUtils;

public class PrintDatabase {

	public static void printTable(MysqlUtils utils) {
		Connection mConnect=utils.returncon();
		String sqlPerson = "SELECT * FROM person";
		PreparedStatement printStatementPerson = null;
		ResultSet resultPerson = null;
		try {
			printStatementPerson = mConnect.prepareStatement(sqlPerson); // 创建statement类对象，用来执行SQL语句！！
			resultPerson = printStatementPerson.executeQuery();
			System.out.println("表person:");
	        System.out.println("+***********************************************************************+");
	        System.out.println("| username         name                age             teleno           |");
	        System.out.println("+-----------------------------------------------------------------------+");
			while (resultPerson.next()) {
				if (resultPerson.getString("username") != null) {
					System.out.printf("%s \t\t", resultPerson.getString("username"));
				}
				if (resultPerson.getString("name") != null) {
					System.out.printf("%s \t\t", resultPerson.getString("name"), "		");
				}
				if (resultPerson.getString("age") != null) {
					System.out.printf("%10s \t\t", resultPerson.getString("age"), "		");
				}
				if (resultPerson.getString("teleno") != null) {
					System.out.printf("%s \t\t", resultPerson.getString("teleno"), "		");
				}
				
				System.out.println();
				System.out.println("+-----------------------------------------------------------------------+");
			}
		} catch (SQLException e) {
			System.out.println("输出表异常:" + e.getMessage());
		} finally {
			try {
				printStatementPerson.close();
			} catch (SQLException e) {
				System.out.println("关闭prepareStatement异常:" + e.getMessage());
			}
			try {
				resultPerson.close();
			} catch (SQLException e) {
				System.out.println("关闭ResultSet异常:" + e.getMessage());
			}
		}

		String sqlUsers = "SELECT * FROM users";
		ResultSet resultUsers = null;
		PreparedStatement printStatementUsers = null;

		try {
			printStatementUsers = mConnect.prepareStatement(sqlUsers); // 创建statement类对象，用来执行SQL语句！！
			resultUsers = printStatementUsers.executeQuery();
			System.out.println("表users:");
	        System.out.println("+****************************************************+");
	        System.out.println("|username                   password                |");
	        System.out.println("+----------------------------------------------------+");
			while (resultUsers.next()) {
				if (resultUsers.getString("username") != null) {
					System.out.printf("%10s \t\t", resultUsers.getString("username"));
					
				}
				
				if (resultUsers.getString("pass") != null) {
					System.out.printf("%10s \t\t", resultUsers.getString("pass"), "		");
				}
				System.out.println();
				System.out.println("+----------------------------------------------------+");
			}
			System.out.println("\n");

		} catch (SQLException e) {
			System.out.println("输出表异常:" + e.getMessage());
		} finally {
			try {
				printStatementUsers.close();
			} catch (SQLException e) {
				System.out.println("关闭prepareStatement异常:" + e.getMessage());
			}
			try {
				resultUsers.close();
			} catch (SQLException e) {
				System.out.println("关闭ResultSet异常:" + e.getMessage());
			}
		}
	}

}

