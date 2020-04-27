package views_package.loginView_package;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers_package.controller_login_package.*;


public class Menu extends JFrame{

	private JLabel lTitle;
	private String username;
	
	public Controller_Menu controllermenu;
	
	public Menu(Controller_Menu controllermenu, String username) {
		this.controllermenu = controllermenu;
		this.username = username;
		attributesMenu();
		validationMenu();
	}

	private void attributesMenu() {
		lTitle = new JLabel("WELCOME " + username);
		
		this.setTitle("Ultra-Vision");
		this.setVisible(true);
		this.setBounds(400, 100, 250, 380);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel p = new JPanel();
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.add(p);
		p.setAlignmentX(Component.CENTER_ALIGNMENT);
		p.setPreferredSize(new Dimension(150, 310));
	    p.setMaximumSize(new Dimension(150, 310));
	    
		JLabel lMenu = new JLabel("M a i n   M e n u :");
		JButton bTitles = new JButton("TITLES");
		JButton bCustomer = new JButton("CUSTOMER");
		JButton bRent = new JButton("RENT A TITLE");
		JButton bReturn = new JButton("RETURN A TITLE");
		JButton bBack = new JButton("Back to LOGIN");
		
		

		p.add(lTitle);
		p.add(Box.createRigidArea(new Dimension(0, 60)));
		p.add(lMenu);
		p.add(Box.createRigidArea(new Dimension(0, 60)));
		p.add(bTitles);
		p.add(Box.createRigidArea(new Dimension(0, 30)));
		p.add(bCustomer);
		p.add(Box.createRigidArea(new Dimension(0, 30)));
		p.add(bRent);
		p.add(Box.createRigidArea(new Dimension(0, 30)));
		p.add(bReturn);
		p.add(Box.createRigidArea(new Dimension(0, 30)));
		p.add(bBack);
		
		bTitles.addActionListener((ActionListener)controllermenu);
		bTitles.setActionCommand("albTitle");
		
		bCustomer.addActionListener((ActionListener)controllermenu);
		bCustomer.setActionCommand("albCustomer");
		
		bRent.addActionListener((ActionListener)controllermenu);
		bRent.setActionCommand("albRent");
		
		bReturn.addActionListener((ActionListener)controllermenu);
		bReturn.setActionCommand("albReturn");
		
		bBack.addActionListener((ActionListener)controllermenu);
		bBack.setActionCommand("alBack");

	}
	
	private void validationMenu() {
		this.validate();
		this.repaint();
	}
	

}
