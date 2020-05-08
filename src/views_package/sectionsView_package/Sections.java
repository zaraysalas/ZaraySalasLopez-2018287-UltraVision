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
	protected Controller_Returning contReturn;

	public Sections() {
		super();
		controllersection = new Controller_Sections();
		contReturn = new Controller_Returning();
		secattri = new Sections_Attributes();
		
	}

	@Override
	public void FrameAttributes(int frameLeft, int frameUpper, int frameWidth, int frameHeight) {
		sectionFrame = new JFrame("Ultra-Vision    " + secattri.getSection());
		sectionFrame.setVisible(true);
		sectionFrame.setBounds(secattri.getFrameLeft(), secattri.getFrameUpper(), secattri.getFrameWidth(),
				secattri.getFrameHeight());
		// Activate this at the end
		sectionFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		sectionFrame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent we) {
				String OptionButtons[] = { "Exit", "Stay" };
				int PromptResult = JOptionPane.showOptionDialog(null,
						"Are you sure you want to exit \n Ultra-Vision System?", "EXIT SYSTEM",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, OptionButtons, OptionButtons[1]);
				if (PromptResult == 0) {
					System.exit(0);
				}
			}
		});

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
		p1.add(p1Menu = new JPanel());
		p1Menu.add(bMenu);
		p1Menu.add(p1search = new JPanel());
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
		model = (DefaultComboBoxModel) cAfterSearch.getModel();
		cAfterSearch.setVisible(false);

		p1search.add(p1searchButtons = new JPanel());
		p1searchButtons.setLayout(new BoxLayout(p1searchButtons, BoxLayout.X_AXIS));

		bSearch = new JButton("SEARCH");
		bSearch.setActionCommand("bSearch");
		bSearch.addActionListener(controllersection);
		p1searchButtons.add(bSearch);
		p1searchButtons.add(Box.createRigidArea(new Dimension(10, 40)));




	}
	@Override
	public void modifyButtons() {

	}

	@Override
	public void AddTop(int p1Addwidth, int p1Addheight, String titleBorder) {
		bNew.setVisible(false);
		if(secattri.getSection().equals("CUSTOMER")) {bUpdate.setVisible(false);}
		
		p1.setPreferredSize(new Dimension(secattri.getP1width(), secattri.getP1height()));
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
		String[] category = { "Choose one", "ML", "VL", "TV" };
		comboCategory = new JComboBox<String>(category);
		p1addlabels.add(comboCategory);

		p1addlabels.add(lStock = new JLabel("STOCK:"));
		p1addlabels.add(fStock = new JTextField());
	}
	@Override
	public void Update() {		
		p1addlabels.add(lMembershipCard = new JLabel("MEMBERSHIP CARD NUMBER:"));
		p1addlabels.add(fMembershipCard = new JTextField());
		
		p1addlabels.setPreferredSize(new Dimension(350, 70));
		p1addlabels.setMaximumSize(new Dimension(350, 70));
		p1addlabels.add(lCategory = new JLabel("CATEGORY:"));
		categoryList = new String[] { "Choose one", "PR", "ML", "VL", "TV"};
		comboCategory = new JComboBox<String>(categoryList);
		p1addlabels.add(comboCategory);

	}
	public String getCategoryUpdate() {
		category = (String) comboCategory.getSelectedItem();
		return category;
	}

	@Override
	public void AddCustomer() {
		p1addlabels.add(lFirstName = new JLabel("FIRST NAME:"));
		p1addlabels.add(fFirstName = new JTextField());

		p1addlabels.add(lLastName = new JLabel("LAST NAME:"));
		p1addlabels.add(fLastName = new JTextField());

		p1addlabels.add(lMembership = new JLabel("MEMBERSHIP'S LEVEL:"));
		memberLevelList = new String[] {"Choose one","MO", "MU", "LC", "BS"};
		cMemberLevel = new JComboBox<String>(memberLevelList);
		p1addlabels.add(cMemberLevel);
		
		p1addlabels.add(lCreditCard = new JLabel("CREDIT CARD:"));
		p1addlabels.add(fCreditCard = new JTextField());
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
	//------------TABLE----------------------
		@Override
		public void table(int frameLerftTable,int frameUpperTable,int frameWidthTableint, int frameHeightTable) {
			sectionFrame.setBounds(secattri.getFrameLeftTable(), secattri.getFrameUpperTable(), secattri.getFrameWidthTable(), secattri.getFrameHeightTable());

			
			tableSearchModel = new DefaultTableModel(secattri.getTableContent(), secattri.getTableNames());
			tableSearch = new JTable(tableSearchModel);
			tableSearchModel = (DefaultTableModel) tableSearch.getModel();
			tableSearch.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

			sectionFrame.add(p2 = new JPanel());
			p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
			p2.setPreferredSize(new Dimension(400, 330));
			p2.setMaximumSize(new Dimension(400, 300));
			p2.add(Box.createRigidArea(new Dimension(0, 50)));
			p2.setBorder(BorderFactory.createTitledBorder(getRaised(), secattri.getSection() + " TABLE", TitledBorder.LEFT,
					TitledBorder.DEFAULT_POSITION));

			scroll = new JScrollPane(tableSearch);
			scroll.setViewportView(tableSearch);
			p2.add(scroll);
			
			p2.add(p2TableButtons = new JPanel());
			p2TableButtons.setLayout(new BoxLayout(p2TableButtons, BoxLayout.X_AXIS));
			/* Data can be delete or modify dynamically because tables are connected 
			 * between them in the database (Parent tables)
			 * 		// Delete
			bDelete = new JButton("DELETE");
			bDelete.setActionCommand("DELETE");
			bDelete.addActionListener(controllersection);
			p2TableButtons.add(bDelete);
			*/	
			// Show all data
			bAll = new JButton("SHOW ALL");
			bAll.setActionCommand("SHOW ALL");
			bAll.addActionListener(controllersection);
			p2TableButtons.add(bAll);
			Validation();

		}
		@Override
		public void tableSearchModel() {	
			if (tableSearchModel.getRowCount() > 0) {
	            for (int i = tableSearchModel.getRowCount() - 1; i > -1; i--) {
	            	tableSearchModel.removeRow(i);
	            }
	        }
			tableSearchModel = new DefaultTableModel(secattri.getTableContent(), secattri.getTableNames());
			tableSearch.setModel(tableSearchModel);

		Validation();
		}



	@Override
	public void Validation() {
		sectionFrame.validate();
		sectionFrame.repaint();
	}

	// ------------------SEARCH-----------------------------
	@Override
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

	@Override
	public Object getSelection2() {
		Object selection2 = cAfterSearch.getSelectedItem();
		return selection2;
	}

	// --------------------ADD-------------------------------
	@Override
	public String getComboGenre() {
		genre = (String) comboGenre.getSelectedItem();
		return genre;
	}

	@Override
	public String getFrelease() {
		String release = fRelease.getText();
		return release;
	}

	@Override
	public String getComboFormat() {
		format = (String) comboFormat.getSelectedItem();
		return format;
	}

	public String getComboCategory() {
		category = (String) comboCategory.getSelectedItem();
		return category;
	}

	@Override
	public String getFstock() {
		String stock = fStock.getText();
		return stock;
	}

	// -----------------CUSTOMER------------------------
	@Override
	public String getfFirstName() {
		firstName = fFirstName.getText();
		return firstName;
	}

	@Override
	public String getfLastName() {
		lastName = fLastName.getText();
		return lastName;
	}

	@Override
	public String getcMemberLevel() {
		membershipLevel = (String) cMemberLevel.getSelectedItem();
		return membershipLevel;
	}

	@Override
	public String getfCreditCard() {
		String stringcreditCard = fCreditCard.getText();
		return stringcreditCard;
	}

	// -------------------RECEIPT--------------------------
	@Override
	public String getfMembershipCard() {
		MembershipCard = fMembershipCard.getText();
		return MembershipCard;
	}

	// Display and change label
	// Membership_card will trigger...
	@Override
	public void fMembershipLevel() {

		fMembership.setText(null);
		fMembership.setText(valueFound1);

		fCustomerName.setText(null);
		fCustomerName.setText(valueFound2 + " " + valueFound3);

		fConsecutiveReceipt.setText(null);
		consecutiveReceipt = secattri.getConsecutiveReceipt();
		fConsecutiveReceipt.setText("" + consecutiveReceipt);

		fDateToday.setText(today());
	}

	@Override
	// get current date
	public String today() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(cal.getTime());
	}

	@Override
	// Receipt_type will shoot Returning_date label or Days_overrented label
	public String getcReceiptType() {
		ReceiptType = (String) cReceiptType.getSelectedItem();
		return ReceiptType;
	}

	// ---------------RENTING-----------------------------
	// If the user select RENT
	@Override
	public void RentReceipt() {

		lDate.setVisible(true);
		lDate.setText("RETURNING DATE:");
		fDate.setVisible(true);
		lTotal.setText("TOTAL (3DAYS RENT):");
		lStockAvailable.setVisible(true);
		lStockAvailable.setText("STOCK AVAILABLE:");
		fStockAvailable.setVisible(true);
		fDate.setText(returningDay());
		lPrice.setText("PRICE PER DAY");
	}

