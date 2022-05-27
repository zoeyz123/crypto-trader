package cryptoTrader.gui;


public class userDataBase {
	
	private static userDataBase instance;
	
	private String correctName = "cs";
	private String correctPassword = "2212";
	
	// gets instance of userDatabase object - uses the singleton design pattern
	public static userDataBase getInstance() {
		if (instance == null)
			instance = new userDataBase();
		return instance;
	}
	
	// constructor
	private userDataBase() {
	}
	
	/*
	 * This method checks if the username and password is correct
	 * @param the givenName is the user name that was inputted in the text field and the givenPassword is the password inputted in the text field
	 * @return it returns true if the credential match and false if it doesn't
	 */
	public boolean checkCredentials(String givenName, String givenPassword) {
		if (givenName.equals(correctName) && givenPassword.equals(correctPassword)) {
			return true;
		}
		return false;
	}

}
