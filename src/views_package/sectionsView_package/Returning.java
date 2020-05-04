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

public class Returning extends Sections{
	public Returning() {
		super();
		secattri.setFrameSize(150,10,370, 450);
		FrameAttributes(frameLeft, frameUpper, frameWidth, frameHeight);
		secattri.setFrameHeightTable(700);
		secattri.setP1height(330, 650);
		p1(p1width, p1height);
		Search();
		secattri.setp1Add(330, 200, "RETURNING");
		AddTop(p1Addwidth, p1Addheight, titleBorder) ;
		secattri.setFrameHeightTable(480);
	}
	
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
		p1addButtons.add(bAdd = new JButton("RETURN"));
		//p1addButtons.add(bAdd = new JButton("DELETE"));
		//p1addButtons.add(bAdd = new JButton(""));
		//p1addButtons.add(bAdd = new JButton(""));
		bAdd.addActionListener((ActionListener) contReturn);
		bAdd.setActionCommand("RETURN");
		//bAdd.setActionCommand("DELETE");
		//bAdd.setActionCommand("");
		//bAdd.setActionCommand("");
	}


}
