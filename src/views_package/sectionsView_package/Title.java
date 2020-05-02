package views_package.sectionsView_package;

import controllers_package.controllers_sections_package.Controller_Sections;

public class Title extends Sections{
	public Title(){
		super();
		FrameAttributes(frameLeft, frameUpper,frameWidth, frameHeight);
		p1(p1width, p1height);
		Search();
		AddTop(p1Addwidth, p1Addheight, titleBorder);;
		Add();
		AddButton(AddButton);
		Validation();
		
		
	}

}
