package views_package.sectionsView_package;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Encapsulations.*;
import controllers_package.controller_login_package.*;
import controllers_package.controllers_sections_package.*;

public class Sections extends Sections_Attributes implements Sections_Interface {
	
	protected Sections_Attributes secattri;
	Controller_Sections controllersection;

	public Sections() {
		super();
	}

	@Override
	public void FrameAttributes() {
		sectionFrame = new JFrame("Ultra-Vision");
		sectionFrame.setVisible(true);
		sectionFrame.setBounds(350, 100, 350, 550);
		sectionFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	@Override
	public void Search() {
		controllersection = new Controller_Sections();
		sectionFrame.getContentPane().setLayout(new BoxLayout(sectionFrame.getContentPane(), BoxLayout.X_AXIS));

		sectionFrame.add(p1 = new JPanel());
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p1.setPreferredSize(new Dimension(330, 400));
		p1.setMaximumSize(new Dimension(330, 400));
		p1.add(p1search = new JPanel());

		p1search.setLayout(new BoxLayout(p1search, BoxLayout.Y_AXIS));
		p1search.setBorder(BorderFactory.createTitledBorder(getRaised(), "SEARCH", TitledBorder.LEFT,
				TitledBorder.DEFAULT_POSITION));
		p1search.setPreferredSize(new Dimension(330, 95));
		p1search.setMaximumSize(new Dimension(330, 95));
		p1search.add(p1searchSquares = new JPanel());
		p1searchSquares.setLayout(new BoxLayout(p1searchSquares, BoxLayout.X_AXIS));
		// Titles will be searched by either option in the ComboBox
		p1searchSquares.add(lsearchby = new JLabel("Search by:"));
		csearchby = new JComboBox<String>(searchby);
		csearchby.addItemListener((ItemListener) controllersection);

		p1searchSquares.add(Box.createRigidArea(new Dimension(10, 0)));
		p1searchSquares.add(csearchby);
		p1searchSquares.add(Box.createRigidArea(new Dimension(10, 0)));
		
		cAfterSearch = new JComboBox<Object>();
		p1searchSquares.add(cAfterSearch);
		//The combobox is set not visible until the user choose something from first combobox
		model = (DefaultComboBoxModel) cAfterSearch.getModel();
		cAfterSearch.setVisible(false);
		
		p1search.add(p1searchButtons = new JPanel());
		p1searchButtons.setLayout(new BoxLayout(p1searchButtons, BoxLayout.X_AXIS));
		
		bSearch = new JButton("SEARCH");
		bSearch.setActionCommand("bSearch");
		bSearch.addActionListener(controllersection);
		p1searchButtons.add(bSearch);
		p1searchButtons.add(Box.createRigidArea(new Dimension(10, 40)));
		
		bAll = new JButton("SHOW ALL");
		bAll.setActionCommand("bSearchAll");
		bAll.addActionListener(controllersection);
		p1searchButtons.add(bAll);
		

	}

	@Override
	public String getSelection() {
		String selection = (String) csearchby.getSelectedItem();
		return selection;
	}

	@Override
	public void cAfterSearch() {
		//Make the combobox visible
		cAfterSearch.setVisible(true);
		//Removing all elements from combobox
		model.removeAllElements();
		//Getting the array of elements for the combobox
		sAfterSearch = Sections_Attributes.getsAfterSearch();
		//adding the array to the combobox(model)
		for (Object item : sAfterSearch) {
            model.addElement(item);
        }
        // setting model with new data to the actual combobox
		cAfterSearch.setModel(model);
		Validation();
		
	}
	
	public Object getSelection2() {
		Object selection2 = cAfterSearch.getSelectedItem();
		return selection2;
	}

	@Override
	public void AddTop() {

		p1.add(p1add = new JPanel());
		p1add.setLayout(new BoxLayout(p1add, BoxLayout.Y_AXIS));
		p1add.setPreferredSize(new Dimension(330, 300));
		p1add.setMaximumSize(new Dimension(330, 300));
		p1add.setBorder(
				BorderFactory.createTitledBorder(getRaised(), "ADD", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION));
		p1add.add(p1addlabels = new JPanel());
		p1addlabels.setLayout(new GridLayout(0, 2, 0, 8));
	}

		@Override
	public void table() {
		
		sectionFrame.setBounds(150, 100, 700, 400);
		secattri = new Sections_Attributes();
		tableSearch = new JTable(secattri.getTableContent(), secattri.getTableNames());
		tableSearch.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		p2 = new JPanel();
		sectionFrame.add(p2);
		p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
		p2.add(Box.createRigidArea(new Dimension(0, 50)));
		p2.setBorder(BorderFactory.createTitledBorder(getRaised(), Sections_Attributes.getSection() + " TABLE", TitledBorder.LEFT,
				TitledBorder.DEFAULT_POSITION));
		p2.setPreferredSize(new Dimension(330, 380));
		p2.setMaximumSize(new Dimension(330, 380));
		
		scroll = new JScrollPane(tableSearch);
		scroll.setViewportView(tableSearch);
		p2.add(scroll);
		
		Validation();
		
		
	}
	


	@Override
	public void Add() {
		controllersection = new Controller_Sections();
		p1addlabels.add(lName = new JLabel("NAME:"));
		p1addlabels.add(fName = new JTextField());

		p1addlabels.add(lGenre = new JLabel("GENRE:"));
		String[] genre = { "Choose one", "Drama", "Comedy", "Pop", "Romance", "Thriller", "Rock", "Soul" };
		comboGenre = new JComboBox<String>(genre);
		p1addlabels.add(comboGenre);

		p1addlabels.add(lRelease = new JLabel("RELEASE YEAR:"));
		p1addlabels.add(fRelease = new JTextField());

		p1addlabels.add(lFormat = new JLabel("FORMAT"));
		String[] format = { "Choose one", "DVD", "Blu-Ray", "CD" };
		comboFormat = new JComboBox<String>(format);
		p1addlabels.add(comboFormat);

		p1addlabels.add(lCategory = new JLabel("CATEGORY"));
		String[] category = { "Choose one", "MO", "MU", "LC", "BS" };
		comboCategory = new JComboBox<String>(category);
		p1addlabels.add(comboCategory);

		p1addlabels.add(lStock = new JLabel("STOCK:"));
		p1addlabels.add(fStock = new JTextField());
	}
	
	@Override
	public void AddButton() {
		p1add.add(p1addButtons = new JPanel());
		p1addButtons.add(Box.createRigidArea(new Dimension(0, 5)));
		p1addButtons.setLayout(new BoxLayout(p1addButtons, BoxLayout.Y_AXIS));
		p1addButtons.add(bAdd = new JButton("ADD"));
		bAdd.addActionListener((ActionListener) controllersection);
		bAdd.setActionCommand("bAdd");
		p1add.add(Box.createRigidArea(new Dimension(0, 10)));
		sectionFrame.add(Box.createRigidArea(new Dimension(20, 0)));
	}

	public String getfName() {
		name = fName.getText();
		return name;
	}
	public String getComboGenre() {
		genre = (String) comboGenre.getSelectedItem();
		return genre;
	}
	
	public String getFrelease() {
		String release = fRelease.getText();
		return release;
	}
	public String getComboFormat() {
		format = (String) comboFormat.getSelectedItem();
		return format;
	}
	
	public String getComboCategory() {
		category = (String) comboCategory.getSelectedItem();
		return category;
	}
	
	public String getFstock() {
		String stock = fStock.getText();
		return stock;
	}
	public void AddMovies() {

	}
	@Override
	public void AddMusic() {
		// TODO Auto-generated method stub

	}

	@Override
	public void AddBoxSet() {
		// TODO Auto-generated method stub

	}

	@Override
	public void AddConcert() {
		// TODO Auto-generated method stub

	}

	@Override
	public void LoyaltyCard() {
		p1.add(p1loyaltyCard = new JPanel());
		p1loyaltyCard.setPreferredSize(new Dimension(330, 200));
		p1loyaltyCard.setMaximumSize(new Dimension(330, 200));
		p1loyaltyCard.setBorder(BorderFactory.createTitledBorder(getRaised(), "LOYALTY CARD", TitledBorder.LEFT,
				TitledBorder.DEFAULT_POSITION));

		p1loyaltyCard.setLayout(new GridLayout(0, 2, 0, 8));
		p1loyaltyCard.add(lmemberNum = new JLabel("MEMBERSHIP #:"));
		p1loyaltyCard.add(lmemberNumber = new JLabel("bring here the number"));

		p1loyaltyCard.add(lPoint = new JLabel("POINTS:"));
		p1loyaltyCard.add(lAmountPoints = new JLabel("points here"));

		p1loyaltyCard.add(lFreeRents = new JLabel("FREE RENTS:"));
		p1loyaltyCard.add(lAmountFreeRents = new JLabel("amount of free rents here"));

		p1loyaltyCard.add(Box.createRigidArea(new Dimension(0, 10)));
	}

	@Override
	public void RentedList() {
		p1.add(p1rentedList = new JPanel());
		p1rentedList.setPreferredSize(new Dimension(330, 300));
		p1rentedList.setMaximumSize(new Dimension(330, 300));
		p1rentedList.setBorder(BorderFactory.createTitledBorder(getRaised(), "RENTED LIST", TitledBorder.LEFT,
				TitledBorder.DEFAULT_POSITION));
	}

	@Override
	public void Receipt() {
		// TODO Auto-generated method stub

	}

	@Override
	public void RentingReceipt() {
		// TODO Auto-generated method stub

	}

	@Override
	public void PenaltyReceipt() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Validation() {
		sectionFrame.validate();
		sectionFrame.repaint();
	}





}
