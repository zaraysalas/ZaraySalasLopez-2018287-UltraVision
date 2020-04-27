package database_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database_Connection {
	// Establish a conection with the program where the database is.
private Connection con;
private final String dbServer = "jdbc:mysql://localhost:3306/zaraysalas_ultravision";
private final String username = "root";
private final String password = "los3garcia";

public Connection getDatabaseConexion() {
		try {
			// Load the database driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(this.dbServer, this.username, this.password);
		} catch (SQLException e) {
			System.err.println(e);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Database_Connection.class.getName()).log(Level.SEVERE, null, ex);
		}
		return con;
	}
}
