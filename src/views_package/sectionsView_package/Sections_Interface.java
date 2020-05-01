package views_package.sectionsView_package;

import java.awt.event.ItemEvent;

import controllers_package.controllers_sections_package.Controller_Sections;

public interface Sections_Interface {

	public void FrameAttributes(int frameLeft, int frameUpper, int frameWidth, int frameHeight);
	public void Search();
	public String getSelection();
	public void cAfterSearch();
	public void p1(int p1width, int p1height);
	public void table(int frameHeightTable);
	public void AddTop(int p1Addwidth, int p1Addheight, String titleBorder);
	public void Add();
	public void AddButton(String AddButton);
	public void Receipt();
	public void RentReceipt();
	public void PenaltyReceipt();
	public void AddMovies();
	public void AddMusic();
	public void AddBoxSet();
	public void AddConcert();
	public void LoyaltyCard();
	public void RentedList() ;

	public void Validation();
	
	

	
	
	
	

}
