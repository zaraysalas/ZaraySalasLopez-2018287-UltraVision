import Encapsulations.StaffEncapsulation;
import controllers_package.controller_login_package.*;

public class Main {

	//This will run the ControllerLogin

	public static void main(String[] args) {
		//new Controller_Login();
		StaffEncapsulation staffcapsule = new StaffEncapsulation();
		staffcapsule.setstaff("gsalas", "1234");
		new Controller_Menu(staffcapsule);
	}

}
