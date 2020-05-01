package views_package.sectionsView_package;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import Encapsulations.*;
import controllers_package.controller_login_package.*;
import controllers_package.controllers_sections_package.*;

public class Sections extends Sections_Attributes implements Sections_Interface {

	protected Sections_Attributes secattri;
	protected Controller_Sections controllersection;

	public Sections() {
		super();
		controllersection = new Controller_Sections();
		secattri = new Sections_Attributes();
		secattri.setFrameSize(200, 80, 330, 480);
		secattri.setFrameHeightTable(480);
		secattri.setP1height(330, 450);
		secattri.setAddButton("ADD_TITLE");
		secattri.setp1Add(330, 300, "ADD");
	}

	@Override
	public void FrameAttributes(int frameLeft, int frameUpper, int frameWidth, int frameHeight) {
		sectionFrame = new JFrame("Ultra-Vision    " + secattri.getSection());
		sectionFrame.setVisible(true);
		sectionFrame.setBounds(secattri.getFrameLeft(), secattri.getFrameUpper(), secattri.getFrameWidth(),
				secattri.getFrameHeight());
		// Activate this at the end
		// sectionFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		sectionFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		/*
		 * sectionFrame.addWindowListener(new WindowAdapter() {
		 * 
		 * @Override public void windowClosing(WindowEvent we) { String OptionButtons[]
		 * = { "Exit", "Stay" }; int PromptResult = JOptionPane.showOptionDialog(null,
		 * "Are you sure you want to exit \n Ultra-Vision System?", "EXIT SYSTEM",
		 * JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, OptionButtons,
		 * OptionButtons[1]); if (PromptResult == 0) { System.exit(0); } } });
		 */
		sectionFrame.getContentPane().setLayout(new BoxLayout(sectionFrame.getContentPane(), BoxLayout.X_AXIS));

	}

	@Override
	public void p1(int p1width, int p1height) {
		sectionFrame.add(p1 = new JPanel());
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p1.setPreferredSize(new Dimension(secattri.getP1width(), secattri.getP1height()));
		p1.setMaximumSize(new Dimension(secattri.getP1width(), secattri.getP1height()));
		bMenu = new JButton("MAIN MENU");

		bMenu.setActionCommand("bMenu");
		bMenu.addActionListener(controllersection);
		p1.add(bMenu);
		p1.add(p1search = new JPanel());
	}

	@Override
	public void Search() {
		p1search.setLayout(new BoxLayout(p1search, BoxLayout.Y_AXIS));
		p1search.setBorder(BorderFactory.createTitledBorder(getRaised(), "SEARCH", TitledBorder.LEFT,
				TitledBorder.DEFAULT_POSITION));
		p1search.setPreferredSize(new Dimension(330, 95));
		p1search.setMaximumSize(new Dimension(330, 95));
		p1search.add(p1searchSquares = new JPanel());
		p1searchSquares.setLayout(new BoxLayout(p1searchSquares, BoxLayout.X_AXIS));
		// Titles will be searched by any option in the ComboBox
		p1searchSquares.add(lsearchby = new JLabel("Search by:"));
		csearchby = new JComboBox<String>(searchby);
		csearchby.addItemListener((ItemListener) controllersection);

		p1searchSquares.add(Box.createRigidArea(new Dimension(10, 0)));
		p1searchSquares.add(csearchby);
		p1searchSquares.add(Box.createRigidArea(new Dimension(10, 0)));

		cAfterSearch = new JComboBox<Object>();
		p1searchSquares.add(cAfterSearch);
		// The combobox is set not visible until the user choose something from first
		// combobox
		model = (DefaultComboBoxModel<?>) cAfterSearch.getModel();
		cAfterSearch.setVisible(false);

		p1search.add(p1searchButtons = new JPanel());
		p1searchButtons.setLayout(new BoxLayout(p1searchButtons, BoxLayout.X_AXIS));

		bSearch = new JButton("SEARCH");
		bSearch.setActionCommand("bSearch");
		bSearch.addActionListener(controllersection);
		p1searchButtons.add(bSearch);
		p1searchButtons.add(Box.createRigidArea(new Dimension(10, 40)));

		bAll = new JButton("SHOW ALL");
		bAll.setActionCommand("bSearchAll");
		bAll.addActionListener(controllersection);
		p1searchButtons.add(bAll);

	}

	@Override
	public void AddTop(int p1Addwidth, int p1Addheight, String titleBorder) {

		p1.add(p1add = new JPanel());
		p1add.setLayout(new BoxLayout(p1add, BoxLayout.Y_AXIS));
		p1add.setPreferredSize(new Dimension(secattri.getP1Addwidth(), secattri.getP1Addheight()));
		p1add.setMaximumSize(new Dimension(secattri.getP1Addwidth(), secattri.getP1Addheight()));
		p1add.setBorder(BorderFactory.createTitledBorder(getRaised(), secattri.getTitleBorder(), TitledBorder.LEFT,
				TitledBorder.DEFAULT_POSITION));
		p1add.add(p1addlabels = new JPanel());
		p1addlabels.setLayout(new GridLayout(0, 2, 0, 8));
	}

