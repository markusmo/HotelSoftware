package at.fhv.roomanizer.domain.person;

import java.util.Date;

public interface IGuest extends IParty {
	/**
	 * Returns the first name of the person
	 * @return The first name of the person
	 */
	public String getFirstName();	
	/**
	 * Returns the last name of the person
	 * @return The last name of the person
	 */
	public String getLastName();
	
	/**
	 * Returns the birthday of the guest
	 * @return The birthday of the guest
	 */
	public Date getBirthday();
}
