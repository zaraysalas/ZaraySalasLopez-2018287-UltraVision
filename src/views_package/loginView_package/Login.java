package views_package.loginView_package;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controllers_package.controller_login_package.*;

/* This the first window that the user will see
 he/she will need to enter username and password to enter the system.
 */

public class Login extends JFrame{

	private JTextField fieldUsername;
	private JTextField fieldPassword;
	//private ActionListener controller;
	/*Class "LoginWindow" will know who the ControllerLogin is.
	Instance of...
	*/
	private Controller_Login controller;
	//Method
	public Login(Controller_Login controller) {
		//Setter / Reference of the Controller_Login
		this.controller = controller;
		//Run method with attributes, fields and validation.
		attributes();
		content();
		validation();
	}
	
	// What the JFrame will look.
	public void attributes() {
		this.setVisible(true);
		this.setBounds(400, 100, 300, 450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Ultra-Vision");
		
	}
	

	// What the JFrame will contain.
	public void content() {
		Border raised = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		Border blueline = BorderFactory.createLineBorder(Color.BLUE);
		raised = BorderFactory.createCompoundBorder(raised, blueline);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createTitledBorder(raised, "WELCOME TO ULTRA-VISION", TitledBorder.LEFT ,TitledBorder.ABOVE_TOP));
		p.setPreferredSize(new Dimension(270, 350));
	    p.setMaximumSize(new Dimension(270, 350));
	    
	    JLabel labelUsername = new JLabel("USERNAME");
		fieldUsername = new JTextField(20);
		JLabel labelPassword = new JLabel("PASSWORD");
		fieldPassword = new JTextField(20);
		JButton b = new JButton("LOGIN");
		b.addActionListener((ActionListener) controller);
		b.setActionCommand("albutton");
		
	    JLabel lNewUser1 = new JLabel("If you want to add a new USER add");
	    JLabel lNewUser2 = new JLabel("the new USERNAME and the PASSWORD");
	    JLabel lNewUser3 = new JLabel("in the spaces above and press:");
	    JButton bAddStaff = new JButton("NEW USER");
	    bAddStaff.addActionListener((ActionListener) controller);
	    bAddStaff.setActionCommand("bAddStaff");
		
		// Add to the JFrame.
		this.add(p);
		
		// Add to the Panel
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		p.add(Box.createRigidArea(new Dimension(0, 60)));
		p.add(labelUsername);
		p.add(fieldUsername);
		p.add(labelPassword);
		p.add(fieldPassword);
		p.add(b);
		p.add(Box.createRigidArea(new Dimension(0, 60)));
		p.add(lNewUser1);
		p.add(lNewUser2);
		p.add(lNewUser3);
		p.add(bAddStaff);
		
	}
	
	public void validation() {
		this.validate();
		this.repaint();
	}
	public String getUsername() {
		return fieldUsername.getText();
	}
	
	public String getPassword() {
		return fieldPassword.getText();
	}
}