	@Override
	public void table(int frameHeightTable) {
		sectionFrame.setBounds(200, 40, 800, secattri.getFrameHeightTable());
		// secattri = new Sections_Attributes();
		tableSearch = new JTable(secattri.getTableContent(), secattri.getTableNames());
		tableSearch.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		p2 = new JPanel();
		sectionFrame.add(p2);
		p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
		p2.add(Box.createRigidArea(new Dimension(0, 50)));
		p2.setBorder(BorderFactory.createTitledBorder(getRaised(), secattri.getSection() + " TABLE", TitledBorder.LEFT,
				TitledBorder.DEFAULT_POSITION));
		p2.setPreferredSize(new Dimension(450, 300));
		p2.setMaximumSize(new Dimension(450, 300));

		scroll = new JScrollPane(tableSearch);
		scroll.setViewportView(tableSearch);
		p2.add(scroll);

		Validation();

	}

	@Override
	public void Add() {
		p1addlabels.add(lName = new JLabel("NAME:"));
		p1addlabels.add(fName = new JTextField());

		p1addlabels.add(lGenre = new JLabel("GENRE:"));
		genreList = new String[] { "Choose one", "Drama", "Comedy", "Pop", "Romance", "Thriller", "Rock", "Soul" };
		comboGenre = new JComboBox<String>(genreList);
		p1addlabels.add(comboGenre);

		p1addlabels.add(lRelease = new JLabel("RELEASE YEAR:"));
		p1addlabels.add(fRelease = new JTextField());

		p1addlabels.add(lFormat = new JLabel("FORMAT"));
		formatList = new String[] { "Choose one", "DVD", "Blu-Ray", "CD" };
		comboFormat = new JComboBox<String>(formatList);
		p1addlabels.add(comboFormat);

		p1addlabels.add(lCategory = new JLabel("CATEGORY"));
		String[] category = { "Choose one", "MO", "MU", "LC", "BS" };
		comboCategory = new JComboBox<String>(category);
		p1addlabels.add(comboCategory);

		p1addlabels.add(lStock = new JLabel("STOCK:"));
		p1addlabels.add(fStock = new JTextField());
	}

	@Override
	public void AddButton(String AddButton) {
		p1add.add(p1addButtons = new JPanel());
		p1addButtons.add(Box.createRigidArea(new Dimension(0, 5)));
		p1addButtons.setLayout(new BoxLayout(p1addButtons, BoxLayout.Y_AXIS));
		p1addButtons.add(bAdd = new JButton(secattri.getAddButton()));
		bAdd.addActionListener((ActionListener) controllersection);
		bAdd.setActionCommand(secattri.getAddButton());
		p1add.add(Box.createRigidArea(new Dimension(0, 10)));
		sectionFrame.add(Box.createRigidArea(new Dimension(20, 0)));
	}

	public void AddMovies() {

	}

	@Override
	public void AddMusic() {
		// TODO Auto-generated method stub

	}

	@Override
	public void AddBoxSet() {
		// TODO Auto-generated method stub

	}

