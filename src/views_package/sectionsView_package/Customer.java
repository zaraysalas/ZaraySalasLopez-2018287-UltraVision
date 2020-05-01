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

import Encapsulations.Sections_Attributes;
import controllers_package.controller_login_package.Controller_Menu;
import controllers_package.controllers_sections_package.Controller_Sections;

public class Customer extends Sections{
	
	private static Controller_Sections controllersection;


	public Customer(){
		super();
		secattri.setFrameSize(200, 100, 330, 400);
		FrameAttributes(frameLeft, frameUpper, frameWidth, frameHeight);
		secattri.setFrameHeightTable(400);
		secattri.setP1height(330, 320);
		p1(p1width, p1height);
		Search();
		AddTop(p1Addwidth, p1Addheight, titleBorder);
		Add();
		secattri.setAddButton("ADD_CUSTOMER");
		AddButton(AddButton);
		Validation();
	}
	

	@Override
	public void Add() {
		p1addlabels.add(lFirstName = new JLabel("FIRST NAME:"));
		p1addlabels.add(fFirstName = new JTextField());

		p1addlabels.add(lLastName = new JLabel("LAST NAME:"));
		p1addlabels.add(fLastName = new JTextField());

		p1addlabels.add(lMembership = new JLabel("MEMBERSHIP'S LEVEL:"));
		memberLevelList = new String[] {"Choose one","MO", "MU", "LC", "BS"};
		cMemberLevel = new JComboBox<String>(memberLevelList);
		p1addlabels.add(cMemberLevel);
		
		p1addlabels.add(lCreditCard = new JLabel("CREDIT CARD:"));
		p1addlabels.add(fCreditCard = new JTextField());
	}
	

}
