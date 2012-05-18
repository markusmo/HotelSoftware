package at.fhv.roomanizer.application;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import at.fhv.roomanizer.application.exceptions.ArrivalAfterLeavingException;
import at.fhv.roomanizer.application.exceptions.NoIReservationFoundException;
import at.fhv.roomanizer.domain.person.Address;
import at.fhv.roomanizer.domain.person.Country;
import at.fhv.roomanizer.domain.person.Guest;
import at.fhv.roomanizer.domain.person.IAddress;
import at.fhv.roomanizer.domain.person.IGuest;
import at.fhv.roomanizer.domain.person.IParty;
import at.fhv.roomanizer.domain.person.Party;
import at.fhv.roomanizer.domain.reservation.IOption;
import at.fhv.roomanizer.domain.reservation.IReservation;
import at.fhv.roomanizer.domain.reservation.IReservationItem;
import at.fhv.roomanizer.domain.reservation.Option;
import at.fhv.roomanizer.domain.reservation.Reservation;
import at.fhv.roomanizer.domain.reservation.ReservationItem;
import at.fhv.roomanizer.domain.room.Category;
import at.fhv.roomanizer.persistence.ManagerFactory;
import at.fhv.roomanizer.persistence.manager.PersonManager;
import at.fhv.roomanizer.persistence.manager.ReservationManager;

/**
 * The ReservationController is responsible for the correct sequence of
 * functions during the Reservation. The functions will get called by the GUI
 * Controller and will send him the requested data.
 * 
 * @author phils
 * 
 */
public class ReservationController {

	/*------ loading Data from Reservation --------*/
	/**
	 * Returns a list of IReservation converted from the the persistance layer
	 * 
	 * @return List of IReservations
	 * @throws ClassCastException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public List<IReservation> loadReservations() throws ClassCastException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException, IllegalArgumentException,
			InvocationTargetException {
		ReservationManager revManager = ManagerFactory.getReserveationManager();
		LinkedList<IReservation> allReservations = new LinkedList<IReservation>();

		for (IReservation iR : revManager.getAllReservations()) {
			allReservations.add(iR);
		}

		return allReservations;
	}

	/**
	 * Returns a list of IReservation converted from the the persistance layer
	 * 
	 * @param date
	 * @return a list of IReservation
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public List<IReservation> loadReservations(Date date)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IllegalArgumentException,
			InvocationTargetException {
		ReservationManager revManager = ManagerFactory.getReserveationManager();
		LinkedList<IReservation> allReservations = new LinkedList<IReservation>();

		for (IReservation iR : revManager.getFutureReservations(date)) {
			allReservations.add(iR);
		}

		return allReservations;
	}

	/**
	 * Returns a IReservation searched by the id
	 * 
	 * @param reservationid
	 * @return specific IReservation
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassCastException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public IReservation loadReservation(int reservationid)
			throws ClassCastException, InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException {
		List<IReservation> allReservations = loadReservations();

		for (IReservation iReservation : allReservations) {
			if (iReservation.getId() == reservationid) {
				return iReservation;
			}
		}

		return null;
	}

	/**
	 * Returns a IReservation searched by the first- and lastname of a person
	 * 
	 * @param firstname
	 * @param lastname
	 * @return specific IReservation
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassCastException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public IReservation loadReservation(String firstname, String lastname)
			throws ClassCastException, InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException {
		List<IReservation> allReservations = loadReservations();

		for (IReservation iReservation : allReservations) {
			if (iReservation.getIGuest().getFirstName().equals(firstname)) {
				if (iReservation.getIGuest().getLastName().equals(lastname)) {
					return iReservation;
				}
			}
		}

		return null;
	}

	/**
	 * Returns a List of IReservations searched by lastname
	 * 
	 * @param lastname
	 * @return a list of IReservation
	 * @throws ClassCastException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoIReservationFoundException
	 */
	public List<IReservation> searchReservation(String lastname)
			throws ClassCastException, InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException,
			NoIReservationFoundException {
		List<IReservation> allReservations = loadReservations();
		List<IReservation> searchedReservations = new ArrayList<IReservation>();

		for (IReservation iR : allReservations) {
			if (iR.getIGuest().getLastName().equals(lastname)) {
				searchedReservations.add(iR);
			}
		}

		if (searchedReservations == null) {
			throw new NoIReservationFoundException("no reservation was found");
		}
		return searchedReservations;
	}