	@Override
	public void AddConcert() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Receipt() {
		p1addlabels.add(lName = new JLabel("MEMBERSHIP CARD:"));
		p1addlabels.add(fMembershipCard = new JTextField());
		fMembershipCard.getDocument().addDocumentListener((DocumentListener) controllersection);

		p1addlabels.add(lMembership = new JLabel("MEMBERSHIP'S LEVEL:"));
		p1addlabels.add(fMembership = new JTextField());
		fMembership.setEditable(false);

		p1addlabels.add(lCustomerName = new JLabel("CUSTOMER NAME:"));
		p1addlabels.add(fCustomerName = new JTextField());
		fCustomerName.setEditable(false);

		p1addlabels.add(lConsecutiveReceipt = new JLabel("RECEIPT NUMBER:"));
		p1addlabels.add(fConsecutiveReceipt = new JTextField());
		fConsecutiveReceipt.setEditable(false);

		p1addlabels.add(lReceiptType = new JLabel("RECEIPT TYPE:"));
		receiptTypeList = new String[] { "Choose one", "RENT", "PENALTY" };
		cReceiptType = new JComboBox<String>(receiptTypeList);
		p1addlabels.add(cReceiptType);
		cReceiptType.addItemListener((ItemListener) controllersection);

		p1addlabels.add(lDateToday = new JLabel("DATE:"));
		p1addlabels.add(fDateToday = new JTextField());
		fDateToday.setEditable(false);

		p1addlabels.add(lDate = new JLabel("RETURNING DATE"));
		lDate.setVisible(false);
		p1addlabels.add(fDate = new JTextField());
		fDate.setVisible(false);
		fDate.setEditable(false);

		p1addlabels.add(lTitleCode = new JLabel("TITLE CODE:"));
		comboTitleCode = new JComboBox<String>();
		p1addlabels.add(comboTitleCode);
		comboTitleCode.addItemListener((ItemListener) controllersection);
		// The combobox is set not visible until the user choose something from first
		// combobox
		model = (DefaultComboBoxModel<?>) comboTitleCode.getModel();
		comboTitleCode.setVisible(true);

		p1addlabels.add(lStockAvailable = new JLabel("STOCK AVAILABLE: "));
		lStockAvailable.setVisible(false);
		p1addlabels.add(fStockAvailable = new JTextField());
		fStockAvailable.setVisible(false);
		fStockAvailable.setEditable(false);

		p1add.add(p1addTitleName = new JPanel());
		p1addTitleName.setPreferredSize(new Dimension(330, 0));
		p1addTitleName
				.add(fTitle = new JTextField("TitleDescription TitleDescription TitleDescription TitleDescription"));
		fTitle.setPreferredSize(new Dimension(315, 30));
		fTitle.setMaximumSize(new Dimension(315, 60));
		fTitle.setEditable(false);
		fTitle.setVisible(true);

		p1addlabels.add(lPrice = new JLabel("PRICE PER DAY:"));
		p1addlabels.add(fPrice = new JTextField());
		fPrice.setEditable(false);

		p1addlabels.add(lTotal = new JLabel("TOTAL:"));
		p1addlabels.add(fTotal = new JTextField());
		fTotal.setEditable(false);
	}

	@Override
	public void LoyaltyCard() {
		p1.add(p1loyaltyCard = new JPanel());
		p1loyaltyCard.setPreferredSize(new Dimension(330, 200));
		p1loyaltyCard.setMaximumSize(new Dimension(330, 200));
		p1loyaltyCard.setBorder(BorderFactory.createTitledBorder(getRaised(), "LOYALTY CARD", TitledBorder.LEFT,
				TitledBorder.DEFAULT_POSITION));

		p1loyaltyCard.setLayout(new GridLayout(0, 2, 0, 8));
		p1loyaltyCard.add(lmemberNum = new JLabel("MEMBERSHIP #:"));
		p1loyaltyCard.add(lmemberNumber = new JLabel("bring here the number"));

		p1loyaltyCard.add(lPoint = new JLabel("POINTS:"));
		p1loyaltyCard.add(lAmountPoints = new JLabel("points here"));

		p1loyaltyCard.add(lFreeRents = new JLabel("FREE RENTS:"));
		p1loyaltyCard.add(lAmountFreeRents = new JLabel("amount of free rents here"));

		p1loyaltyCard.add(Box.createRigidArea(new Dimension(0, 10)));
	}

	@Override
	public void RentedList() {
		p1.add(p1rentedList = new JPanel());
		p1rentedList.setPreferredSize(new Dimension(330, 300));
		p1rentedList.setMaximumSize(new Dimension(330, 300));
		p1rentedList.setBorder(BorderFactory.createTitledBorder(getRaised(), "RENTED LIST", TitledBorder.LEFT,
				TitledBorder.DEFAULT_POSITION));
	}

	@Override
	public void Validation() {
		sectionFrame.validate();
		sectionFrame.repaint();
	}

//------------------SEARCH-----------------------------
	public String getfName() {
		name = fName.getText();
		return name;
	}

	@Override
	public String getSelection() {
		String selection = (String) csearchby.getSelectedItem();
		return selection;
	}

	@Override
	public void cAfterSearch() {
		// Make the combobox visible
		cAfterSearch.setVisible(true);
		// Removing all elements from combobox
		model.removeAllElements();
		// Getting the array of elements for the combobox
		sAfterSearch = secattri.getsAfterSearch();
		// adding the array to the combobox(model)
		for (Object item : sAfterSearch) {
			model.addElement(item);
		}
		// setting model with new data to the actual combobox
		cAfterSearch.setModel(model);
		Validation();
	}

	public Object getSelection2() {
		Object selection2 = cAfterSearch.getSelectedItem();
		return selection2;
	}

//--------------------ADD-------------------------------
	public String getComboGenre() {
		genre = (String) comboGenre.getSelectedItem();
		return genre;
	}

	public String getFrelease() {
		String release = fRelease.getText();
		return release;
	}

	public String getComboFormat() {
		format = (String) comboFormat.getSelectedItem();
		return format;
	}

