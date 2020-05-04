package views_package.sectionsView_package;

import java.awt.Dimension;
import java.awt.event.ItemListener;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

//import controllers_package.controllers_sections_package.Controller_Sections;

public class Receipt extends Sections{
	
	public Receipt() {
		super();
		secattri.setFrameSize(150,10,370, 650);
		FrameAttributes(frameLeft, frameUpper, frameWidth, frameHeight);
		secattri.setFrameHeightTable(700);
		secattri.setP1height(330, 650);
		p1(p1width, p1height);
		Search();
		secattri.setp1Add(330, 600,"RECEIPT");
		AddTop(p1Addwidth, p1Addheight, titleBorder);
		Add();
		secattri.setAddButton("RECEIPT");
		AddButton(AddButton);
	}
	@Override
	public void Add() {
		p1addlabels.add(lName = new JLabel("MEMBERSHIP CARD:"));
		p1addlabels.add(fMembershipCard = new JTextField());
		fMembershipCard.getDocument().addDocumentListener((DocumentListener) controllersection);

		p1addlabels.add(lMembership = new JLabel("MEMBERSHIP'S LEVEL:"));
		p1addlabels.add(fMembership = new JTextField());
		fMembership.setEditable(false);

		p1addlabels.add(lCustomerName = new JLabel("CUSTOMER NAME:"));
		p1addlabels.add(fCustomerName = new JTextField());
		fCustomerName.setEditable(false);

		p1addlabels.add(lConsecutiveReceipt = new JLabel("RECEIPT NUMBER:"));
		p1addlabels.add(fConsecutiveReceipt = new JTextField());
		fConsecutiveReceipt.setEditable(false);

		p1addlabels.add(lReceiptType = new JLabel("RECEIPT TYPE:"));
		receiptTypeList = new String[] { "Choose one", "RENT", "PENALTY", "FREE RENT" };
		cReceiptType = new JComboBox<String>(receiptTypeList);
		p1addlabels.add(cReceiptType);
		cReceiptType.addItemListener((ItemListener) controllersection);
		
		//Day the costumer return the title
		p1addlabels.add(lDateToday = new JLabel("DATE:"));
		p1addlabels.add(fDateToday = new JTextField());
		fDateToday.setEditable(false);

		// Day was supposed to be returned
		p1addlabels.add(lDate = new JLabel("DATE: "));
		lDate.setVisible(false);
		p1addlabels.add(fDate = new JTextField());
		fDate.setVisible(false);
		fDate.setEditable(false);


		p1addlabels.add(lTitleCode = new JLabel("TITLE CODE:"));
		comboTitleCode = new JComboBox<String>();
		p1addlabels.add(comboTitleCode);
		comboTitleCode.addItemListener((ItemListener) controllersection);
		// The combobox is set not visible until the user choose something from first
		// combobox
		modelTitleCode = (DefaultComboBoxModel) comboTitleCode.getModel();
		comboTitleCode.setVisible(true);
		//
		p1addlabels.add(lStockAvailable = new JLabel(""));
		lStockAvailable.setVisible(false);
		p1addlabels.add(fStockAvailable = new JTextField());
		fStockAvailable.setVisible(false);
		fStockAvailable.setEditable(false);

		p1add.add(p1addTitleName = new JPanel());
		p1addTitleName.setPreferredSize(new Dimension(330, 0));
		p1addTitleName
				.add(fTitle = new JTextField("TitleDescription TitleDescription TitleDescription TitleDescription"));
		fTitle.setPreferredSize(new Dimension(315, 30));
		fTitle.setMaximumSize(new Dimension(315, 60));
		fTitle.setEditable(false);
		fTitle.setVisible(true);

		p1addlabels.add(lPrice = new JLabel(""));
		p1addlabels.add(fPrice = new JTextField());
		fPrice.setEditable(false);

		p1addlabels.add(lTotal = new JLabel("TOTAL:"));
		p1addlabels.add(fTotal = new JTextField());
		fTotal.setEditable(false);
	}

}
