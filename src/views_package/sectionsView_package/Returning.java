package views_package.sectionsView_package;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Returning extends Sections{
	public Returning() {
		super();
		secattri.setFrameSize(150,10,370, 450);
		FrameAttributes(frameLeft, frameUpper, frameWidth, frameHeight);
		secattri.setFrameHeightTable(200,40,800,350);
		table(frameLerftTable, frameUpperTable, frameWidthTableint, frameHeightTable);
		secattri.setP1height(330, 650);
		p1(p1width, p1height);
		Search();
		//secattri.setp1Add(330, 50, "RETURNING");
		//AddTop(p1Addwidth, p1Addheight, titleBorder) ;
	}
	/*
	@Override
	public void AddTop(int p1Addwidth, int p1Addheight, String titleBorder) {

		p1.add(p1add = new JPanel());
		p1add.setLayout(new BoxLayout(p1add, BoxLayout.Y_AXIS));
		p1add.setPreferredSize(new Dimension(secattri.getP1Addwidth(), secattri.getP1Addheight()));
		p1add.setMaximumSize(new Dimension(secattri.getP1Addwidth(), secattri.getP1Addheight()));
		p1add.setBorder(BorderFactory.createTitledBorder(getRaised(), secattri.getTitleBorder(), TitledBorder.LEFT,
				TitledBorder.DEFAULT_POSITION));
		p1add.add(p1addButtons = new JPanel());
		p1addButtons.setLayout(new GridLayout(0, 1, 0, 10));
	}
	*/
	@Override
	public void table(int frameLerftTable,int frameUpperTable,int frameWidthTableint, int frameHeightTable) {
		sectionFrame.setBounds(secattri.getFrameLeftTable(), secattri.getFrameUpperTable(), secattri.getFrameWidthTable(), secattri.getFrameHeightTable());

		
		tableSearchModel = new DefaultTableModel(secattri.getTableContent(), secattri.getTableNames());
		tableSearch = new JTable(tableSearchModel);
		tableSearchModel = (DefaultTableModel) tableSearch.getModel();
		tableSearch.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		sectionFrame.add(p2 = new JPanel());
		p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
		p2.setPreferredSize(new Dimension(400, 330));
		p2.setMaximumSize(new Dimension(400, 300));
		p2.add(Box.createRigidArea(new Dimension(0, 50)));
		p2.setBorder(BorderFactory.createTitledBorder(getRaised(), secattri.getSection() + " TABLE", TitledBorder.LEFT,
				TitledBorder.DEFAULT_POSITION));

		scroll = new JScrollPane(tableSearch);
		scroll.setViewportView(tableSearch);
		p2.add(scroll);
		
		p2.add(p2TableButtons = new JPanel());
		p2TableButtons.setLayout(new BoxLayout(p2TableButtons, BoxLayout.X_AXIS));
		/* Data can be delete or modify dynamically because tables are connected 
		 * between them in the database (Parent tables)
		 * 		// Delete
		bDelete = new JButton("DELETE");
		bDelete.setActionCommand("DELETE");
		bDelete.addActionListener(controllersection);
		p2TableButtons.add(bDelete);
		*/	
		// Show all data
		bAll = new JButton("SHOW ALL");
		bAll.setActionCommand("SHOW ALL");
		bAll.addActionListener(controllersection);
		p2TableButtons.add(bAll);
		
		p2TableButtons.add(Box.createRigidArea(new Dimension(30, 0)));
		
		p2TableButtons.add(bAdd = new JButton("RETURN"));
		bAdd.addActionListener((ActionListener) contReturn);
		bAdd.setActionCommand("RETURN");
		Validation();

	}

}
