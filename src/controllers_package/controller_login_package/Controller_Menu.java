package controllers_package.controller_login_package;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import Encapsulations.*;
import controllers_package.controllers_sections_package.*;
import database_package.*;
import views_package.loginView_package.*;
import views_package.sectionsView_package.*;

public class Controller_Menu extends Sections_Attributes implements ActionListener{
	
	private Menu menu;
	private Title title;
	private Customer customer;
	private Receipt renting;
	private Returning returning;
	private Controller_Login againControllerLogin;
	private Controller_Menu controllermenu;
	private Controller_Sections controllersection;
	private Database_Functions database;
	private StaffEncapsulation staffcapsule;
	private Sections_Attributes secattri;
	
	private String[] searchby;
	private String password, username, searchValue, selection, selection1, name, genre, format, category, className;
	private Database_Functions dbfunc;

	
	public Controller_Menu(StaffEncapsulation staffcapsule) {
		this.staffcapsule = staffcapsule;
		username = staffcapsule.getUsername();
		//System.out.println(username + " in Controller_Menu");//Checking point
		// Open the Menu with this Controller and passing the username
		menu = new Menu(this, username);
		

	}

// Depend on what button the user press, different process are called
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		// When "Title" is pressed in the Menu
		case "albTitle":
			// menu is closed and...
			menu.dispose();
			secattri = new Sections_Attributes();
			searchby = new String[] {"Choose one", "Genre", "Release_Year", "Format"};
			section = "TITLES";
			secattri.setcontrollerdata(searchby, section);
			//Display a table with all data
			dbfunc = new Database_Functions();
			dbfunc.searchAll();

			matchFound = secattri.getMatchFound();
			if (matchFound) {
				tableNames = secattri.getTableNames();
				tableContent = secattri.getTableContent();
			} else {
				new PopUp_Windows().noData();
			}
			//---------------------
			//... the window for Title is showed.
			title = new Title();
			
			break;
		// When "Customer" is pressed in the Menu
		case "albCustomer":
			menu.dispose();
			searchby = new String[] {"Choose one", "FIRST_NAME", "LAST_NAME", "LEVEL_MEMBERSHIP"};
			secattri = new Sections_Attributes();
			section = "CUSTOMER";
			secattri.setcontrollerdata(searchby, section);
			//Display a table with all data
			dbfunc = new Database_Functions();
			dbfunc.searchAll();

			matchFound = secattri.getMatchFound();
			if (matchFound) {
				tableNames = secattri.getTableNames();
				tableContent = secattri.getTableContent();
			} else {
				new PopUp_Windows().noData();
			}
			//---------------------
			customer = new Customer();
			break;
		// When "Renting" is pressed in the Menu
		case "bCheckout":
			menu.dispose();
			searchby = new String[] {"Choose one", "RECEIPT_TYPE", "DATE_RENTED", "RETURNING_DATE", "MEMBERSHIP_CARD"};
			secattri = new Sections_Attributes();
			section = "RECEIPT";
			secattri.setcontrollerdata(searchby, section);
			//Display a table with all data
			dbfunc = new Database_Functions();
			dbfunc.searchAll();
			//Display a table with all data
			dbfunc = new Database_Functions();
			dbfunc.searchAll();

			matchFound = secattri.getMatchFound();
			if (matchFound) {
				tableNames = secattri.getTableNames();
				tableContent = secattri.getTableContent();
			} else {
				new PopUp_Windows().noData();
			}
			//---------------------
			matchFound = secattri.getMatchFound();
			if (matchFound) {
				tableNames = secattri.getTableNames();
				tableContent = secattri.getTableContent();
			} else {
				new PopUp_Windows().noData();
			}
			//---------------------
			renting = new Receipt();
			break;
		// When "Returning" is pressed in the Menu
		case "albReturn":
			menu.dispose();
			searchby = new String[] {"Choose one", "MEMBERSHIP_CARD", "CODE", "DATE_RENTED", "RETURNING_DATE"};
			secattri = new Sections_Attributes();
			section = "RENTED_LIST";
			secattri.setcontrollerdata(searchby, section);
			//Display a table with all data
			dbfunc = new Database_Functions();
			dbfunc.searchAll();

			matchFound = secattri.getMatchFound();
			if (matchFound) {
				tableNames = secattri.getTableNames();
				tableContent = secattri.getTableContent();
			} else {
				new PopUp_Windows().noData();
			}
			//---------------------
			returning = new Returning();
			break;
		// When "Back to login" is pressed in the Menu
		case "alBack":
			System.out.println("Button BACK was pressed");
			againControllerLogin = new Controller_Login();
			menu.dispose();
			
			break;
		}

	}


}