	/**
	 * Returns a list of Reservations searched by Reservation Id and/or Lastname
	 * 
	 * @param reservationNumber
	 *            if empty ("") no number will be searched
	 * @param lastname
	 *            if empty ("") no lastname will be searched
	 * @return a list of searched reservations
	 * @throws ClassCastException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoIReservationFoundException
	 */
	public List<IReservation> searchReservation(String reservationNumber, String lastname) throws ClassCastException, InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
		List<IReservation> allReservations = loadReservations();
		List<IReservation> searchedReservation = new ArrayList<IReservation>();
		for (IReservation ir : allReservations) {
			if ((lastname.equals("") || ir.getIGuest().getLastName()
					.equals(lastname))
					&& (reservationNumber.equals("") || ir.getNumber().equals(
							reservationNumber))) {
				searchedReservation.add(ir);
			}
		}

		return searchedReservation;
	}

	/**
	 * Returns a sorted list by date and lastname
	 * 
	 * @param date
	 * @param LastName
	 * @return a sorted reservationlist
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public List<IReservation> searchReservation(Date date, String LastName)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IllegalArgumentException,
			InvocationTargetException {
		ReservationManager revManager = ManagerFactory.getReserveationManager();
		LinkedList<IReservation> allReservations = new LinkedList<IReservation>();
		List<Reservation> searchedReservations = revManager
				.getFutureReservationsByName(date, LastName);

		for (IReservation ir : searchedReservations) {
			allReservations.add(ir);
		}

		return allReservations;
	}

	/**
	 * Returns the Person included in a Reservation
	 * 
	 * @param firstname
	 * @param lastname
	 * @return Person in a Reservation
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassCastException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public IGuest loadGuest(String firstname, String lastname)
			throws ClassCastException, InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException {
		IReservation tempIReservation = loadReservation(firstname, lastname);
		return tempIReservation.getIGuest();
	}

	/**
	 * loads all guests
	 * 
	 * @return a list of iguests
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * 
	 */
	public List<IGuest> loadAllGuests() throws IllegalArgumentException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, InvocationTargetException {
		PersonManager persManager = ManagerFactory.getPersonmanager();
		List<IGuest> allIGuests = new ArrayList<IGuest>();
		for (IGuest iG : persManager.getGuestList()) {
			allIGuests.add(iG);
		}
		return allIGuests;
	}

	/**
	 * Searches a Guest via LastName
	 * 
	 * @param LastName
	 * @return a list of Guests
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public List<IGuest> searchGuest(String LastName)
			throws IllegalArgumentException, ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {
		List<IGuest> allGuests = loadAllGuests();
		List<IGuest> filteredGuests = new ArrayList<IGuest>();
		for (IGuest iG : allGuests) {
			if (iG.getLastName().equals(LastName)) {
				filteredGuests.add(iG);
			}
		}
		return filteredGuests;
	}

	/**
	 * Returns the Person included in a Reservation
	 * 
	 * @param reservationid
	 * @return Person in a Reservation
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassCastException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public IGuest loadGuest(int reservationid) throws ClassCastException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException, IllegalArgumentException,
			InvocationTargetException {
		IReservation tempIReservation = loadReservation(reservationid);
		return tempIReservation.getIGuest();
	}

	/**
	 * Returns the Address of a person
	 * 
	 * @param firstname
	 * @param lastname
	 * @return address of a person
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassCastException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */

	public IAddress loadGuestAddress(String firstname, String lastname)
			throws ClassCastException, InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException {
		IGuest tempIPerson = loadGuest(firstname, lastname);
		return tempIPerson.getIAddress();
	}

	/**
	 * Returns the Address of a person
	 * 
	 * @param reservationid
	 * @return address of a person
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassCastException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */

	public IAddress loadGuestAddress(int reservationid)
			throws ClassCastException, InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException {
		IParty tempIPerson = loadGuest(reservationid);
		return tempIPerson.getIAddress();
	}

	/**
	 * Returns the list of options according to a reservation, searched by id
	 * 
	 * @param reservationid
	 * @return a list of Options
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassCastException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public List<IOption> loadOptions(int reservationid)
			throws ClassCastException, InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException {
		IReservation tempIReservation = loadReservation(reservationid);
		return tempIReservation.getIOptions();
	}

	/**
	 * Returns the list of options according to a reservation, searched by
	 * first- and lastname
	 * 
	 * @param firstname
	 * @param lastname
	 * @return a list of Options
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassCastException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public List<IOption> loadOptions(String firstname, String lastname)
			throws ClassCastException, InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException {
		IReservation tempIReservation = loadReservation(firstname, lastname);
		return tempIReservation.getIOptions();
	}

	/**
	 * Returns a list of ReservationItems according to a reservation, searched
	 * by id
	 * 
	 * @param reservationid
	 * @return a list of ReservationItems
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassCastException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */

	public List<IReservationItem> getReservationItems(int reservationid)
			throws ClassCastException, InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException {
		IReservation tempIReservation = loadReservation(reservationid);
		return tempIReservation.getIReservationItems();
	}

	/**
	 * Returns a list of ReservationItems according to a reservation, searched
	 * by first- and lastname
	 * 
	 * @param firstname
	 * @param lastname
	 * @return a list of ReservationItems
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassCastException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */

	public List<IReservationItem> getReservationItems(String firstname,
			String lastname) throws ClassCastException, InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException {
		IReservation tempIReservation = loadReservation(firstname, lastname);
		return tempIReservation.getIReservationItems();
	}

	/*------ changing Data in the Reservation --------*/

	/**
	 * loading all reservations for getting one reservation to change it
	 * 
	 * @return a list of reservations for changing the reservations
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	private List<Reservation> loadReservationsForChange()
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IllegalArgumentException,
			InvocationTargetException {
		ReservationManager revManager = ManagerFactory.getReserveationManager();
		List<Reservation> allReservations = revManager.getAllReservations();

		return allReservations;
	}

	/**
	 * loading a Reservation for change
	 * 
	 * @param reservationid
	 * @return a reservation for change
	 * @throws ClassCastException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	private Reservation loadReservationForChange(int reservationid)
			throws ClassCastException, InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException {
		List<Reservation> allReservations = loadReservationsForChange();

		for (Reservation Reservation : allReservations) {
			if (Reservation.getId() == reservationid) {
				return Reservation;
			}
		}

		return null;
	}

	/**
	 * Loading a person for the Change
	 * 
	 * @param reservationid
	 * @return a person for internal change
	 * @throws ClassCastException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	private Guest loadPersonForChange(int reservationid)
			throws ClassCastException, InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException {
		Reservation resTemp = loadReservationForChange(reservationid);
		return resTemp.getGuest();
	}
	
	/**
	 * Returns the Person included in a Reservation
	 * 
	 * @param firstname
	 * @param lastname
	 * @return Person in a Reservation
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassCastException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public Guest loadGuestforChange(String firstname, String lastname)
			throws ClassCastException, InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException {
		PersonManager pm = ManagerFactory.getPersonmanager();
		List<Guest> guestlist = pm.getGuestList();
		for (Guest g : guestlist) {
			if (g.getFirstName().equals(firstname) && g.getLastName().equals(lastname)) {
				return g;
			}
		}
		return null;
	}

	/**
	 * For changing the details of a person
	 * 
	 * @param reservationtid
	 * @param firstname
	 * @param lastname
	 * @throws Exception
	 */
	public void changePerson(int reservationtid, String firstname,
			String lastname) throws Exception {

		Reservation tempReservation = loadReservationForChange(reservationtid);
		Guest tempPerson = tempReservation.getGuest();

		if (!firstname.equals("")) {
			tempPerson.setFirstName(firstname);
		}
		if (!lastname.equals("")) {
			tempPerson.setLastName(lastname);
		}

		tempReservation.setGuest(tempPerson);
		ReservationManager revManager = ManagerFactory.getReserveationManager();
		revManager.saveReservation(tempReservation);
	}

	/**
	 * For changing the details of the address of a person
	 * 
	 * @param reservationid
	 * @param street
	 * @param city
	 * @param zip
	 * @param email
	 * @param phone
	 * @param fax
	 * @throws Exception
	 */
	public void changeAddress(int reservationid, String street, String city,
			String zip, String email, String phone, String fax)
			throws Exception {
		Reservation tempReservation = loadReservationForChange(reservationid);
		Guest tempPerson = tempReservation.getGuest();
		Address tempAddress = tempPerson.getAddress();

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

		tempPerson.setAddress(tempAddress);
		tempReservation.setGuest(tempPerson);

		ReservationManager revManager = ManagerFactory.getReserveationManager();
		revManager.saveReservation(tempReservation);
	}

	/**
	 * For changing details in the OPtion of a Reservation
	 * 
	 * @param reservationid
	 * @param expiration
	 * @param fulfilled
	 * @param prePayment
	 * @throws Exception
	 */
	public void addOption(int reservationid, Date expiration,
			boolean fulfilled, double prePayment) throws Exception {
		Reservation tempReservation = loadReservationForChange(reservationid);

		Option tempoption = new Option();
		tempoption.setExpiration(expiration);
		tempoption.setFulfilled(fulfilled);
		tempoption.setPrePayment(prePayment);

		tempReservation.addOption(tempoption);

		ReservationManager revManager = ManagerFactory.getReserveationManager();
		revManager.saveReservation(tempReservation);
	}

	/**
	 * For adding an Reservationitem
	 * 
	 * @param reservationid
	 * @param amount
	 * @param category
	 * @throws Exception
	 */
	public void addReservationItem(int reservationid, int amount,
			Category category) throws Exception {
		Reservation tempReservation = loadReservationForChange(reservationid);

		tempReservation.createItem(category, amount);

		ReservationManager revManager = ManagerFactory.getReserveationManager();
		revManager.saveReservation(tempReservation);
	}

	/**
	 * For changing details of the Reservation itself
	 * 
	 * @param reservationid
	 * @param number
	 * @param start
	 * @param end
	 * @param comment
	 * @param created
	 * @throws Exception
	 */
	public void changeReservation(int reservationid, String number, Date start,
			Date end, String comment, Date created) throws Exception {
		Reservation tempReservation = loadReservationForChange(reservationid);

		if (!number.equals("")) {
			tempReservation.setNumber(number);
		}
		if (start != null) {
			tempReservation.setStart(start);
		}
		if (end != null) {
			tempReservation.setEnd(end);
		}
		if (!comment.equals("")) {
			tempReservation.setComment(comment);
		}
		if (created != null) {
			tempReservation.setCreated(created);
		}

		ReservationManager revManager = ManagerFactory.getReserveationManager();
		revManager.saveReservation(tempReservation);
	}

	/**
	 * save entire Reservation with all changes
	 * 
	 * @param reservationtid
	 * @param firstname
	 * @param lastname
	 * @param street
	 * @param city
	 * @param zip
	 * @param email
	 * @param phone
	 * @param fax
	 * @param number
	 * @param start
	 * @param end
	 * @param comment
	 * @param created
	 * @return true if no exception was thrown
	 * @throws Exception
	 */
	public Boolean saveReservation(int reservationtid, String firstname,
			String lastname, String street, String city, String zip,
			String email, String phone, String fax, String number, Date start,
			Date end, String comment, Date created, String countryname)
			throws Exception {

		// if the start date is after the end date throw an exception
		if (start.after(end)) {
			throw new ArrivalAfterLeavingException(
					"The start-date is after the end-date");
		}

		Reservation tempReservation;

		try {
			PersonManager persManager = ManagerFactory.getPersonmanager();

			tempReservation = loadReservationForChange(reservationtid);
			//should be id....
			Guest tempPerson = loadGuestforChange(firstname, lastname);

			if (!firstname.equals("")) {
				tempPerson.setFirstName(firstname);
			}
			if (!lastname.equals("")) {
				tempPerson.setLastName(lastname);
			}
			persManager.saveGuest(tempPerson);

			tempReservation.setGuest(tempPerson);

			Address tempAddress = tempPerson.getAddress();
			Country tempCountry = tempAddress.getCountry();

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
				tempCountry = (Country) cc.loadCountryByName(countryname);
			}
			
			tempAddress.setCountry(tempCountry);
			persManager.saveAddress(tempAddress);

			tempPerson.setAddress(tempAddress);
			tempReservation.setGuest(tempPerson);

			if (!number.equals("")) {
				tempReservation.setNumber(number);
			}
			if (start != null) {
				tempReservation.setStart(start);
			}
			if (end != null) {
				tempReservation.setEnd(end);
			}
			if (!comment.equals("")) {
				tempReservation.setComment(comment);
			}
			if (created != null) {
				tempReservation.setCreated(created);
			}

			ReservationManager revManager = ManagerFactory
					.getReserveationManager();

			revManager.saveReservation(tempReservation);

			return true;

		} catch (ClassCastException e) {
			throw e;
		} catch (InstantiationException e) {
			throw e;
		} catch (IllegalAccessException e) {
			throw e;
		} catch (ClassNotFoundException e) {
			throw e;
		} finally {
			return false;
		}
	}

}
