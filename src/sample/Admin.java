package sample;

import java.util.ArrayList;

/**
 * The class illustrates an Admin profile
 * @author Julian Chan
 */
public class Admin extends User {

	private ArrayList<User> userList;
	
	/**
     * Create an Admin object with specified arguments
     * @param username
     * @param first name
     * @param last name
     */
	public Admin(String userName, String first, String last) {
		super(userName, first, last);
	}

	/**
     * Method to add users
     * @param User
     */
	public void addUser(User user) {
    	userList.add(user);
    }

	 /**
     * Method to remove users
     * @param User
     */
	public void removeUser(User user) {
		userList.remove(user);
	}

	 /**
     * Method to check if a username is valid
     * @param username
     */
	public boolean CheckUsername(String UserName) {
		boolean check = false;
	    if(UserName == getUserName()) {
	    	check = true;
	    }
	    return check;
	}
	
	//toString method
	public String toString() {
		return ("Number of current users :" + getUserCount());
	}

}

