package MedicalServices;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Conexiune {
	static public Connection con;
	static public String databaseName = "proiect_max";
	static public String url = "jdbc:mysql://localhost:3306/" + databaseName;
	static public String username = "root";
	static public String password = "oqa4vo5wx";
	static public PreparedStatement preparedStatement;
	static public PreparedStatement preparedStatement2;
	static public PreparedStatement preparedStatement3;
	static public PreparedStatement preparedStatement4;
	static public ResultSet myRs;
	static public ResultSet myRs2;
	static public ResultSet myRs3;
	static public ResultSet myRs4;
	static public CallableStatement cstmt;
	static public CallableStatement cstmt2;
	public static void init() throws InstantiationException, IllegalAccessException{
		try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		catch (java.lang.ClassNotFoundException e)
		{
			System.err.println("ClassNotFoundException" + e);
		}
		try {
			con = DriverManager.getConnection(url, username, password);
			if (con != null)
			{
				System.out.println("Connection made succesfull");
			}
		}
		catch(Exception ex)
		{
			System.out.println("not connected to database");
		}

	}

}
