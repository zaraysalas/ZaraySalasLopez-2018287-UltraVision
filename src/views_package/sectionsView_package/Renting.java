package views_package.sectionsView_package;

public class Renting extends Sections{
	
	public Renting() {
		secattri.setFrameSize(150,10,370, 700);
		FrameAttributes(frameLeft, frameUpper, frameWidth, frameHeight);
		secattri.setFrameHeightTable(700);
		secattri.setP1height(330, 650);
		p1(p1width, p1height);
		
		Search();
		
		secattri.setp1Add(330, 600,"RECEIPT");
		AddTop(p1Addwidth, p1Addheight, titleBorder);
		
		Receipt();
		
		secattri.setAddButton("RECEIPT");
		AddButton(AddButton);
	}

	

}
