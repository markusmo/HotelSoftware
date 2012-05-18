package at.fhv.roomanizer.domain.person;

/**
 * Represent a user, almost everytime an employee.
 * 
 * @author Daniel Rotter <daniel.rotter@students.fhv.at>
 */
public class User implements IUser {
	/**
	 * The id of the user
	 */
	private int _id;
	/**
	 * The username of the user
	 */
	private String _username;
	/**
	 * The password of the user
	 */
	private String _password;
	/**
	 * Whether the user is active or not
	 */
	private boolean _active;
	
	
	/**
	 * @see IUser#getId()
	 */
	public int getId(){
		return _id;
	}
	
	/**
	 * Sets the id of the user
	 * @param id The id of the user
	 */
	public void setId(int id){
		_id = id;
	}
	
	/**
	 * Returns the username of the user
	 * @return The username of the user
	 */
	public String getUsername(){
		return _username;
	}
	
	/**
	 * Sets the username of the user
	 * @param username The username of the user
	 */
	public void setUsername(String username){
		_username = username;
	}
	
	/**
	 * Returns the password of the user
	 * @return The password of the user
	 */
	public String getPassword(){
		return _password;
	}
	
	/**
	 * Sets the password of the user
	 * @param password The password of the user
	 */
	public void setPassword(String password){
		_password = password;
	}
	
	/**
	 * Returns whether the user is active or not
	 * @return Whether the user is active or not
	 */
	public boolean getActive(){
		return _active;
	}
	
	/**
	 * Sets whether the user is active or not
	 * @param active Whether the user is active or not
	 */
	public void setActive(boolean active){
		_active = active;
	}
}
