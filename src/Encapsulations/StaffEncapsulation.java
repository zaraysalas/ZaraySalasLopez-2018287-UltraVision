package Encapsulations;
/* Encapsulation of attributes of Staff than are going 
   to be used in DatabaseConnection and Controller.
*/
public class StaffEncapsulation {
	private String username;
	private String password;
	
	
	public void setstaff(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
}
