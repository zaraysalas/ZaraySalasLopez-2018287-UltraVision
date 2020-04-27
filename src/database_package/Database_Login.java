package database_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Encapsulations.*;

public class Database_Login extends Database_Connection{
	private boolean in;
	private String query;
	private PreparedStatement stmt;
	private Connection con;
	private ResultSet rs;
	// "in" is false if the user is not in the database.
	// "in" is true if the connection is.
	public boolean login(StaffEncapsulation staffLogin) {

		in = false;
		//
		try {
			con = getDatabaseConexion();
			
			query = "SELECT * FROM STAFF WHERE USERNAME = '" + staffLogin.getUsername() + "' AND PASSWORD = '"
					+ staffLogin.getPassword() + "';";
			// Establish a connection to MySQL Workbench
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery(query);
			//If "rs" finds a match, "next" will make the cursor to move to the next row
			//what will make "in" = "true"
			in = rs.next();
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("SQL Exception:");

			// Loop through the SQL Exceptions
			while (e != null) {
				System.out.println("State  : " + e.getSQLState());
				System.out.println("Message: " + e.getMessage());
				System.out.println("Error  : " + e.getErrorCode());

				e = e.getNextException();
			}

		} catch (Exception se) {
			System.out.println(se);
		}

		return in;
	}
	

}