	public String getComboCategory() {
		category = (String) comboCategory.getSelectedItem();
		return category;
	}

	public String getFstock() {
		String stock = fStock.getText();
		return stock;
	}

//-----------------CUSTOMER------------------------
	public String getfFirstName() {
		firstName = fFirstName.getText();
		return firstName;
	}

	public String getfLastName() {
		lastName = fLastName.getText();
		return lastName;
	}

	public String getcMemberLevel() {
		membershipLevel = (String) cMemberLevel.getSelectedItem();
		return membershipLevel;
	}

	public String getfCreditCard() {
		String stringcreditCard = fCreditCard.getText();
		return stringcreditCard;
	}
//-------------------RECEIPT--------------------------

	public static String getfMembershipCard() {
		MembershipCard = fMembershipCard.getText();
		return MembershipCard;
	}

	public void fMembershipLevel() {
		fMembership.setText(null);
		fMembership.setText(valueFound1);

		fCustomerName.setText(null);
		fCustomerName.setText(valueFound2 + " " + valueFound3);

		fConsecutiveReceipt.setText(null);
		consecutiveReceipt = secattri.getConsecutiveReceipt();
		fConsecutiveReceipt.setText("" + consecutiveReceipt);

		//Membership_card will shoot Date
		fDateToday.setText(today());

	}

	// get current date
	public static String today() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(cal.getTime());
	}

	//Receipt_type will shoot Returning_date label or Days_overrented label
	public String getcReceiptType() {
		ReceiptType = (String) cReceiptType.getSelectedItem();
		return ReceiptType;
	}

//---------------RENTING-----------------------------
	//If the user select RENT
	public void RentReceipt() {
		lDate.setVisible(true);
		fDate.setVisible(true);
		lTotal.setText("TOTAL (3DAYS RENT):");
		//Title_code will shoot price
		comboTitleCode();
		
	}

	public void comboTitleCode() {
		// Make the combobox visible
		comboTitleCode.setVisible(true);
		// Removing all elements from combobox
		model.removeAllElements();
		// Getting the array of elements for the combobox
		titleCodeList = secattri.getitleCodeList();
		// adding the array to the combobox(model)
		// model.addElement("Choose one");
		for (Object item : titleCodeList) {
			model.addElement(item);
		}
		// setting model with new data to the actual combobox
		comboTitleCode.setModel(model);
		Validation();
	}
	
	public void titleName() {
		lStockAvailable.setVisible(true);
		fStockAvailable.setVisible(true);
		fTitle.setText(secattri.getTitleName());
		fPrice.setText("" + secattri.getPrice());
		fStockAvailable.setText("" + secattri.getStockAvailable());
		// Adding 3 Days to the current date
		fDate.setText(returningDay());
		fTotal.setText(""+total());
	}

	public String getfMembership() {
		membershipLevel = fMembership.getText();
		return membershipLevel;
	}

	//Returning_Date: Date plus 3 days allowed to have a title rented
	// Adding 3 Days to the current date
	public static String returningDay() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		cal.add(Calendar.DAY_OF_MONTH, 3);
		return format.format(cal.getTime());
	}

	public Object getcomboTitleCode() {
		titleCode = comboTitleCode.getSelectedItem();
		return titleCode;
	}
	public Object getTotal() {
		total = fTotal.getText();
		return total;
	}

	public Object getReceiptNum() {
		receiptNum = fConsecutiveReceipt.getText();
		return receiptNum;
	}

	//------------------PENALTY--------------------------
	//If the option PENALTY is select...
	
	public void PenaltyReceipt() {
		lDate.setVisible(true);
		lDate.setText("DAYS OVERRENTED:");
		fDate.setVisible(true);
		fDate.setText("");
		lTotal.setText("TOTAL:");
		comboTitleCode();
	}
	public int total() {
		int total = (Integer.parseInt(fPrice.getText())*3) ;
		return total;
	}
	
	public void noData() {
		fMembershipCard.setText("");
		fMembership.setText("");
		fCustomerName.setText("");
		fConsecutiveReceipt.setText("");
		cReceiptType.setSelectedIndex(0);
		fDateToday.setText("");
		lDate.setVisible(false);
		fDate.setVisible(false);
		model.removeAllElements();
		lStockAvailable.setVisible(false);
		fStockAvailable.setVisible(false);
		fTitle.setText("");
		fPrice.setText("");
		lTotal.setText("TOTAL:");
		fTotal.setText("");
	}

	//Use this for RENT and PENALTY
	public Object getTodayDate() {
		todayDate =  fDateToday.getText();
		return todayDate;
	}
//Use this for RENT and PENALTY
	public Object getDate() {
		Date = fDate.getText();
		return Date;
	}


}
