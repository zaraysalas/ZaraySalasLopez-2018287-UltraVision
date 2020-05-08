package controllers_package.controllers_sections_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import Encapsulations.*;
import database_package.Database_Functions;
import views_package.sectionsView_package.*;

public class Controller_Returning extends Sections_Attributes implements ActionListener {
	private Sections_Attributes secattri;
	private Sections classection;
	private Database_Functions dbfunc;
	private Receipt receipt;

	public Controller_Returning() {
		super();

	}

	// if the user press a button
	@Override
	public void actionPerformed(ActionEvent e) {
		secattri = new Sections_Attributes();
		classection = new Sections();
		dbfunc = new Database_Functions();
		
		switch (e.getActionCommand()) {

		case "RETURN":

			try {

				// Takes elements in the row selected at Rented_List's table
				MembershipCard = (String) (tableSearchModel.getDataVector().elementAt(tableSearch.getSelectedRow()))
						.elementAt(0);
				secattri.setMembershipCard(MembershipCard);

				titleCode = (tableSearchModel.getDataVector().elementAt(tableSearch.getSelectedRow())).elementAt(1);
				secattri.setCodeTitle(titleCode);
				
				String today = classection.today();
				//System.out.println(today);
				Object returningDate = (tableSearchModel.getDataVector().elementAt(tableSearch.getSelectedRow()))
						.elementAt(3);
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
				LocalDate localDate = LocalDate.parse(today, formatter);
				Temporal startDate = localDate;
				
				localDate = LocalDate.parse((CharSequence) returningDate, formatter);
				Temporal endDate = localDate;
				//Difference in days between today and Returning day
				long daysOver = ChronoUnit.DAYS.between(endDate, startDate);
				/*
				System.out.println(startDate + " - " + endDate + " = " + daysOver);
				If the difference is more than Zero, the customer overrented the title and need to pay a penalty...
				*/
				if (daysOver > 0) {
					new PopUp_Windows().titleOver();
					//...therefore the Receipt window open with the data of the customer and the title
					receipt = new Receipt();
					fMembershipCard.setText(MembershipCard);
					cReceiptType.setSelectedIndex(2);
				} else {
					//...if the difference is equal or less than zero, delete the data from the Rented_List
					// and update the stock
					//System.out.println("MembershipCard: " + MembershipCard + ", CodeTitle: " + titleCode);
					//Call Database function to delete the row
					updateQuery = dbfunc.deleteRentedList();
					if (updateQuery <= 0) {
						new PopUp_Windows().tryAgain();
					} else {
						//it not a PENALTY but the same method(query) will be used.
						secattri.setReceiptType("PENALTY");
						updateQueryStock = dbfunc.updateStock();
						if (updateQueryStock > 0) {
							new PopUp_Windows().stockUpdated();
						}
					}
				}
			} catch (Exception e1) {
				new PopUp_Windows().noTableSelected();
				e1.printStackTrace();

				/*
				 * Object row =
				 * tableSearchModel.getDataVector().elementAt(tableSearch.getSelectedRow());
				 * System.out.println(row); int rowNumber = tableSearch.getSelectedRow(); int
				 * numOfElementsRow =
				 * tableSearchModel.getDataVector().elementAt(rowNumber).size();
				 * System.out.println("tableSearch.getSelectedRow() "+tableSearch.getSelectedRow
				 * ()); for (int i = 0; i < numOfElementsRow; i++) { Object cellData =
				 * (tableSearchModel.getDataVector().elementAt(rowNumber)).elementAt(i);
				 * System.out.println("Element #: "+ i + " = " +cellData+ " at row " +
				 * rowNumber); }
				 */

			}

			break;
		}
	}
}
