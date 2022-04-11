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
			printStatementPerson = mConnect.prepareStatement(sqlPerson); // ����statement���������ִ��SQL��䣡��
			resultPerson = printStatementPerson.executeQuery();
			System.out.println("��person:");
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
			System.out.println("������쳣:" + e.getMessage());
		} finally {
			try {
				printStatementPerson.close();
			} catch (SQLException e) {
				System.out.println("�ر�prepareStatement�쳣:" + e.getMessage());
			}
			try {
				resultPerson.close();
			} catch (SQLException e) {
				System.out.println("�ر�ResultSet�쳣:" + e.getMessage());
			}
		}

		String sqlUsers = "SELECT * FROM users";
		ResultSet resultUsers = null;
		PreparedStatement printStatementUsers = null;

		try {
			printStatementUsers = mConnect.prepareStatement(sqlUsers); // ����statement���������ִ��SQL��䣡��
			resultUsers = printStatementUsers.executeQuery();
			System.out.println("��users:");
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
			System.out.println("������쳣:" + e.getMessage());
		} finally {
			try {
				printStatementUsers.close();
			} catch (SQLException e) {
				System.out.println("�ر�prepareStatement�쳣:" + e.getMessage());
			}
			try {
				resultUsers.close();
			} catch (SQLException e) {
				System.out.println("�ر�ResultSet�쳣:" + e.getMessage());
			}
		}
	}

}

