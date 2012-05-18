package at.fhv.roomanizer.persistence.entity.person;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Represents a guest of the hotel. A person is a guest, which stays at the hotel, and
 * uses its services.
 * 
 * @author Daniel Rotter <daniel.rotter@students.fhv.at>
 * @author Andreas Sinz <andreas.sinz@students.fhv.at>
 */
@Entity
@Table(name="guests")
@PrimaryKeyJoinColumn(name="idParties")
public class GuestEntity extends PartyEntity {

	/**
	 * The first name of the guest
	 */
	@Column(name="fname")
	private String _firstName;
	/**
	 * The last name of the guest
	 */
	@Column(name="lname")
	private String _lastName;
	/**
	 * The birthday of the guest
	 */
	@Column(name="birthday")
	private Date _birthday;
	
	/**
	 * Sets the first name of the person
	 * @param firstName The first name of the person
	 */
	public void setFirstName(String firstName){
		_firstName = firstName;
	}
	
	/**
	 * Returns the first name of the person
	 * @return first name of the person
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
	 * Returns the last name of the person
	 * @return The last name of the person
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
	 * Returns the birthday of the guest
	 * @return The birthday of the guest
	 */
	public Date getBirthday(){
		return _birthday;
	}
}
