package at.fhv.roomanizer.application;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.fhv.roomanizer.domain.Habitation;
import at.fhv.roomanizer.domain.IHabitation;
import at.fhv.roomanizer.domain.person.Address;
import at.fhv.roomanizer.domain.person.Country;
import at.fhv.roomanizer.domain.person.Guest;
import at.fhv.roomanizer.domain.person.IGuest;
import at.fhv.roomanizer.persistence.ManagerFactory;
import at.fhv.roomanizer.persistence.manager.HabitationManager;
import at.fhv.roomanizer.persistence.manager.IHabitationManager;
import at.fhv.roomanizer.persistence.manager.PersonManager;

/**
 * The HabitationController is responsible for correctly creating a Habitation.
 * 
 * @author phils
 */
public class HabitationController {
	/*--------------------loading Data for habitation--------------------------*/
	/**
	 * Returns a list of all Habitations
	 * 
	 * @return a list of Habitations
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public List<IHabitation> loadAllHabitations()
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
		IHabitationManager habManager = ManagerFactory.getHabitationManager();
		List<Habitation> allHabitations = habManager.getAllHabitations();
		List<IHabitation> allIHabitations = new ArrayList<IHabitation>();
		for (Habitation h : allHabitations) {
			allIHabitations.add((IHabitation) h);
		}
		return allIHabitations;
	}

	/**
	 * Returns a Habitation
	 * 
	 * @param habitationid
	 * @return a Habitation
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public IHabitation loadHabitation(int habitationid)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
		List<IHabitation> allHabitations = loadAllHabitations();
		for (IHabitation habitation : allHabitations) {
			if (habitation.getId() == habitationid) {
				return habitation;
			}
		}
		return null;
	}

	/**
	 * Returns a list of Guests of a habitation
	 * 
	 * @param habitationid
	 * @return a list of Guests
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public List<IGuest> loadGuests(int habitationid)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
		List<Guest> allGuests = loadHabitation(habitationid).getGuests();
		List<IGuest> allIGuests = new ArrayList<IGuest>();
		for (Guest g : allGuests) {
			allIGuests.add(g);
		}
		return allIGuests;
	}

	/*--------------------setting Data for the Habitation--------------------------*/
	/**
	 * private function loads a list for changing it
	 * 
	 * @return list of habitations
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	private List<Habitation> loadAllHabitationsforChange()
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
		IHabitationManager habManager = ManagerFactory.getHabitationManager();
		List<Habitation> allHabitations = habManager.getAllHabitations();
		return allHabitations;
	}

	/**
	 * private funcation for getting an habitation
	 * 
	 * @param habitationid
	 * @return habitation
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	protected Habitation loadHabitationforChange(int habitationid)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
		List<Habitation> allHabitations = loadAllHabitationsforChange();
		for (Habitation habitation : allHabitations) {
			if (habitation.getId() == habitationid) {
				return habitation;
			}
		}
		return null;
	}

	/**
	 * Add a new Guest
	 * 
	 * @param habitationid
	 * @param firstname
	 * @param lastname
	 * @param birthday
	 * @param street
	 * @param city
	 * @param zip
	 * @param email
	 * @param phone
	 * @param fax
	 * @throws Exception
	 */
	public void addGuest(int habitationid, String firstname, String lastname,
			Date birthday, String street, String city, String zip,
			String email, String phone, String fax, String countryname) throws Exception {

		Habitation tempHabitation = loadHabitationforChange(habitationid);
		Guest tempguest = new Guest();

		if (!firstname.equals("")) {
			tempguest.setFirstName(firstname);
		}
		if (!lastname.equals("")) {
			tempguest.setLastName(lastname);
		}
		if (birthday != null) {
			tempguest.setBirthday(birthday);
		}

		Address tempAddress = new Address();

		if (!street.equals("")) {
			tempAddress.setStreet(street);
		}
		if (!city.equals("")) {
			tempAddress.setCity(city);
		}
		if (!zip.equals("")) {
			tempAddress.setZip(zip);
		}
		if (!email.equals("")) {
			tempAddress.setEmail(email);
		}
		if (!phone.equals("")) {
			tempAddress.setPhone(phone);
		}
		if (!fax.equals("")) {
			tempAddress.setFax(fax);
		}
		if (!countryname.equals("")) {
			CountryController cc = new CountryController();
			Country c = (Country) cc.loadCountryByName(countryname);
			tempAddress.setCountry(c);
		}

		tempguest.setAddress(tempAddress);

		tempHabitation.addGuest(tempguest);

		// save address
		PersonManager perManager = ManagerFactory.getPersonmanager();
		perManager.saveAddress(tempAddress);

		// save person
		perManager.saveGuest(tempguest);

		// save habitation
		IHabitationManager habManager = ManagerFactory.getHabitationManager();
		habManager.saveHabitation(tempHabitation);

	}

	public List<IHabitation> getActiveHabitationsList(Date currentDate) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
		IHabitationManager habitationManager = ManagerFactory.getHabitationManager();
		List<Habitation> tmpList = habitationManager.getHabitationsByDate(currentDate);

		List<IHabitation> iHabitationList = new ArrayList<IHabitation>();
		for(Habitation item : tmpList) {
			iHabitationList.add(item);
		}

		return iHabitationList;
	}
}
