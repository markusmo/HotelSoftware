package at.fhv.roomanizer.domain.person;

import java.util.Date;

/**
 * Represents a guest of the hotel. A person is a guest, which stays at the hotel, and
 * uses its services.
 * 
 * @author Daniel Rotter <daniel.rotter@students.fhv.at>
 */
public class Guest extends Party implements IGuest {
	/**
	 * The first name of the guest
	 */
	private String _firstName;
	/**
	 * The last name of the guest
	 */
	private String _lastName;
	/**
	 * The birthday of the guest
	 */
	private Date _birthday;
	
	/**
	 * Sets the first name of the person
	 * @param firstName The first name of the person
	 */
	public void setFirstName(String firstName){
		_firstName = firstName;
	}
	
	/**
	 * @see IGuest#getFirstName()
	 */
	public String getFirstName(){
		return _firstName;
	}
	
	/**
	 * Sets the last name of the person
	 * @param lastName The last name of the person
	 */
	public void setLastName(String lastName){
		_lastName = lastName;
	}
	
	/**
	 * @see IGuest#getLastName()
	 */
	public String getLastName(){
		return _lastName;
	}
	
	/**
	 * Sets the birthday of the guest
	 * @param birthday The birthday of the guest
	 */
	public void setBirthday(Date birthday){
		_birthday = birthday;
	}
	
	/**
	 * @see IGuest#getBirthday()
	 */
	public Date getBirthday(){
		return _birthday;
	}
}
