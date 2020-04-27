package Encapsulations;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class Sections_Attributes {
	
	protected static JPanel p1, p1search, p1searchSquares, p1searchButtons, p1add;
	protected static JPanel p2, p1addButtons, p1addlabels, p1loyaltyCard, p1rentedList;
	protected static JPanel p1searchSelection2;
	protected static JButton bSearch, bAll, bAdd;
	protected static JLabel label1, lName, lGenre, lPrice;
	protected static JLabel lPenalty, lRelease, lFormat, lCategory, lStock;
	protected static JLabel lsearchby, lMembership, lmemberNum, lmemberNumber, lPoint, lAmountPoints, lFreeRents, lAmountFreeRents;
	protected static JTextField fName, fGenre, fRelease, fFormat, fCategory, fStock;
	protected static JFrame sectionFrame;
	protected static JScrollPane scroll;
	protected static JComboBox<String> csearchby, combo1, comboGenre, comboFormat, comboCategory, cMemberLevel;
		public static JComboBox<String> getCsearchby() {
			return csearchby;
		}
		public static void setCsearchby(JComboBox<String> csearchby) {
			Sections_Attributes.csearchby = csearchby;
		}
		public static JComboBox<String> getCombo1() {
			return combo1;
		}
		public static void setCombo1(JComboBox<String> combo1) {
			Sections_Attributes.combo1 = combo1;
		}
	
	protected static int amountrow,amountcolumn;
		public static int getAmountrow() {
			return amountrow;
		}
		public static void setAmountrow(int amountrow) {
			Sections_Attributes.amountrow = amountrow;
		}
		public static int getAmountcolumn() {
			return amountcolumn;
		}
		public static void setAmountcolumn(int amountcolumn) {
			Sections_Attributes.amountcolumn = amountcolumn;
		}

protected static Object selection, selection2;

		public Object getSelection() {
			return selection;
		}
		public void setSelection(Object selection) {
			Sections_Attributes.selection = selection;
		}
		public Object getSelection2() {
			return selection2;
		}
		public void setSelection2(Object selection2) {
			Sections_Attributes.selection2 = selection2;
			
		}

	protected static String[] memeberLevel;

		public static String[] getMemeberLevel() {
			return memeberLevel;
		}
		public static void setMemeberLevel(String[] memeberLevel) {
			Sections_Attributes.memeberLevel = memeberLevel;
		}



	protected static JTable tableSearch;
		public static JTable getTableSearch() {
			return tableSearch;
		}
		public static void setTableSearch(JTable tableSearch) {
			Sections_Attributes.tableSearch = tableSearch;
		}


//----------------------------------------------------	
	protected static Border raised = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		public static Border getRaised() {
			return raised;
		}
		public static void setRaised(Border raised) {
			Sections_Attributes.raised = raised;
		}
	
	protected static String[] searchby;
		public void setcontrollerdata(String[] searchby, String section) {
			Sections_Attributes.section = section;
			Sections_Attributes.searchby = searchby;
			
		}
	protected static String section;
		public static String getSection() {
			return section;
		}
		public static String[] getSearchby() {
			return searchby;
		}
		
	protected static JComboBox<Object> cAfterSearch;
		public void setcAfterSearch(JComboBox<Object> cAfterSearch) {
			Sections_Attributes.cAfterSearch = cAfterSearch;
			
		}
		public static JComboBox<Object> getcAfterSearch() {
			return cAfterSearch;
		}

	protected static Object[] sAfterSearch;
		public void setsAfterSearch(Object[] sAfterSearch) {
			Sections_Attributes.sAfterSearch = sAfterSearch;
			
		}
		public static Object[] getsAfterSearch() {
			return sAfterSearch;
		}
		
	protected static DefaultComboBoxModel model;
	protected static DefaultTableModel tablemodel;
	
	
	protected static boolean matchFound;
	public void setMatchFound(boolean matchFound) {
		Sections_Attributes.matchFound = matchFound;
		
	}
	public boolean getMatchFound() {
		return matchFound;
	}
	
protected static String[] tableNames;
	public String[] getTableNames() {
		return tableNames;
	}
	public void setTableNames(String[] tableNames) {
		Sections_Attributes.tableNames = tableNames;
	}
	
protected static String[][] tableContent;
	public String[][] getTableContent() {
		return tableContent;
	}
	public void setTableContent(String[][] tableContent) {
		Sections_Attributes.tableContent = tableContent;
	}

	protected static String name, genre, format, category;
	protected static int release, stock;
	public void setAttriAdd(String name, String genre, int release, String format, String category, int stock) {
		Sections_Attributes.name = name;
		Sections_Attributes.genre = genre;
		Sections_Attributes.release= release;
		Sections_Attributes.format = format;
		Sections_Attributes.category = category;
		Sections_Attributes.stock = stock;
	}
	public static String getName() {
		return name;
	}
	public static String getGenre() {
		return genre;
	}
	public static String getFormat() {
		return format;
	}
	public static String getCategory() {
		return category;
	}
	public static int getRelease() {
		return release;
	}
	public static int getStock() {
		return stock;
	}
	protected static boolean insertDone;
	
}
