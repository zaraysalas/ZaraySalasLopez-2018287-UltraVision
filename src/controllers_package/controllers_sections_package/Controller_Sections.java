package controllers_package.controllers_sections_package;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

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
		dbfunc = new Database_Functions();
	}

//If the user select an item from a combobox
	@Override
	public void itemStateChanged(ItemEvent e) {
		classection = new Sections();
		if (e.getStateChange() == ItemEvent.SELECTED) {

			// If a Receipt type is selected..."
			if (e.getSource() == cReceiptType) {
				ReceiptType = classection.getcReceiptType();
				secattri = new Sections_Attributes();
				secattri.setReceiptType(ReceiptType);
				// ...return error message if is ""Choose one"...
				if (ReceiptType == "Choose one") {
					new PopUp_Windows().tryAgain();
					classection.noData();
				} else if (ReceiptType == "RENT") {
					referenceValue = classection.getfMembership();

					secattri.setQuerySearchOneValue("TITLES", "CATEGORY", referenceValue, "CODE", "NAME",
							"STOCK_AVAILABLE");
					// if it is a PREMIUM=PR member, all the titles will be display
					if (referenceValue.equals("PR")) {
						resultQuery = new Database_Functions().withPremium();
						new Database_Functions().getTitleCodeList(resultQuery);
					} else {
						// System.out.println("Controller: "+ referenceValue);//Checking point

						resultQuery = new Database_Functions().receipt();
						new Database_Functions().getTitleCodeList(resultQuery);
					}
					classection.comboTitleCode();
					matchFound = secattri.getMatchFound();
					if (matchFound) {
						new Sections().RentReceipt();
					} else {
						new PopUp_Windows().noData();
						new Sections().noData();
					}
					// if PENALTY is selected.
				} else if (ReceiptType == "PENALTY") {

					classection.PenaltyReceipt();
					referenceValue = classection.getfMembershipCard();
					secattri.setQuerySearchOneValue("RENTED_LIST", "MEMBERSHIP_CARD", referenceValue, "CODE",
							"DATE_RENTED", "RETURNING_DATE");
					resultQuery = new Database_Functions().receipt();
					new Database_Functions().getTitleCodeList(resultQuery);
					classection.comboTitleCode();

				} else if (ReceiptType == "FREE RENT") {
					
					referenceValue = classection.getfMembership();
					System.out.println(referenceValue);
					//Select all from TITLES.
					secattri.setQuerySearchOneValue("TITLES", "CATEGORY", referenceValue, "CODE", "NAME",
							"STOCK_AVAILABLE");
						resultQuery = new Database_Functions().withPremium();
						new Database_Functions().getTitleCodeList(resultQuery);
					classection.comboTitleCode();
					matchFound = secattri.getMatchFound();
					if (matchFound) {
						new Sections().freeRentReceipt();
					} else {
						new PopUp_Windows().noData();
						new Sections().noData();
					}
				}

			} else if (e.getSource() == comboTitleCode) {
				titleCode = classection.getcomboTitleCode();
				if (ReceiptType.equals("RENT")) {
					priceN = "PRICE";
				} else {
					priceN = "PENALTY_COST";
				}
				secattri.setQuerySearchOneValue("TITLES", "CODE", titleCode, "NAME", priceN, "STOCK_AVAILABLE");
				ResultSet resultQuery = new Database_Functions().receipt();
				new Database_Functions().getOneRow(resultQuery);
				matchFound = secattri.getMatchFound();
				if (matchFound) {
					secattri.setTitleName(valueFound1);
					int price = Integer.parseInt(valueFound2);
					secattri.setPrice(price);
					int stockav = Integer.parseInt(valueFound3);
					secattri.setStockAvailable(stockav);
					if (ReceiptType.equals("RENT")) {
						total = (price * 3);
						new Sections().RentReceipt();
						// Adding 3 Days to the current date
						secattri.setDate(classection.returningDay());
						classection.titleName();
					} else if (ReceiptType.equals("PENALTY")) {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
						String date = new Database_Functions().returningDate();
						secattri.setDate(date);
						LocalDate localDate = LocalDate.parse(date, formatter);
						Temporal startDate = localDate;
						date = (String) classection.getTodayDate();
						localDate = LocalDate.parse(date, formatter);
						Temporal endDate = localDate;
						long daysOver = ChronoUnit.DAYS.between(startDate, endDate);
						secattri.setStockAvailable((int) daysOver);
						// System.out.println(startDate+" - "+endDate+" = "+ daysOver);

						totall = (int) (price * daysOver);

						if (totall < 0) {
							total = 0;
							new PopUp_Windows().stillTime();
						} else {
							total = (price * daysOver);
						}
						classection.titleName();
					}else if (ReceiptType.equals("FREE RENT")) {
						new Sections().freeRentReceipt();
						// Adding 3 Days to the current date
						secattri.setDate(classection.returningDay());
						classection.titleName();
					}
					// classection.titleName();

					// if any other combobox is selected
				} else {
					new PopUp_Windows().noData();
					new Sections().noData();
				}

			} else {
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
					// first search parameter.
					dbfunc.uniques(secattri);
					/*
					 * This is just a checking to see if I get any data cAfterSearch =
					 * secattri.getcAfterSearch();
					 * 
					 * int size = cAfterSearch.getItemCount();
					 * 
					 * for (int i = 0; i < size; i++) { Object item = cAfterSearch.getItemAt(i);
					 * System.out.println("Item at " + i + " = " + item); } section =
					 * secattri.getSection(); if(section == "TITLES") {
					 * System.out.println("Controller: " + section); title.cAfterSearch(); }
					 */
					// classection = new Sections();
					classection.cAfterSearch();

				}
			}
		}
	}

