package Encapsulations;

import javax.swing.JOptionPane;

public class PopUp_Windows{

	public void tryAgain() {
		JOptionPane.showMessageDialog(null,
			    "Information Incorrect. Please, try again",
			    "ERROR",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	public void notNumber() {
		JOptionPane.showMessageDialog(null,
			    "Release / Stock must be numbers. Please, try again",
			    "NumberFormatException",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	public void success() {
		JOptionPane.showMessageDialog(null ,
			    "Operation Successful.");
	}
	
	public void empty() {
		JOptionPane.showMessageDialog(null,
			    "No empty boxes allowed. Please, try again",
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
}
