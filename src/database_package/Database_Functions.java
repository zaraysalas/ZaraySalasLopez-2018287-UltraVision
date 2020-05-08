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

	private String query1, query, columnName, type, tableName;
	private Object equalsto;
	private int updateQuery, freeRents, points, size, stock_available, newstock_available, insertStaff, price, penalty,
			amountColumn, amountrows, consecutive;
	private PreparedStatement stmt;
	private Connection con;
	private ResultSet resultQuery;
	private boolean matchFound, insertDone, loyaltyCardFound, loyaltyCardDone;
	private JComboBox<Object> cAfterSearch;
	private ResultSetMetaData mdResultQuery;
	private String[][] tableContent;
	private String[] tableNames;
	private Object[] sAfterSearch, titleCodeList, titleNameList;
	private Sections_Attributes secattri;
	private Controller_Sections controllersec;
	private String table, valueFound1, valueFound2, valueFound3;

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

		tableName = secattri.getSection();
		columnName = (String) secattri.getSelection();
		equalsto = secattri.getSelection2();
		query = "SELECT * FROM " + tableName + " WHERE " + columnName + " = '" + equalsto + "';";
		System.out.println(query);
		search();
	}

	public void searchAll() {
		query = "SELECT * FROM " + secattri.getSection() + ";";
		search();

	}

	public void searchbyJoin() {
		tableName = secattri.getSection();
		columnName = (String) secattri.getSelection();
		equalsto = secattri.getSelection2();
		try {
			con = getDatabaseConexion();
			if (tableName.equals("TITLES")) {
				query = "SELECT CATEGORY FROM " + tableName + " WHERE " + columnName + " = '" + equalsto + "';";
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
				// Execute the query
				resultQuery = stmt.executeQuery(query);
				matchFound = resultQuery.next();
				table = resultQuery.getString("CATEGORY");
				switch (table) {
				case "ML":
					table = "MUSIC";
					break;
				case "VL":
					table = "MOVIE";
					break;
				case "TV":
					table = "BOX_SET";
					break;
				}
				query = "SELECT " + tableName + ".*, " + table + ".* " + " FROM " + table + " INNER JOIN " + tableName
						+ " " + "ON " + tableName + ".CODE = " + table + ".CODE WHERE " + tableName + "." + columnName
						+ " = '" + equalsto + "' ;";
			} else if (tableName.equals("CUSTOMER")) {
				query = "SELECT " + tableName + ".*, LOYALTY_CARD.* " + " FROM LOYALTY_CARD INNER JOIN " + tableName
						+ " " + "ON " + tableName + ".MEMBERSHIP_CARD = LOYALTY_CARD.MEMBERSHIP_CARD WHERE " + tableName
						+ "." + columnName + " = '" + equalsto + "' ;";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// System.out.println(query);//Checking point

		search();

	}

	public void search() {
		try {
			con = getDatabaseConexion();
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

			try {
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
			} catch (Exception e) {
				secattri.setTableContent(null);
			}
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
			updateQuery = stmt.executeUpdate(query);
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

	// Get the list of codes when the membership level is PR
	public ResultSet withPremium() {
		con = getDatabaseConexion();
		query = "SELECT * FROM TITLES;";
		// System.out.println(query);
		try {
			stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
			// Execute the query
			resultQuery = stmt.executeQuery(query);
			matchFound = resultQuery.next();
			// System.out.println("Database with list of codes: "+matchFound);
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
		// System.out.println("receipt(): "+query);

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
			/*
			 * System.out.println(secattri.getvaluetoFound1() + ": " + valueFound1 + ", " +
			 * secattri.getvaluetoFound2() + ": " + valueFound2 + ", " +
			 * secattri.getvaluetoFound3() + ": " + valueFound3);
			 */
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
			// System.out.println(columnname);
			// Add each piece of information from the ResultSet in array
			for (int i = 0; i < size; i++) {
				titleCodeList[i] = resultQuery.getObject(columnname);
				// System.out.println(titleCodeList[i]);// to check if works
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
		if (secattri.getReceiptType().equals("RENT")) {
			query = "INSERT INTO RECEIPT(RECEIPT_TYPE, DATE_RENTED, RETURNING_DATE, MEMBERSHIP_CARD, TOTAL)"
					+ "VALUES ('" + secattri.getReceiptType() + "', '" + secattri.getTodayDate() + "', '"
					+ secattri.getDate() + "', '" + secattri.getMembershipCard() + "', '" + secattri.getTotal() + "');";
		} else if (secattri.getReceiptType().equals("PENALTY")) {
			query = "INSERT INTO RECEIPT(RECEIPT_TYPE, DATE_RENTED, RETURNING_DATE, MEMBERSHIP_CARD, TOTAL)"
					+ "VALUES ('" + secattri.getReceiptType() + "', '" + secattri.getDate() + "', '"
					+ secattri.getTodayDate() + "', '" + secattri.getMembershipCard() + "', '" + secattri.getTotal()
					+ "');";
		} else if (secattri.getReceiptType().equals("FREE RENT")) {
			query = "INSERT INTO RECEIPT(RECEIPT_TYPE, DATE_RENTED, RETURNING_DATE, MEMBERSHIP_CARD, TOTAL)"
					+ "VALUES ('" + secattri.getReceiptType() + "', '" + secattri.getTodayDate() + "', '"
					+ secattri.getDate() + "', '" + secattri.getMembershipCard() + "', '0');";
		}
		return query;
	}

	public String insertRentedList() {
		return query = "INSERT INTO RENTED_LIST(MEMBERSHIP_CARD, CODE, DATE_RENTED, RETURNING_DATE)" + "VALUES ('"
				+ secattri.getMembershipCard() + "', '" + secattri.getCodeTitle() + "', '" + secattri.getTodayDate()
				+ "', '" + secattri.getDate() + "');";

	}

	public boolean insert(String query) {
		System.out.println(query);
		this.query = query;
		insertDone = false;
		try {
			con = getDatabaseConexion();

			// System.out.println(query);
			stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
			// Execute the query
			int updateQuery = stmt.executeUpdate(query);
			// System.out.println(updateQuery);
			// if it was update successfully, "insertDone" is true.
			if (updateQuery != 0) {
				insertDone = true;
			}
			// System.out.println(insertDone);
			stmt.close();
			con.close();
		} catch (SQLException e) {
			insertDone = false;
		}
		return insertDone;
	}

	public boolean checkLoyaltyCard() {
		con = getDatabaseConexion();
		// Look for the loyalty card which belong to the current customer...
		query = "SELECT * FROM LOYALTY_CARD WHERE MEMBERSHIP_CARD = '" + secattri.getMembershipCard() + "';";
		// System.out.println("Query for finding LoyaltyCard: "+ query);//Checking point
		try {
			stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
			// Execute the query
			resultQuery = stmt.executeQuery(query);
			// if there is a LOYALTY CARD, "matchFound" is true.
			loyaltyCardFound = resultQuery.next();
			// System.out.println("Loyalty card found? "+loyaltyCardFound);//Checking point
			if (loyaltyCardFound) {
				// ... if LOYALTY CARD exist add 10 points
				secattri.setPoints(resultQuery.getInt(2));
				secattri.setFreeRents(resultQuery.getInt(3));
				// System.out.println("The LoyaltyCard " + secattri.getMembershipCard() + " has
				// " + resultQuery.getInt(2) + " points and " + resultQuery.getInt(3) +" free
				// rents.");
				// Checking point
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			secattri.setPoints(10);
			secattri.setFreeRents(0);
		}
		return loyaltyCardFound;
	}

	public boolean updateloyaltyCard(boolean loyaltyCardFound, int pointsEarn) {
		this.loyaltyCardFound = loyaltyCardFound;
		loyaltyCardDone = false;
		try {
			con = getDatabaseConexion();
			/*
			 * // Look for the loyalty card which belong to the current customer... query =
			 * "SELECT * FROM LOYALTY_CARD WHERE MEMBERSHIP_CARD = '" +
			 * secattri.getMembershipCard()+"';"; //System.out.println(query); stmt =
			 * con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE); // Execute
			 * the query resultQuery = stmt.executeQuery(query); // if there is a LOYALTY
			 * CARD, "matchFound" is true. matchFound = resultQuery.next();
			 * //System.out.println(matchFound);
			 * 
			 */
			if (loyaltyCardFound) {
				// ... if LOYALTY CARD exist add 10 points
				points = secattri.getPoints() + pointsEarn;
				freeRents = points / 100;
				query = "UPDATE LOYALTY_CARD SET POINTS = " + points + " , FREE_RENT = " + freeRents
						+ " WHERE MEMBERSHIP_CARD = " + secattri.getMembershipCard() + ";";
				// System.out.println(query);//Checking point
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
				// Execute the query
				int updateQuery = stmt.executeUpdate(query);
				// if at least one match is found, "matchFound" is true.
				// System.out.println(updateQuery);
				// if it was update successfully, "insertDone" is true.
				if (updateQuery != 0) {
					loyaltyCardDone = true;
					secattri.setPoints(points);
					secattri.setFreeRents(freeRents);
				}

			} else {
				// if there is not a loyalty card, create one with 10 points
				query = "INSERT INTO LOYALTY_CARD(MEMBERSHIP_CARD, POINTS, FREE_RENT) VALUES ('"
						+ secattri.getMembershipCard() + "', '10' , '0');";
				// System.out.println(query);
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
				// Execute the query
				int updateQuery = stmt.executeUpdate(query);
				// System.out.println(updateQuery);
				// if it was update successfully, "insertDone" is true.
				if (updateQuery != 0) {
					loyaltyCardDone = true;
					secattri.setPoints(10);
					secattri.setFreeRents(0);
				}
			}

			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loyaltyCardDone;
	}

	public void updateCustomer() {

	}

	public String returningDate() {
		String valueFound1 = null;
		con = getDatabaseConexion();

		query = "SELECT RETURNING_DATE FROM RENTED_LIST WHERE MEMBERSHIP_CARD = '" + secattri.getMembershipCard()
				+ "' and CODE = " + secattri.getCodeTitle() + ";";
		// System.out.println("returningDate(): "+query);
		try {
			stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
			// Execute the query
			resultQuery = stmt.executeQuery(query);

			// if at least one match is found, "matchFound" is true.
			matchFound = resultQuery.next();
			secattri.setMatchFound(matchFound);
			valueFound1 = resultQuery.getString("RETURNING_DATE");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return valueFound1;
	}

	public int updateStock() {
		con = getDatabaseConexion();
		try {
			query = "SELECT STOCK_AVAILABLE FROM TITLES WHERE CODE = " + secattri.getCodeTitle() + ";";
			// System.out.println(query); // checking
			stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
			// Execute the query
			resultQuery = stmt.executeQuery(query);
			resultQuery.next();
			stock_available = resultQuery.getInt(1);

			if (secattri.getReceiptType().equals("RENT") || secattri.getReceiptType().equals("FREE RENT")) {
				secattri.setStockAvailable(stock_available - 1);
			} else if (secattri.getReceiptType().equals("PENALTY")) {
				secattri.setStockAvailable(stock_available + 1);
			}

			query = "UPDATE TITLES SET STOCK_AVAILABLE = " + secattri.getStockAvailable() + " WHERE CODE = "
					+ secattri.getCodeTitle() + ";";
			// System.out.println("updateStock(): "+query);
			stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
			// Execute the query
			updateQuery = stmt.executeUpdate(query);

			// System.out.println("updateStock(): "+updateQuery);

			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updateQuery;
	}

	public int update() {
		con = getDatabaseConexion();
		try {
			query = "UPDATE CUSTOMER SET LEVEL_MEMBERSHIP = '"+secattri.getCategory()+"' WHERE MEMBERSHIP_CARD = "+secattri.getMembershipCard()+";";
			System.out.println(query); // checking point
			stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
			// Execute the query
			updateQuery = stmt.executeUpdate(query);

			// System.out.println("updateStock(): "+updateQuery);

			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updateQuery;
	}

	public int deleteRentedList() {
		con = getDatabaseConexion();
		try {
			query = "DELETE FROM RENTED_LIST WHERE MEMBERSHIP_CARD = " + secattri.getMembershipCard() + " AND CODE = "
					+ secattri.getCodeTitle() + ";";
			// System.out.println("updateStock(): "+ query);
			stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE);
			// Execute the query
			updateQuery = stmt.executeUpdate(query);

			System.out.println("updateStock(): " + updateQuery);

			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updateQuery;

	}
	/*
	 * public int delete() { con = getDatabaseConexion(); try { query =
	 * "DELETE FROM "+secattri.getTableName()+" WHERE "+secattri.getWhere()+" = " +
	 * secattri.getReferenceValue() +";"; System.out.println("updateStock(): "+
	 * query); stmt = con.prepareStatement(query,
	 * ResultSet.TYPE_SCROLL_INSENSITIVE); // Execute the query updateQuery =
	 * stmt.executeUpdate(query);
	 * 
	 * System.out.println("deleteTitle(): "+ updateQuery);
	 * 
	 * stmt.close(); con.close(); } catch (SQLException e) { e.printStackTrace(); }
	 * return updateQuery;
	 * 
	 * }
	 */
}