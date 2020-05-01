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

	private String query1, query, columnName, type, tableName, equalsto;
	private int freeRents, points, size, stock_available, insertStaff, price, penalty, amountColumn, amountrows, consecutive;
	private PreparedStatement stmt;
	private Connection con;
	private ResultSet resultQuery;
	private boolean matchFound, insertDone;
	private JComboBox<Object> cAfterSearch;
	private ResultSetMetaData mdResultQuery;
	private String[][] tableContent;
	private String[] tableNames;
	private Object[] sAfterSearch, titleCodeList, titleNameList;
	private Sections_Attributes secattri;
	private Controller_Sections controllersec;
	private String valueFound1, valueFound2, valueFound3;

	public Database_Functions() {
		secattri = new Sections_Attributes();
	}

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
			query = "SELECT DISTINCT " + secattri.getSelection() + " FROM " + secattri.getSection() + ";";
			// System.out.println(query); //checking
			stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
			// Execute the query
			resultQuery = stmt.executeQuery(query);
			// Getting the amount of items gotten in the search
			size = 0;
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

		tableName = secattri.getSection();
		columnName = (String) secattri.getSelection();
		equalsto = (String) secattri.getSelection2();
		query = "SELECT * FROM " + tableName + " WHERE " + columnName + " = '" + equalsto + "';";
		//System.out.println(query);
		search();
	}

	public void searchAll() {
		con = getDatabaseConexion();
		query = "SELECT * FROM " + secattri.getSection() + ";";
		search();
	}

	public void search() {
		try {
			stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
			// Execute the query
			resultQuery = stmt.executeQuery(query);
			// if at least one match is found, "matchFound" is true.
			matchFound = resultQuery.next();
			// Return the matchFound
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
			/*
			 * Sequence to check the data returned for (String a : tableNames) {
			 * System.out.print(a + " , "); } System.out.println();
			 * 
			 * for (String[] a : tableContent) { for (String i : a) { System.out.print(i +
			 * " , "); } System.out.println("\n"); }
			 */
			secattri.setTableContent(tableContent);
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public boolean insertTitle() {
		// secattri = new Sections_Attributes();
		insertDone = false;
		try {
			con = getDatabaseConexion();
			price = 6;
			penalty = 1;
			stock_available = secattri.getStock();

			query = "INSERT INTO " + secattri.getSection()
					+ "(NAME, GENRE, RELEASE_YEAR, FORMAT, PRICE, PENALTY_COST, CATEGORY, STOCK, STOCK_AVAILABLE)"
					+ "VALUES ('" + secattri.getName() + "', '" + secattri.getGenre() + "', '" + secattri.getRelease()
					+ "', '" + secattri.getFormat() + "', '" + price + "', '" + penalty + "','" + secattri.getCategory()
					+ "', '" + secattri.getStock() + "', '" + stock_available + "');";

			// System.out.println(query);
			stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
			// Execute the query
			int updateQuery = stmt.executeUpdate(query);
			// System.out.println(updateQuery);
			// if at leas one match is found, "matchFound" is true.
			if (updateQuery != 0) {
				insertDone = true;
			}
			// System.out.println(insertDone);

			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertDone;
	}

	public String insertCustomer() {
		/*
		 * secattri = new Sections_Attributes(); insertDone = false; try { con =
		 * getDatabaseConexion();
		 */
		return query = "INSERT INTO " + secattri.getSection() + "(FIRST_NAME, LAST_NAME, LEVEL_MEMBERSHIP, CREDIT_CARD)"
				+ "VALUES ('" + secattri.getFirstName() + "', '" + secattri.getLastName() + "', '"
				+ secattri.getMembershipLevel() + "', '" + secattri.getCreditCard() + "');";
		/*
		 * System.out.println(query); stmt = con.prepareStatement(query,
		 * ResultSet.TYPE_SCROLL_INSENSITIVE); // Execute the query int updateQuery =
		 * stmt.executeUpdate(query); System.out.println(updateQuery); // if at least
		 * one match is found, "matchFound" is true. if (updateQuery != 0) { insertDone
		 * = true; } System.out.println(insertDone); stmt.close(); con.close(); } catch
		 * (SQLException e) { e.printStackTrace(); } return insertDone;
		 */
	}

	public ResultSet withPremium() {
		con = getDatabaseConexion();
		query = "SELECT * FROM TITLES;";
		//System.out.println(query);
			try {
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
				// Execute the query
				resultQuery = stmt.executeQuery(query);
				matchFound = resultQuery.next();
				secattri.setMatchFound(matchFound);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		return resultQuery;
	}
	public ResultSet receipt() {
		con = getDatabaseConexion();

		query = "SELECT * FROM " + secattri.getTableName() + " WHERE " + secattri.getColumnResult() + " = '"
				+ secattri.getReferenceValue() + "';";
		//System.out.println(query);

		try {
			stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);

			// Execute the query
			resultQuery = stmt.executeQuery(query);
			// if at least one match is found, "matchFound" is true.
			matchFound = resultQuery.next();
			secattri.setMatchFound(matchFound);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultQuery;
	}
//Get the Customer Name and Membership level
	public void getOneRow(ResultSet resultQuery) {
		this.resultQuery = resultQuery;
		try {
			valueFound1 = resultQuery.getString(secattri.getvaluetoFound1());
			valueFound2 = resultQuery.getString(secattri.getvaluetoFound2());
			valueFound3 = resultQuery.getString(secattri.getvaluetoFound3());
			/*System.out.println(secattri.getvaluetoFound1() + ": " + valueFound1 + ", " + secattri.getvaluetoFound2()
					+ ": " + valueFound2 + ", " + secattri.getvaluetoFound3() + ": " + valueFound3);*/
			// Set the values found
			secattri.setValuesFound(valueFound1, valueFound2, valueFound3);
			// stmt.close();
			// con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getTitleCodeList(ResultSet resultQuery) {
		this.resultQuery = resultQuery;
		try {
			size = 1;
			// Size of the table in rows
			while (resultQuery.next()) {
				size++;
			}

			// Setting an array for the data gotten in the search
			titleCodeList = new Object[size];
			// Make the ResultSet starts again
			resultQuery.first();
			String columnname = secattri.getvaluetoFound1();
			// Add each piece of information from the ResultSet in array
			for (int i = 0; i < size; i++) {
				titleCodeList[i] = resultQuery.getObject(columnname);
				//System.out.println(titleCodeList[i]);// to check if works
				resultQuery.next();
			}
			// Encapsulate the array to be used in a combobox
			secattri.setTitleCodeList(titleCodeList);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Checking in the table Receipt the consecutive number
	public int receiptNum() {
		con = getDatabaseConexion();
		// SELECT DISTINCT RECEIPT_NUM FROM RECEIPT;
		query = "SELECT DISTINCT RECEIPT_NUM FROM " + secattri.getSection() + ";";
		// System.out.println(query);//to check if works
		try {
			stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
			// Execute the query
			resultQuery = stmt.executeQuery(query);
			// Get the last item of the list of unique
			while (resultQuery.next()) {
				consecutive = resultQuery.getInt("RECEIPT_NUM");
				// System.out.println(consecutive);//to check if works
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
		}
		return consecutive;
	}

	// insert to RECEIPT_TABLE
	public String insertReceiptTable() {

		return query = "INSERT INTO RECEIPT(RECEIPT_TYPE, DATE_RENTED, RETURNING_DATE, MEMBERSHIP_CARD, TOTAL)"
				+ "VALUES ('" + secattri.getReceiptType() + "', '" + secattri.getTodayDate() + "', '"
				+ secattri.getDate() + "', '" + secattri.getMembershipCard() + "', '" + secattri.getTotal() + "');";
	}

	public String insertRentedList() {
		return query = "INSERT INTO RENTED_LIST(MEMBERSHIP_CARD, CODE, DATE_RENTED, RETURNING_DATE)" + "VALUES ('"
				+ secattri.getMembershipCard() + "', '" + secattri.getCodeTitle() + "', '" + secattri.getTodayDate()
				+ "', '" + secattri.getDate() + "');";

	}

	public boolean insert(String query) {
		this.query = query;
		insertDone = false;
		try {
			con = getDatabaseConexion();

			//System.out.println(query);
			stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
			// Execute the query
			int updateQuery = stmt.executeUpdate(query);
			//System.out.println(updateQuery);
			// if it was update successfully, "insertDone" is true.
			if (updateQuery != 0) {
				insertDone = true;
			}
			//System.out.println(insertDone);
			stmt.close();
			con.close();
		} catch (SQLException e) {
			insertDone = false;
		}
		return insertDone;
	}

	public boolean updateloyaltyCard() {
		// UPDATE LOYALTY_CARD SET POINTS = 110 WHERE MEMBERSHIP_CARD = 1;
		insertDone = false;
		try {
			con = getDatabaseConexion();
			query = "SELECT * FROM LOYALTY_CARD WHERE MEMBERSHIP_CARD = '" + secattri.getMembershipCard()+"';";
			//System.out.println(query);
			stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
			// Execute the query
			resultQuery = stmt.executeQuery(query);
			// if at least one match is found, "matchFound" is true.
			matchFound = resultQuery.next();
			//System.out.println(matchFound);
			if(matchFound) {
				points = resultQuery.getInt(2);
				points = points + 10;
				freeRents = resultQuery.getInt(3);
				query = "UPDATE LOYALTY_CARD SET POINTS = "+ points+ " "
						+ "WHERE MEMBERSHIP_CARD = "+ secattri.getMembershipCard() + ";";
				//System.out.println(query);
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
				// Execute the query
				int updateQuery = stmt.executeUpdate(query);
				// if at least one match is found, "matchFound" is true.
				//System.out.println(updateQuery);
				// if it was update successfully, "insertDone" is true.
				if (updateQuery != 0) {
					insertDone = true;
					secattri.setPoints(points);
					secattri.setFreeRents(freeRents);
				}
				
			}else {
				query = "INSERT INTO LOYALTY_CARD(MEMBERSHIP_CARD, POINTS, FREE_RENT) VALUES ('" 
			+ secattri.getMembershipCard() +"', '10' , '0');";
				//System.out.println(query);
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
				// Execute the query
				int updateQuery = stmt.executeUpdate(query);
				//System.out.println(updateQuery);
				// if it was update successfully, "insertDone" is true.
				if (updateQuery != 0) {
					insertDone = true;
					secattri.setPoints(10);
					secattri.setFreeRents(0);
				}
			}
			
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertDone;
	}

}