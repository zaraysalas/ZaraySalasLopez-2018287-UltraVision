package views_package.sectionsView_package;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import Encapsulations.*;
import controllers_package.controllers_sections_package.Controller_Sections;

public class Title extends Sections{
	protected Controller_Sections controllersec;
	protected Sections_Attributes secattri;

	public Title(){
		super();
		
		FrameAttributes();
		Search();
		AddTop();
		Add();
		AddButton();
		Validation();
		
	}
	public void cAfterSearch() {
	}


}
