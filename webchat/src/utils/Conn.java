package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
	private static Connection connection=null;
	static {
		 try {
			String Driver="com.mysql.jdbc.Driver";    //�����
			    Class.forName(Driver);
			    connection=DriverManager.getConnection(Config.DBConfig.url,Config.DBConfig.user,Config.DBConfig.psw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		return connection;
	}
}
