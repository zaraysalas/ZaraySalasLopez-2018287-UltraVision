package Encapsulations;

import javax.swing.JOptionPane;

public class PopUp_Windows{

	protected Sections_Attributes secattri;
	public PopUp_Windows() {secattri = new Sections_Attributes(); }
	public void tryAgain() {
		JOptionPane.showMessageDialog(null,
			    "Information Incorrect. Please, try again",
			    "ERROR",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	public void notNumber() {
		JOptionPane.showMessageDialog(null,
			    "That is not a valid number. \n Please, try again",
			    "NumberFormatException",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	public void success() {
		JOptionPane.showMessageDialog(null ,
			    "Operation Successful.");
	}
	
	public void empty() {
		JOptionPane.showMessageDialog(null,
			    "No empty boxes allowed. \n Please, try again",
			    "ERROR",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	public void duplicate() {
		JOptionPane.showMessageDialog(null,
			    "Already exist. Try different one.",
			    "ERROR",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	public void noData() {
		JOptionPane.showMessageDialog(null,
			    "No information available.",
			    "SORRY",
			    JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void releaseOutRange() {
		JOptionPane.showMessageDialog(null,
			    "First CD was produced in 1982, DVD in 1996 and Blu-Ray in 2002. \n"
			    + "Try again!! between 1982 and 2020",
			    "Out of Range",
			    JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void moreThanZero() {
		JOptionPane.showMessageDialog(null,
			    "Input a number > 0.",
			    "Number < 0",
			    JOptionPane.ERROR_MESSAGE);
	}

	public void notCreditCard() {
		JOptionPane.showMessageDialog(null,
			    "That is not a valid Credit Card Number. \n Must be 16 digits.",
			    "Format Invalid",
			    JOptionPane.ERROR_MESSAGE);
		
	}

	public void InexistantMemberShipCard() {
		JOptionPane.showMessageDialog(null,
			    "That is not a valid Membership Card. \n Must be 10001 or more.",
			    "Format Invalid",
			    JOptionPane.ERROR_MESSAGE);
		
	}

	public void duplicateRent() {
		JOptionPane.showMessageDialog(null,
			    "A customer can't rent twice the same Title",
			    "Rent Invalid",
			    JOptionPane.ERROR_MESSAGE);
		
	}	
	public void points_freeRents_available() {
		JOptionPane.showMessageDialog(null,
			    "Membership Card "+ secattri.getMembershipCard() + " has:\n POINTS: "+ secattri.getPoints() +"\nFREE RENTS: "+ secattri.getFreeRents() + "\navailable",
			    "Loyalty Card",
			    JOptionPane.INFORMATION_MESSAGE);
	}
	public void stillTime() {
		JOptionPane.showMessageDialog(null,
			    "The title "+secattri.getCodeTitle()+" is not overrrented.",
			    "Days Remaining",
			    JOptionPane.INFORMATION_MESSAGE);
	}
	public void stockUpdated() {
		JOptionPane.showMessageDialog(null,
			    "New Stock Available is "+secattri.getStockAvailable(),
			    "Days Remaining",
			    JOptionPane.INFORMATION_MESSAGE);
	}
	public void noTableSelected() {
		JOptionPane.showMessageDialog(null,
			    "Please, select a table. \nYou can do that in the upper section",
			    "No Table",
			    JOptionPane.ERROR_MESSAGE);
		
	}
}
