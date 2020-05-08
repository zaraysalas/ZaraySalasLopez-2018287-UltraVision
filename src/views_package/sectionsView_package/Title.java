package views_package.sectionsView_package;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import controllers_package.controllers_sections_package.Controller_Sections;

public class Title extends Sections{
	public Title(){
		super();
		secattri.setFrameSize(200, 80, 330, 480);
		secattri.setP1height(330, 450);
		secattri.setp1Add(330, 300, "ADD TITLE");
		secattri.setAddButton("ADD_TITLE");
		secattri.setFrameHeightTable(200,40,800,480);
		
		FrameAttributes(frameLeft, frameUpper,frameWidth, frameHeight);
		table( frameLerftTable, frameUpperTable, frameWidthTableint, frameHeightTable);
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
		bNew = new JButton("NEW TITLE");
		bNew.setActionCommand("NEW");
		bNew.addActionListener(controllersection);
		p1ModifyButtons.add(bNew);
		//p1ModifyButtons.add(Box.createRigidArea(new Dimension(50, 0)));
	}


}
