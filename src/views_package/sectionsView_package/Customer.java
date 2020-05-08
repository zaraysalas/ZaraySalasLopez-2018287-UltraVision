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

	public Customer(){
		super();
		secattri.setFrameSize(200, 100, 330, 200);
		secattri.setP1height(330, 450);
		secattri.setp1Add(330, 200, "NEW CUSTOMER");
		secattri.setFrameHeightTable(200,40,800,400);
		FrameAttributes(secattri.getFrameLeft(), secattri.getFrameUpper(), secattri.getFrameWidth(), secattri.getFrameHeight());
		table(frameLerftTable, frameUpperTable, frameWidthTableint, frameHeightTable);
		p1(p1width, p1height);
		Search();
		modifyButtons();

		Validation();
	}

	@Override
	public void modifyButtons() {
		
		p1Menu.add(p1ModifyButtons = new JPanel());
		//p1Modify.add(p1ModifyButtons = new JPanel());
		p1ModifyButtons.setLayout(new BoxLayout(p1ModifyButtons, BoxLayout.X_AXIS));
		//Modify
		bNew = new JButton("NEW CUSTOMER");
		bNew.setActionCommand("NEW");
		bNew.addActionListener(controllersection);
		p1ModifyButtons.add(bNew);
		p1ModifyButtons.add(Box.createRigidArea(new Dimension(15, 0)));
		
		bUpdate = new JButton("UPDATE CUSTOMER");
		bUpdate.setActionCommand("UPDATE");
		bUpdate.addActionListener(controllersection);
		p1ModifyButtons.add(bUpdate);
	}

}
