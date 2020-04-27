package controllers_package.controllers_sections_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import Encapsulations.*;
import database_package.*;
import views_package.sectionsView_package.*;

public class Controller_Sections extends Sections_Attributes implements ItemListener, ActionListener {
	//private Controller_Sections controllersection;
	private Database_Functions dbfunc;
	private Title title;
	private Customer customer;
	protected Sections_Attributes secattri;
	private Sections classection;
	

	public Controller_Sections() {super();}

	@Override
	public void itemStateChanged(ItemEvent e) {

		if (e.getStateChange() == ItemEvent.SELECTED) {
			classection = new Sections();
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
				cAfterSearch = Sections_Attributes.getcAfterSearch();
				
				 int size = cAfterSearch.getItemCount();
				
				for (int i = 0; i < size; i++) {
					Object item = cAfterSearch.getItemAt(i);
					System.out.println("Item at " + i + " = " + item);
				}
							section = Sections_Attributes.getSection();
							if(section == "TITLES") {
					System.out.println("Controller: " + section);
					title.cAfterSearch();
				}
				*/
				classection = new Sections();
				classection.cAfterSearch();

			}
		}

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "bSearch":
			
			classection = new Sections();
			selection = classection.getSelection();
			selection2=classection.getSelection2();
			secattri = new Sections_Attributes();
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
					classection.table();
				}else {
					new PopUp_Windows().noData();
				}
			}
			break;
		case "bSearchAll":
			dbfunc = new Database_Functions();
			dbfunc.searchAll();
			secattri = new Sections_Attributes();
			matchFound = secattri.getMatchFound();
			if (matchFound) {
				tableNames = secattri.getTableNames();
				tableContent = secattri.getTableContent();
				classection = new Sections();
				classection.table();

			}else {
				new PopUp_Windows().noData();
			}
			break;
			
		case "bAdd":
			classection = new Sections();
			name = classection.getfName();
			genre = classection.getComboGenre();
			format = classection.getComboFormat();
			category = classection.getComboCategory();
			try {
				release = Integer.parseInt(classection.getFrelease());
				stock = Integer.parseInt(classection.getFstock());
			} catch (NumberFormatException e1) {
				new PopUp_Windows().notNumber();
			}
				if (name == null || name.isEmpty()  || genre.isEmpty() || genre == null || format.isEmpty() ||
						format == null || category.isEmpty() || category == null) {
					new PopUp_Windows().empty();
				}else if (genre.equals("Choose one") || format.equals("Choose one") || category.equals("Choose one")) {
					new PopUp_Windows().tryAgain();
				}else if(1981 >= release  || release >= 2021){
					new PopUp_Windows().releaseOutRange();
				}else if(stock <= 0){
					new PopUp_Windows().moreThanZero();
				}else {
					secattri = new Sections_Attributes();
					secattri.setAttriAdd(name, genre, release, format, category,stock);
					System.out.println("Controller: " + name +" , "+ genre +" , "+ release +" , "+ format +" , "+ category +" , "+ stock);
					dbfunc = new Database_Functions();
					insertDone = dbfunc.insertTitle();
					if(insertDone) {new PopUp_Windows().success();
					}else {new PopUp_Windows().tryAgain();}
				}
		  break;
		}
		
	}

	

}