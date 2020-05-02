package views_package.sectionsView_package;

import java.awt.event.ItemEvent;

import controllers_package.controllers_sections_package.Controller_Sections;

public interface Sections_Interface {

	public void FrameAttributes(int frameLeft, int frameUpper, int frameWidth, int frameHeight);
	public void Search();
	
	public void p1(int p1width, int p1height);
	public void table(int frameHeightTable);
	public void AddTop(int p1Addwidth, int p1Addheight, String titleBorder);
	public void Add();
	public void AddButton(String AddButton);

	//public void RentReceipt();
	//public void PenaltyReceipt();
	public void AddMovies();
	public void AddMusic();
	public void AddBoxSet();
	public void AddConcert();
	public void LoyaltyCard();
	public void RentedList() ;
	public void Validation();
	//----------------
	public String getSelection();
	public void cAfterSearch();
	public String getfName();
	public String getComboGenre();
	public String getFrelease();
	public String getComboFormat();
	public String getComboCategory();
	public String getFstock();
	//-----------------CUSTOMER------------------------
	public String getfFirstName();
	public String getfLastName();
	public String getcMemberLevel();
	public String getfCreditCard();
	//-------------------RECEIPT--------------------------
	public String getfMembershipCard();
	public void fMembershipLevel();
	public String today();
	public String getcReceiptType();
	//---------------RENTING-----------------------------
	public void RentReceipt();
	public void comboTitleCode();
	public void titleName();
	public String getfMembership();
	public String returningDay();
	public Object getcomboTitleCode();
	public Object getReceiptNum();
	public void PenaltyReceipt();
	public void noData();
	//--------------FREE RENT---------------------
	public void freeRentReceipt();
}
