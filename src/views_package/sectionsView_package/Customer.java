package views_package.sectionsView_package;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controllers_package.controller_login_package.Controller_Menu;
import controllers_package.controllers_sections_package.Controller_Sections;

public class Customer extends Sections{
	
	public Customer(){
		super();
		FrameAttributes();
		Search();
		AddTop();
		Add();
		AddButton();
		Validation();
	}

	@Override
	public void Add() {
		p1addlabels.add(lName = new JLabel("FIRST NAME:"));
		p1addlabels.add(fName = new JTextField());

		p1addlabels.add(lRelease = new JLabel("LAST NAME:"));
		p1addlabels.add(fRelease = new JTextField());

		p1addlabels.add(lMembership = new JLabel("MEMBERSHIP'S LEVEL:"));
		String[] memeberLevel = {"Choose one","MO", "MU", "LC", "BS"};
		cMemberLevel = new JComboBox<String>(memeberLevel);
		p1addlabels.add(cMemberLevel);
	}
	

}
