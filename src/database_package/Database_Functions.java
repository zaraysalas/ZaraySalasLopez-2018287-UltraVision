package database_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JComboBox;

import Encapsulations.*;
import controllers_package.controllers_sections_package.Controller_Sections;

public class Database_Functions extends Database_Connection {

	private String query, columnName, type;
	private int stock_available, insertStaff, price, penalty, amountColumn, amountrows;
	private PreparedStatement stmt;
	private Connection con;
	private ResultSet resultQuery;
	private boolean matchFound, insertDone;
	private JComboBox<Object> cAfterSearch;
	private ResultSetMetaData mdResultQuery;
	private String[][] tableContent;
	private String[] tableNames;
	private Object[] sAfterSearch;
	private Sections_Attributes secattri;
	private Controller_Sections controllersec;
	
	public boolean insertStaff(StaffEncapsulation staffLogin) {
		insertDone = false;
		try {
			con = getDatabaseConexion();
			// Get the values which were input by the user in "Login".
			// get queries.
			query = "INSERT INTO STAFF(USERNAME, PASSWORD, TYPE_STAFF) VALUES ('" + staffLogin.getUsername() + "', '"
					+ staffLogin.getPassword() + "', 'SHOP_ASSISTANT');";
			stmt = con.prepareStatement(query);
			// Execute the query
			insertStaff = stmt.executeUpdate();
			if (insertStaff != 0) {
				insertDone = true;
			}
			// Close the statement and the connection
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
		}
		return insertDone;

	}
	
	public void uniques(Sections_Attributes secattri) {
		try {
			con = getDatabaseConexion();
			query = "SELECT DISTINCT " + secattri.getSelection() + " FROM " + Sections_Attributes.getSection() + ";";
			stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
			// Execute the query
			resultQuery = stmt.executeQuery(query);
			// Getting the amount of items gotten in the search
			int size = 0;
			while (resultQuery.next()) {
				size++;
			}
			// Setting an array for the data gotten in the search
			sAfterSearch = new Object[size];
			// Make the ResultSet starts again
			resultQuery.first();
			String columnname = (String) secattri.getSelection();
			// Add each piece of information from the ResultSet in array
			for (int i = 0; i < size; i++) {
				sAfterSearch[i] = resultQuery.getObject(columnname);
				// System.out.println(sAfterSearch[i]);//to check if works
				resultQuery.next();
			}
			// Encapsulate the array to be used in a combobox
			secattri = new Sections_Attributes();
			secattri.setsAfterSearch(sAfterSearch);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void searchby() {
		con = getDatabaseConexion();
		secattri = new Sections_Attributes();
		query = "SELECT * FROM " + Sections_Attributes.getSection() + " WHERE " + secattri.getSelection() + " = '" + secattri.getSelection2() + "';";
		search();
	}

	public void searchAll() {

		con = getDatabaseConexion();
		secattri = new Sections_Attributes();
		query = "SELECT * FROM " + Sections_Attributes.getSection()+";";
		System.out.println(query);
		search();
	}

	public void search() {
		secattri = new Sections_Attributes();
		try {
			stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
			// Execute the query
			resultQuery = stmt.executeQuery(query);
			// if at least one match is found, "matchFound" is true.
			matchFound = resultQuery.next();
			//Return the matchFound
			secattri.setMatchFound(matchFound);

			mdResultQuery = resultQuery.getMetaData();

			amountColumn = mdResultQuery.getColumnCount();
			// Go to the end of the table
			resultQuery.last();
			amountrows = resultQuery.getRow();

			// Get the column name
			tableNames = new String[amountColumn];
			for (int i = 1; i <= amountColumn; i++) {
				columnName = mdResultQuery.getColumnName(i);
				tableNames[i - 1] = columnName;
			}
			secattri.setTableNames(tableNames);
			// Return to the beginning of the table
			resultQuery.beforeFirst();
			resultQuery.next();

			// Get the actual table's content
			tableContent = new String[amountrows][amountColumn];
			for (int j = 0; j <= amountrows; j++) {
				for (int i = 1; i <= amountColumn; i++) {
					tableContent[j][i - 1] = resultQuery.getString(i);
				}

				if (resultQuery.next()) {
				} else {
					break;
				}
			}
			/*Sequence to check the data returned
			for (String a : tableNames) {
				System.out.print(a + " , ");
			}
			System.out.println();
			
			for (String[] a : tableContent) {
				for (String i : a) {
					System.out.print(i + " , ");
				}
				System.out.println("\n");
			}*/
			secattri.setTableContent(tableContent);
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public boolean insertTitle() {
		secattri = new Sections_Attributes();
		insertDone = false;
		try {
			con = getDatabaseConexion();
			price = 6;
			penalty = 1;
			stock_available = Sections_Attributes.getStock();
			
			query = "INSERT INTO "+ Sections_Attributes.getSection() +"(NAME, GENRE, RELEASE_YEAR, FORMAT, PRICE, PENALTY_COST, CATEGORY, STOCK, STOCK_AVAILABLE)"
					+ "VALUES ('" + Sections_Attributes.getName() + "', '" + Sections_Attributes.getGenre() + "', '"
					+ Sections_Attributes.getRelease() + "', '" + Sections_Attributes.getFormat() + "', '" + price
					+ "', '" + penalty + "','" + Sections_Attributes.getCategory() + "', '"
					+ Sections_Attributes.getStock() + "', '" + stock_available + "');";
			
			System.out.println(query);
			stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
			// Execute the query
			int updateQuery = stmt.executeUpdate(query);
			System.out.println(updateQuery);
			// if at leas one match is found, "matchFound" is true.
			if (updateQuery != 0) {
				insertDone = true;
			}
			System.out.println(insertDone);

			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertDone;
	}

}
