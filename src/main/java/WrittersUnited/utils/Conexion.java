package WrittersUnited.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static Connection con;
	//Esto debe ir en un XML

	private static String server="jdbc:mysql://localhost:3307";
	private static String database="library";
	private static String username="root";
	private static String password="";
	
	public static void connectDB() {
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(server+"/"+database,username,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			con=null;
			e.printStackTrace();
		}
	}
	
	public static Connection getConexion() {
		if(con==null) {
			connectDB();
		}
		return con;
	}
}
