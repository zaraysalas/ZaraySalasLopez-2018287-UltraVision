package controllers_package.controllers_sections_package;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Encapsulations.*;
import controllers_package.controller_login_package.Controller_Menu;
import database_package.*;
import views_package.loginView_package.*;
import views_package.sectionsView_package.*;

public class Controller_Sections extends Sections_Attributes implements ItemListener, ActionListener, DocumentListener {
	
	private Menu menu;
	private Controller_Menu contmenu;
	private Database_Functions dbfunc;
	private Title title;
	private Customer customer;
	protected Sections_Attributes secattri;
	private Sections classection;
	private StaffEncapsulation staffcapsule;


	

	public Controller_Sections() {
		super();
		secattri = new Sections_Attributes();
		}

	@Override
	public void itemStateChanged(ItemEvent e) {
		classection = new Sections();
		if (e.getStateChange() == ItemEvent.SELECTED) {
			//If a Receipt type is selected..."
			if(e.getSource() == cReceiptType) {
				ReceiptType = classection.getcReceiptType();
				secattri = new Sections_Attributes();
				secattri.setReceiptType(ReceiptType);
				//...return error message if is ""Choose one"...
				if (ReceiptType == "Choose one") {
					new PopUp_Windows().tryAgain();
				} else if(ReceiptType == "RENT"){
					 referenceValue = classection.getfMembership();
					 secattri.setQuerySearchOneValue("TITLES", "CATEGORY", referenceValue, "CODE", "NAME", "STOCK_AVAILABLE");
					 if(referenceValue.equals("PR")) {
						 resultQuery = new Database_Functions().withPremium();
						 new Database_Functions().getTitleCodeList(resultQuery);
					 }else {
					 //System.out.println("Controller: "+ referenceValue);
					
						 resultQuery = new Database_Functions().receipt();
				 	new Database_Functions().getTitleCodeList(resultQuery);
					 }
					matchFound = secattri.getMatchFound();		
					if (matchFound) {
						new Sections().RentReceipt();
					}else {
						new PopUp_Windows().noData();
						new Sections().noData();
					}
					
					 
				}else {//ReceiptType == "PENALTY"
					
				}
				//Title_code will shoot title description
				
			}else if(e.getSource() == comboTitleCode){
				titleCode = classection.getcomboTitleCode();
				secattri.setQuerySearchOneValue("TITLES", "CODE", titleCode, "NAME", "PRICE", "STOCK_AVAILABLE");
				ResultSet resultQuery = new Database_Functions().receipt();
			 	new Database_Functions().getOneRow(resultQuery);
				matchFound = secattri.getMatchFound();		
				if (matchFound) {
					secattri.setTitleName(valueFound1);
					int price = Integer.parseInt(valueFound2);
					secattri.setPrice(price);
					int stockav = Integer.parseInt(valueFound3);
					secattri.setStockAvailable(stockav);
					new Sections().titleName();
					//if any other combobox is selected	
				}else {
					new PopUp_Windows().noData();
					new Sections().noData();
				}
				
			}else {
				selection = classection.getSelection();
				secattri = new Sections_Attributes();
				secattri.setSelection(selection);
				// If the user select "Choose One" will through an error message.
				selection = secattri.getSelection();
				
			if (selection == "Choose one") {
				new PopUp_Windows().tryAgain();
				// Otherwise it will run the database...
			} else {
				dbfunc = new Database_Functions();
				// ...looking for a list for the combobox beside the one with the
				//first search parameter.
				dbfunc.uniques(secattri);
				/*This is just a checking to see if I get any data
				cAfterSearch = secattri.getcAfterSearch();
				
				 int size = cAfterSearch.getItemCount();
				
				for (int i = 0; i < size; i++) {
					Object item = cAfterSearch.getItemAt(i);
					System.out.println("Item at " + i + " = " + item);
				}
							section = secattri.getSection();
							if(section == "TITLES") {
					System.out.println("Controller: " + section);
					title.cAfterSearch();
				}
				*/
				//classection = new Sections();
				classection.cAfterSearch();

			}
		}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		classection = new Sections();
		
		switch (e.getActionCommand()) {
		
		case "bMenu":
			
			staffcapsule = new StaffEncapsulation();
			
			contmenu = new Controller_Menu(staffcapsule);
			
			break;
			
		case "bSearch":
			
			selection = classection.getSelection();
			selection2=classection.getSelection2();
			//secattri = new Sections_Attributes();
			secattri.setSelection2(selection2);
			//If the user did not change the combobox, it will remind the user to choose an option
			if (selection == null && selection2 == null){
				new PopUp_Windows().empty();
			//If the user chooses an not acceptable option,
			//	a window will appear asking for another value
			}else if(selection.equals("Choose one")){
				new PopUp_Windows().tryAgain();
			}else {
				dbfunc.searchby();
				matchFound = secattri.getMatchFound();		
				if (matchFound) {
					tableNames = secattri.getTableNames();
					tableContent = secattri.getTableContent();
					classection.table(frameHeight);
				}else {
					new PopUp_Windows().noData();
				}
			}
			break;
		case "bSearchAll":
			dbfunc = new Database_Functions();
			dbfunc.searchAll();
			//secattri = new Sections_Attributes();
			matchFound = secattri.getMatchFound();
			if (matchFound) {
				tableNames = secattri.getTableNames();
				tableContent = secattri.getTableContent();
				//classection = new Sections();
				classection.table(frameHeight);

			}else {
				new PopUp_Windows().noData();
			}
			break;
			
		case "ADD_TITLE":
			name = classection.getfName();
			genre = classection.getComboGenre();
			format = classection.getComboFormat();
			category = classection.getComboCategory();

			if (name == null || name.isEmpty() || genre.isEmpty() || genre == null || format.isEmpty() || format == null
					|| category.isEmpty() || category == null || classection.getFrelease().isEmpty()
					|| classection.getFrelease() == null || classection.getFstock().isEmpty()
					|| classection.getFstock() == null) {
				new PopUp_Windows().empty();
			} else if (genre.equals("Choose one") || format.equals("Choose one") || category.equals("Choose one")) {
				new PopUp_Windows().tryAgain();
			} else if (!(classection.getFrelease()).matches("[0-9]+") || !(classection.getFstock()).matches("[0-9]+")) {
				new PopUp_Windows().notNumber();
			} else {
				release = Integer.parseInt(classection.getFrelease());
				stock = Integer.parseInt(classection.getFstock());
				if (1981 >= release || release >= 2021) {
					new PopUp_Windows().releaseOutRange();
				} else if (stock <= 0) {
					new PopUp_Windows().moreThanZero();
				} else {
					secattri.setAttriAdd(name, genre, release, format, category, stock);
					/*System.out.println("Controller: " + name + " , " + genre + " , " + release + " , " + format + " , "
							+ category + " , " + stock);*/
					dbfunc = new Database_Functions();
					insertDone = dbfunc.insertTitle();
					if (insertDone) {
						new PopUp_Windows().success();
					} else {
						new PopUp_Windows().tryAgain();
					}
				}
			}
		  break;
		case "ADD_CUSTOMER":
			firstName = classection.getfFirstName();
			lastName = classection.getfLastName();
			membershipLevel = classection.getcMemberLevel();

			if (firstName == null || firstName.isEmpty() || lastName.isEmpty() || lastName == null) {
				new PopUp_Windows().empty();
			} else if (membershipLevel.equals("Choose one")) {
				new PopUp_Windows().tryAgain();
			} else if (!(classection.getfCreditCard()).matches("[0-9]+")) {
				new PopUp_Windows().notNumber();
			} else if (!(classection.getfCreditCard()).matches("(\\d{4}[-. ]?){4}|\\d{4}[-. ]?\\d{6}[-. ]?\\d{5}")) {
				new PopUp_Windows().notCreditCard();
			} else {
				creditCard = Long.parseLong(classection.getfCreditCard());
				secattri.setAddCustomer(firstName, lastName, membershipLevel);
				//System.out.println("Controller: " + firstName + lastName + membershipLevel + creditCard);
				dbfunc = new Database_Functions();
				query = dbfunc.insertCustomer();
				insertDone = dbfunc.insert(query);
				if (insertDone) {
					new PopUp_Windows().success();
				} else {
					new PopUp_Windows().tryAgain();
				}
			}
			break;
		case "RECEIPT":
			secattri.setReceiptType(classection.getcReceiptType());
			secattri.setTodayDate(classection.getTodayDate());
			secattri.setDate(classection.getDate());
			secattri.setMembershipCard(classection.getfMembershipCard());
			secattri.setTotal(classection.getTotal());
			
			secattri.setReceiptNum(classection.getReceiptNum());
			secattri.setCodeTitle(classection.getcomboTitleCode());
			
			
			if(classection.getcReceiptType()== "RENT") {
				//Button RECEIPT will insert to RENTED_LIST_TABLE
				//INSERT INTO RENTED_LIST(MEMBERSHIP_CARD, CODE, DATE_RENTED, RETURNING_DATE)
				//VALUES ('1','1001', '10/04/2020', '12/04/2020')
				query = new Database_Functions().insertRentedList();
				insertDone = new Database_Functions().insert(query);
				if (insertDone) {
					//Button RECEIPT will update LOYALTY_CARD
					insertDone = new Database_Functions().updateloyaltyCard();
					if (insertDone) {
						JOptionPane.showMessageDialog(null,
							    "This customer have "+ secattri.getPoints() +" and  "+ secattri.getFreeRents() + " free rents available",
							    "Loyalty Card",
							    JOptionPane.INFORMATION_MESSAGE);
					}
					
				} else {
					new PopUp_Windows().duplicateRent();
					new Sections().noData();
				}
	
				//INSERT INTO LOYALTY_CARD(MEMBERSHIP_CARD, POINTS, FREE_RENT)
				//VALUES ('1','100', '1')
				
			}else if(classection.getcReceiptType()== "PENALTY"){
				//Receipt_type will displays a combo with titles overrented
				//Title_code will shoot penalty_cost
				//Button RECEIPT will update to RECEIPT_TABLE

				//Button RECEIPT will add to PENALTY_TABLE
			}else {
				//Button RECEIPT will insert to RECEIPT_TABLE for RENT and PENALTY
				query = new Database_Functions().insertReceiptTable();
				insertDone = new Database_Functions().insert(query);
				if (insertDone) {
					System.out.println("RECEIPT_ TABLE insert");
				}
			}
			
			break;	

		}
		
			
		}

	@Override
	public void insertUpdate(DocumentEvent e) {
		//Membership_card will shoot Receipt number
		//Checking that the Membership is a number
		 if (!Sections.getfMembershipCard().matches("[0-9]+")){
			 new PopUp_Windows().notNumber();
		 }else {
			 referenceValue = Sections.getfMembershipCard();
		//Membership_card will shoot Membership_Level
		 secattri.setQuerySearchOneValue("CUSTOMER", "MEMBERSHIP_CARD", referenceValue,"LEVEL_MEMBERSHIP", "FIRST_NAME", "LAST_NAME");
		 	
		 ResultSet resultQuery = new Database_Functions().receipt();
		 	//Customer Name and Membership Level
		 	new Database_Functions().getOneRow(resultQuery);
		 	matchFound = secattri.getMatchFound();
		 	//Set the next available number for the receipt
			consecutiveReceipt = (new Database_Functions().receiptNum ()+1);
			//System.out.println("Controller: "+ consecutiveReceipt);
			secattri.setConsecutiveReceipt(consecutiveReceipt);
			if (matchFound) {
				new Sections().fMembershipLevel();
			}else {
				new PopUp_Windows().noData();
				secattri.setValuesFound("", "", "");
				secattri.setConsecutiveReceipt(0);
				new Sections().fMembershipLevel();
			}
	}	
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		System.out.println("you run removeUpdate at DocumentListener");
		new Sections().noData();
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		System.out.println("you run changedUpdate at DocumentListener");
		
	}

		
	}