//if the user press a button
	@Override
	public void actionPerformed(ActionEvent e) {
		secattri = new Sections_Attributes();
		classection = new Sections();

		switch (e.getActionCommand()) {

		case "bMenu":

			// staffcapsule = new StaffEncapsulation();

			contmenu = new Controller_Menu(staffcapsule);

			break;

		case "bSearch":

			selection = classection.getSelection();
			selection2 = classection.getSelection2();
			// secattri = new Sections_Attributes();
			secattri.setSelection2(selection2);
			// If the user did not change the combobox, it will remind the user to choose an
			// option
			if (selection == null && selection2 == null) {
				new PopUp_Windows().empty();
				// If the user chooses an not acceptable option,
				// a window will appear asking for another value
			} else if (selection.equals("Choose one")) {
				new PopUp_Windows().tryAgain();
			} else {
				dbfunc.searchby();
				matchFound = secattri.getMatchFound();
				if (matchFound) {
					tableNames = secattri.getTableNames();
					tableContent = secattri.getTableContent();
					classection.table(frameHeight);
				} else {
					new PopUp_Windows().noData();
				}
			}
			break;
		case "bSearchAll":

			dbfunc = new Database_Functions();
			dbfunc.searchAll();

			matchFound = secattri.getMatchFound();
			if (matchFound) {
				tableNames = secattri.getTableNames();
				tableContent = secattri.getTableContent();
				// classection = new Sections();
				classection.table(frameHeight);

			} else {
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
					/*
					 * System.out.println("Controller: " + name + " , " + genre + " , " + release +
					 * " , " + format + " , " + category + " , " + stock);
					 */
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
				// System.out.println("Controller: " + firstName + lastName + membershipLevel +
				// creditCard);
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
		// Generate a RECEIPT depend on the option provided
		case "RECEIPT":

			secattri.setReceiptType(classection.getcReceiptType());
			secattri.setTodayDate(classection.getTodayDate());
			secattri.setDate(classection.getDate());
			secattri.setMembershipCard(classection.getfMembershipCard());
			secattri.setTotal(classection.getTotal());

			secattri.setReceiptNum(classection.getReceiptNum());
			secattri.setCodeTitle(classection.getcomboTitleCode());

			if (classection.getcReceiptType() == "RENT"|| classection.getcReceiptType() == "FREE RENT") {
				// Button RECEIPT will insert to RENTED_LIST_TABLE
				query = new Database_Functions().insertRentedList();
				insertDone = new Database_Functions().insert(query);
				if (insertDone) {
					
					// Button RECEIPT will update LOYALTY_CARD
					if(classection.getcReceiptType() == "RENT") {pointsEarn = 10;}else if(classection.getcReceiptType() == "FREE RENT"){pointsEarn = -100;}
					loyaltyCardDone = new Database_Functions().updateloyaltyCard(loyaltyCardFound, pointsEarn);
					// Insert to RECEIPT_TABLE for RENT
					// query = new Database_Functions().insertReceiptTable();
					// insertDone = new Database_Functions().insert(query);
					if (loyaltyCardDone) {
						new PopUp_Windows().points_freeRents_available();
						
					} else {
						new PopUp_Windows().tryAgain();
					}
					

				} else {
					new PopUp_Windows().duplicateRent();
					// new Sections().noData();
				}

			} else if (classection.getcReceiptType() == "PENALTY") {
				// Delete from RENTED_LIST
				updateQuery = dbfunc.deleteRentedList();
		
			} 
			
			if(insertDone || updateQuery>0) {
			// Insert to RECEIPT_TABLE for PENALTY, RENT, FREE RENT
			query = new Database_Functions().insertReceiptTable();
			insertDoneReceiptTable = new Database_Functions().insert(query);
			updateQueryStock = dbfunc.updateStock();
			// Button RECEIPT will add to PENALTY_TABLE
			if (insertDoneReceiptTable && updateQueryStock > 0) {
				new PopUp_Windows().stockUpdated();
				System.out.println("RECEIPT_ TABLE insert");
			} else {
				System.out.println("RECEIPT_ TABLE  was not insert");
				new Sections().noData();
			}
			}
			break;

		}

	}

//if the user type something in a JTextField
	@Override
	public void insertUpdate(DocumentEvent e) {
		classection = new Sections();
		// Checking that the Membership is a number
		if (!classection.getfMembershipCard().matches("[0-9]+")) {
			new PopUp_Windows().notNumber();
		} else {
			referenceValue = classection.getfMembershipCard();
			// Membership_card will shoot Membership_Level
			secattri.setQuerySearchOneValue("CUSTOMER", "MEMBERSHIP_CARD", referenceValue, "LEVEL_MEMBERSHIP",
					"FIRST_NAME", "LAST_NAME");

			ResultSet resultQuery = new Database_Functions().receipt();
			// Get Customer Name and Membership Level
			new Database_Functions().getOneRow(resultQuery);
			matchFound = secattri.getMatchFound();
			// ...set the next available number for the receipt
			consecutiveReceipt = (new Database_Functions().receiptNum() + 1);
			// System.out.println("Controller: "+ consecutiveReceipt);
			secattri.setConsecutiveReceipt(consecutiveReceipt);
			// Check if the customer has FREE RENTS available
			loyaltyCardFound = new Database_Functions().checkLoyaltyCard();
			if (matchFound || loyaltyCardFound) {
				new Sections().fMembershipLevel();
				new PopUp_Windows().points_freeRents_available();
			} else {
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
	}

}