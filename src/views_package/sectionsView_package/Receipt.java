package views_package.sectionsView_package;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemListener;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentListener;

//import controllers_package.controllers_sections_package.Controller_Sections;

public class Receipt extends Sections{
	
	public Receipt() {
		super();
		secattri.setFrameSize(40,0,370, 680);
		FrameAttributes(frameLeft, frameUpper, frameWidth, frameHeight);
		secattri.setFrameHeightTable(200,0,800,665);
		table(frameLerftTable, frameUpperTable, frameWidthTableint, frameHeightTable);
		secattri.setP1height(330, 650);
		p1(p1width, p1height);
		Search();
		secattri.setp1Add(330, 500,"RECEIPT");
		AddTop(p1Addwidth, p1Addheight, titleBorder);
		Add();
		secattri.setAddButton("RECEIPT");
		AddButton(AddButton);
	}
	@Override
	public void AddTop(int p1Addwidth, int p1Addheight, String titleBorder) {
		p1.setPreferredSize(new Dimension(secattri.getP1width(), secattri.getP1height()));
		p1.add(p1add = new JPanel());
		p1add.setLayout(new BoxLayout(p1add, BoxLayout.Y_AXIS));
		p1add.setPreferredSize(new Dimension(secattri.getP1Addwidth(), secattri.getP1Addheight()));
		p1add.setMaximumSize(new Dimension(secattri.getP1Addwidth(), secattri.getP1Addheight()));
		p1add.setBorder(BorderFactory.createTitledBorder(getRaised(), secattri.getTitleBorder(), TitledBorder.LEFT,
				TitledBorder.DEFAULT_POSITION));
		p1add.add(p1addlabels = new JPanel());
		p1addlabels.setLayout(new GridLayout(0, 2, 0, 8));
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