//this is use in RENT and PENALTY
	@Override
	public void comboTitleCode() {
		// Make the combobox visible
		comboTitleCode.setVisible(true);
		// Removing all elements from combobox
		modelTitleCode.removeAllElements();
		// Getting the array of elements for the combobox
		titleCodeList = secattri.getitleCodeList();
		// adding the array to the combobox(modelTitleCode)
		// modelTitleCode.addElement("Choose one");
		for (Object item : titleCodeList) {
			modelTitleCode.addElement(item);
		}
		// setting model with new data to the actual combobox
		comboTitleCode.setModel(modelTitleCode);
		Validation();
	}

	@Override
	public void titleName() {

		fTitle.setText(secattri.getTitleName());
		fPrice.setText("" + secattri.getPrice());
		fStockAvailable.setText("" + secattri.getStockAvailable());

		fDate.setText((String) secattri.getDate());
		fTotal.setText("" + total);
	}

	@Override
	public String getfMembership() {
		membershipLevel = fMembership.getText();
		return membershipLevel;
	}

	// Returning_Date: Date plus 3 days allowed to have a title rented
	// Adding 3 Days to the current date
	@Override
	public String returningDay() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		cal.add(Calendar.DAY_OF_MONTH, 3);
		return format.format(cal.getTime());
	}

	@Override
	public Object getcomboTitleCode() {
		titleCode = comboTitleCode.getSelectedItem();
		return titleCode;
	}

	public Object getTotal() {
		total = fTotal.getText();
		return total;
	}

	@Override
	public Object getReceiptNum() {
		receiptNum = fConsecutiveReceipt.getText();
		return receiptNum;
	}

	// ------------------PENALTY--------------------------
	// If the option PENALTY is select...
	@Override
	public void PenaltyReceipt() {

		lDate.setVisible(true);
		lDate.setText("RETURNING DATE:");
		fDate.setVisible(true);
		fDate.setText("" + secattri.getDate());
		lPrice.setText("PENALTY COST");
		lStockAvailable.setVisible(true);
		lStockAvailable.setText("DAYS PASSED:");
		fStockAvailable.setVisible(true);
		fStockAvailable.setText("" + secattri.getDate());
		lTotal.setText("TOTAL:");
		fTotal.setText("" + total);
	}

	@Override
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
		modelTitleCode.removeAllElements();
		lStockAvailable.setVisible(false);
		fStockAvailable.setVisible(false);
		fTitle.setText("");
		lPrice.setText("PRICE");
		fPrice.setText("");
		lTotal.setText("TOTAL:");
		fTotal.setText("");
	}

	// Use this for RENT and PENALTY
	@Override
	public Object getTodayDate() {
		todayDate = fDateToday.getText();
		return todayDate;
	}

	// Use this for RENT and PENALTY
	@Override
	public Object getDate() {
		Date = fDate.getText();
		return Date;
	}

//--------------FREE RENT---------------------
	@Override
	public void freeRentReceipt() {
		lDate.setVisible(true);
		lDate.setText("RETURNING DATE:");
		fDate.setVisible(true);
		fTotal.setText("100");
		lTotal.setText("TOTAL IN POINTS");
		lStockAvailable.setVisible(true);
		lStockAvailable.setText("STOCK AVAILABLE:");
		fStockAvailable.setVisible(true);
		fDate.setText(returningDay());
		lPrice.setText("PRICE IN POINTS");
		fPrice.setText("100");
	}



//--------------RETURNING---------------------

}
