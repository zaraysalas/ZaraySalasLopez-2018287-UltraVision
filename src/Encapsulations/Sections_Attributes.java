package Encapsulations;

import java.math.BigInteger;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class Sections_Attributes {
	protected static JTextArea message;
	protected static JPanel p, p1, p1search, p1searchSquares, p1searchButtons, p1add;
	protected static JPanel p1addTitleName,p2, p1addButtons, p1addlabels, p1loyaltyCard, p1rentedList;
	protected static JPanel p1searchSelection2;
	protected static JButton bMenu, bSearch, bAll, bAdd, breceipt;
	protected static JLabel lTotal, label1, lName, lGenre, lPrice;
	protected static JLabel lReceiptNum, lPenalty, lRelease, lFormat, lCategory, lStock;
	protected static JLabel lsearchby, lmemberNum, lmemberNumber, lPoint, lAmountPoints, lFreeRents, lAmountFreeRents;
	protected static JTextField fTotal, fPrice, fMembership, fTitle, fName, fGenre, fRelease, fFormat, fCategory, fStock, fReturningDate;

	protected static JFrame sectionFrame;
	protected static JScrollPane scroll;
	protected static int p1width, p1height;
		
		public int getP1width() {
			return p1width;
		}
		public int getP1height() {
			return p1height;
		}
		public void setP1height(int p1width, int p1height) {
			this.p1width = p1width;
			this.p1height = p1height;
		}
	protected static String AddButton;
		public void setAddButton(String AddButton) {
			this.AddButton = AddButton;
			
		}
		public String getAddButton() {
			return AddButton;
		}

	protected static JComboBox<String> csearchby, combo1, comboGenre, comboFormat, comboCategory, cMemberLevel;
		public static JComboBox<String> getCsearchby() {
			return csearchby;
		}
		public void setCsearchby(JComboBox<String> csearchby) {
			this.csearchby = csearchby;
		}
		public static JComboBox<String> getCombo1() {
			return combo1;
		}
		public void setCombo1(JComboBox<String> combo1) {
			this.combo1 = combo1;
		}
	
	protected static int amountrow,amountcolumn;
		public static int getAmountrow() {
			return amountrow;
		}
		public void setAmountrow(int amountrow) {
			this.amountrow = amountrow;
		}
		public static int getAmountcolumn() {
			return amountcolumn;
		}
		public void setAmountcolumn(int amountcolumn) {
			this.amountcolumn = amountcolumn;
		}

protected static Object selection, selection2;

		public Object getSelection() {
			return selection;
		}
		public void setSelection(Object selection) {
			this.selection = selection;
		}
		public Object getSelection2() {
			return selection2;
		}
		public void setSelection2(Object selection2) {
			this.selection2 = selection2;
			
		}

	protected static String[] formatList, genreList, memberLevelList;

		public static String[] getMemberLevelList() {
			return memberLevelList;
		}
		public void setMemeberLevel(String[] memberLevelList) {
			this.memberLevelList = memberLevelList;
		}



	protected static JTable tableSearch;
		public static JTable getTableSearch() {
			return tableSearch;
		}
		public void setTableSearch(JTable tableSearch) {
			this.tableSearch = tableSearch;
		}


//----------------------------------------------------	
	protected static Border raised = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		public static Border getRaised() {
			return raised;
		}
		public void setRaised(Border raised) {
			this.raised = raised;
		}
	
	protected static String[] searchby;
		public void setcontrollerdata(String[] searchby, String section) {
			this.section = section;
			this.searchby = searchby;
			
		}
	protected static String section;
		public String getSection() {
			return section;
		}
		public static String[] getSearchby() {
			return searchby;
		}
		
	protected static JComboBox<Object> cAfterSearch;
		public void setcAfterSearch(JComboBox<Object> cAfterSearch) {
			this.cAfterSearch = cAfterSearch;
			
		}
		public static JComboBox<Object> getcAfterSearch() {
			return cAfterSearch;
		}

	protected static Object[] sAfterSearch;
		public void setsAfterSearch(Object[] sAfterSearch) {
			this.sAfterSearch = sAfterSearch;
			
		}
		public static Object[] getsAfterSearch() {
			return sAfterSearch;
		}
		
	protected static DefaultComboBoxModel model;
	protected static DefaultTableModel tablemodel;
	
	
	protected static boolean matchFound;
	public void setMatchFound(boolean matchFound) {
		this.matchFound = matchFound;
		
	}
	public boolean getMatchFound() {
		return matchFound;
	}
	
protected static String[] tableNames;
	public String[] getTableNames() {
		return tableNames;
	}
	public void setTableNames(String[] tableNames) {
		this.tableNames = tableNames;
	}
	
protected static String[][] tableContent;
	public String[][] getTableContent() {
		return tableContent;
	}
	public void setTableContent(String[][] tableContent) {
		this.tableContent = tableContent;
	}

	protected static String name, genre, format, category;
	protected static int release, stock;
	
	public void setAttriAdd(String name, String genre, int release, String format, String category, int stock) {
		this.name = name;
		this.genre = genre;
		this.release= release;
		this.format = format;
		this.category = category;
		this.stock = stock;
	}
	public String getName() {
		return name;
	}
	public String getGenre() {
		return genre;
	}
	public String getFormat() {
		return format;
	}
	public String getCategory() {
		return category;
	}
	public int getRelease() {
		return release;
	}
	public int getStock() {
		return stock;
	}
	//--------------CUSTOMER--------------------------------------
	protected static boolean insertDone;
	protected static JLabel lMembership, lFirstName, lLastName, lCreditCard;
	protected static JTextField fFirstName, fLastName, fCreditCard;
	protected static String firstName, lastName, membershipLevel;
	protected static long creditCard;
	
		public String getLastName() {
			return lastName;
		}

		public String getFirstName() {
			return firstName;
		}

		public String getMembershipLevel() {
		return membershipLevel;
		}

		public long getCreditCard() {
			return creditCard;
		}
		public void setCreditCard(int creditCard) {
			this.creditCard = creditCard;
		}
		
		public void setAddCustomer(String firstName, String lastName, String membershipLevel) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.membershipLevel = membershipLevel;
		}
		
	protected static String titleBorder;
	protected static int frameHeightTable, p1Addwidth, p1Addheight, frameLeft, frameUpper, frameWidth, frameHeight;
		public void setp1Add(int p1Addwidth, int p1Addheight, String titleBorder) {
		this.p1Addwidth = p1Addwidth;
		this.p1Addheight = p1Addheight;
		this.titleBorder = titleBorder;
			}
		public String getTitleBorder() {
			return titleBorder;
		}
		public int getP1Addwidth() {
			return p1Addwidth;
		}
		public int getP1Addheight() {
			return p1Addheight;
		}
		public void setFrameSize(int frameLeft, int frameUpper, int frameWidth, int frameHeight) {
			this.frameLeft = frameLeft;
			this.frameUpper = frameUpper;
			this.frameWidth = frameWidth;
			this.frameHeight = frameHeight;			
		}
		public int getFrameLeft () {
			return frameLeft;
		}
		public int getFrameUpper() {
			return frameUpper;
		}
		public int getFrameWidth() {
			return frameWidth;
		}
		public int getFrameHeight() {
			return frameHeight;
		}
		public void setFrameHeightTable(int frameHeightTable) {
			this.frameHeightTable = frameHeightTable;
			
		}
		public int getFrameHeightTable() {
			return frameHeightTable;
		}
	//-------------RECEIPT----------------------------------------	
	protected static JLabel lStockAvailable, lReceiptType, lTitleCode, lDate, lDateToday, lCustomerName, lConsecutiveReceipt;
	protected static JTextField fStockAvailable, fDate, fDateToday, fMembershipCard, fCustomerName, fConsecutiveReceipt;
	protected static String query, titleName, valueFound1, valueFound2, valueFound3, ReceiptType, MembershipCard, tableName, valuetoFound1,valuetoFound2, valuetoFound3, columnResult;
	protected static int freeRents, points, stockAvailable, price, consecutiveReceipt;
	protected static Object receiptNum, total, Date, todayDate, titleCode, referenceValue, ObconsecutiveReceipt;
	protected static JComboBox<String> comboTitleCode, cReceiptType;
	protected static Object[] titleCodeList;
	protected static String[] receiptTypeList;
	protected static ResultSet resultQuery;
			
	public String getTableName() {
		// TODO Auto-generated method stub
		return tableName;
	}
	public String getvaluetoFound1() {
		return valuetoFound1;
	}
	public String getvaluetoFound2() {
		return valuetoFound2;
	}
	public String getvaluetoFound3() {
		return valuetoFound3;
	}
	public Object getReferenceValue() {
		return referenceValue;
	}

	public String getColumnResult() {
		return columnResult;
	}
	public void setValuesFound(String valueFound1, String valueFound2, String valueFound3) {
		this.valueFound1 = valueFound1;
		this.valueFound2 = valueFound2;
		this.valueFound3 = valueFound3;
	}
	public void setQuerySearchOneValue(String tableName, String columnResult, Object referenceValue, String valuetoFound1,
			String valuetoFound2, String valuetoFound3) {
		this.tableName = tableName;
		this.columnResult = columnResult;
		this.referenceValue = referenceValue;
		this.valuetoFound1 = valuetoFound1;
		this.valuetoFound2 = valuetoFound2;
		this.valuetoFound3 = valuetoFound3;
		
	}
	public Object getvalueFound1() {
		return valueFound1;
	}
	public void setConsecutiveReceipt(int consecutiveReceipt) {
		this.consecutiveReceipt = consecutiveReceipt;
		
	}
	public int getConsecutiveReceipt() {
		return consecutiveReceipt;
	}
	public Object[] getitleCodeList() {
		return titleCodeList;
	}
	public void setReceiptType(String receiptType) {
		this.ReceiptType = receiptType;
		
	}
	public String getReceiptType() {
		return ReceiptType;
	}
	public void setTitleCodeList(Object[] titleCodeList) {
	this.titleCodeList = titleCodeList;
		
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
		
	}
	public String getTitleName() {
		return titleName;
	}
	public int getPrice() {
		return price;
	}
	public int getStockAvailable() {
		return stockAvailable;
	}
	public void setPrice(int price) {
		this.price = price;
		
	}
	public void setStockAvailable(int stockAvailable) {
		this.stockAvailable = stockAvailable;
		
	}
	//Use this for RENT and PENALTY
	public void setTodayDate(Object todayDate) {
		this.todayDate = todayDate;
		
	}
	//Use this for RENT and PENALTY
	public Object getTodayDate() {
		return todayDate;
	}
	//Use this for RENT and PENALTY
	public void setDate(Object Date) {
		this.Date = Date;
	}
	//Use this for RENT and PENALTY
	public Object getDate() {
		// TODO Auto-generated method stub
		return Date;
	}
	public void setMembershipCard(String MembershipCard) {
		this.MembershipCard = MembershipCard;
		
	}
	public String getMembershipCard() {
		return MembershipCard;
	}
	public void setTotal(Object total) {
		this.total = total;
		
	}
	public Object getTotal() {
		return total;
	}
	public void setReceiptNum(Object receiptNum) {
		this.receiptNum = receiptNum;
		
	}
	public void setCodeTitle(Object titleCode) {
		this.titleCode = titleCode;
		
	}
	public Object getCodeTitle() {
		return titleCode;
	}
	public void setPoints(int points) {
	this.points = points;
		
	}
	public int getPoints() {
		// TODO Auto-generated method stub
		return points;
	}
	public void setFreeRents(int freeRents) {
		this.freeRents = freeRents;
		
	}
	public int getFreeRents() {
		// TODO Auto-generated method stub
		return freeRents;
	}
	public void setSection(String section) {
		this.section = section;
		
	}




	
}
