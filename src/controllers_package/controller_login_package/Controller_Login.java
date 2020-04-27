package controllers_package.controller_login_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Encapsulations.*;
import views_package.loginView_package.*;
import database_package.*;


public class Controller_Login implements ActionListener{
	//Instance of...
	private StaffEncapsulation staffcapsule;
	private Login loginwindow;
	private Database_Login database;
	private Database_Functions dbinsert;
	private Controller_Menu controllermenu;
	
	private String username, password;
	private boolean in, insertDone;

	public Controller_Login(){
		loginwindow = new Login(this);
		database = new Database_Login();

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Get the values which were input by the user in "Login".
		username = loginwindow.getUsername();
		password = loginwindow.getPassword();
		// New instance of StaffEncapsulation with the values from "FirstWindowLogin".
		staffcapsule = new StaffEncapsulation();
		staffcapsule.setstaff(username, password);
		//If any of the textField is empty when the user press any button, the user will see a complaint
		if (username.isEmpty() || password.isEmpty()) {
			new PopUp_Windows().empty();
		} else {
			// Set what will happen when the button "LOGIN" in "Login" is pressed.
			if (e.getActionCommand().equals("albutton")) {
				// Check with the "DatabaseConexion" class if the user is the database.
				in = database.login(staffcapsule);

				// If "in" is true, the user is referred to "Menu" and the window "Login" will
				// be closed.
				if (in) {
					/*
					 * A constructor is created in "Menu" where a new Window is set. The values
					 * "username, password" can be used.
					 */

					// Create a new Controller Menu which controls the Menu(window)
					controllermenu = new Controller_Menu(staffcapsule);
					// the window for Logging in is closed.
					loginwindow.dispose();
				} else {
					new PopUp_Windows().tryAgain();
				}
				/*
				 * Get the values which were input by the user in "Login". Set what will happen
				 * when the button "LOGIN" in "Login" is pressed. When the user add a new staff
				 * to the database, this will run.
				 */

			} else if (e.getActionCommand().equals("bAddStaff")) {
				//Run the database...
				dbinsert = new Database_Functions();
				insertDone = dbinsert.insertStaff(staffcapsule);
				//...to add a new Staff...
				if (insertDone) {
					new PopUp_Windows().success();
					//...but if it already exists a window would show up.
				} else {
					new PopUp_Windows().duplicate();
				}
			}
		}
	}
}

