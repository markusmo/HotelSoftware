/**
 * 
 */
package at.fhv.roomanizer.application;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import at.fhv.roomanizer.application.exceptions.ArrivalAfterLeavingException;
import at.fhv.roomanizer.application.exceptions.NoRoomException;
import at.fhv.roomanizer.application.exceptions.UnderMinimumPriceException;
import at.fhv.roomanizer.domain.Habitation;
import at.fhv.roomanizer.domain.person.Address;
import at.fhv.roomanizer.domain.person.Guest;
import at.fhv.roomanizer.domain.person.User;
import at.fhv.roomanizer.domain.reservation.IReservation;
import at.fhv.roomanizer.domain.reservation.Reservation;
import at.fhv.roomanizer.domain.room.IPrice;
import at.fhv.roomanizer.domain.room.IRoom;
import at.fhv.roomanizer.domain.room.Room;
import at.fhv.roomanizer.domain.room.Status;
import at.fhv.roomanizer.domain.service.Type;
import at.fhv.roomanizer.persistence.ManagerFactory;
import at.fhv.roomanizer.persistence.manager.*;

/**
 * The CheckInController is responsible for the correct sequence of functions
 * during the Check-In. The functions will get called by the GUI Controller and
 * it will send or get the requested data. At the end of the sequence an
 * habitation object should be going to the persistance layer.
 * 
 * @author phils
 * 
 */
public class CheckInController {

	ReservationController _resController;
	HabitationController _habController;
	CategoryController _catController;

	/**
	 * An enum for showing the current state of the checkin process
	 */
	public CheckInStatus _status;

	/**
	 * creates needed instances of the Controller
	 */
	public CheckInController() {
		_resController = new ReservationController();
		_habController = new HabitationController();
		_catController = new CategoryController();
	}

	/**
	 * starting the Check In use case
	 */
	public void start() {
		// set state on start
		_status = CheckInStatus.start;
	}

	/**
	 * Returns true if the Check In has run all functions
	 * 
	 * @return a value showing if the functions has run
	 */
	public boolean end() {
		if (_status.equals(CheckInStatus.habitationCreated)) {
			return true;
		}
		return false;
	}

	/*--------------------loading Data for the Check - In--------------------------*/
	/**
	 * Loads a specific Reservation
	 * 
	 * @param reservationid
	 * @return a IReservation
	 * @throws ClassCastException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public IReservation loadReservation(int reservationid)
			throws ClassCastException, InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException {
		if (_status.equals(CheckInStatus.start)) {
			_status = CheckInStatus.reservationDataLoaded;
			return _resController.loadReservation(reservationid);
		}
		return null;
	}

	/**
	 * Loads all available Rooms for one Reservation
	 * 
	 * @deprecated As of loading available Rooms a few times(call the
	 *             CategoryController instead and then loadedAvailableRooms())
	 * @param start
	 * @param end
	 * @param reservationid
	 * @return a list of rooms
	 * @throws Exception
	 */
	@Deprecated
	public List<IRoom> loadAvailableRooms(Date start, Date end,
			String categoryName, int amount, int reservationid)
			throws Exception {
		if (_status.equals(CheckInStatus.reservationDataLoaded)) {
			_status = CheckInStatus.availableRoomsChecked;
			return _catController.availableRooms(start, end, categoryName,
					amount);
		}
		return null;
	}

	/**
	 * Changes the current status of the checkin process
	 * 
	 * @return a value of changing the current status
	 */
	public boolean loadedAvailableRooms() {
		if (_status.equals(CheckInStatus.reservationDataLoaded)) {
			try {
				_status = CheckInStatus.availableRoomsChecked;
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/*--------------------setting Data for the Check - In--------------------------*/

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
	 * loading Reservation for change
	 * 
	 * @param firstname
	 * @param lastname
	 * @return a reservation
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private Reservation loadReservationForChange(String firstname,
			String lastname) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException {
		List<Reservation> allReservations = loadReservationsForChange();

		for (Reservation Reservation : allReservations) {
			if (Reservation.getGuest().getFirstName().equals(firstname)
					&& Reservation.getGuest().getLastName().equals(lastname)) {
				return Reservation;
			}
		}

		return null;
	}

	/**
	 * Is creating a Habitation
	 * 
	 * @param start
	 * @param end
	 * @param price
	 * @param deposit
	 * @param created
	 * @param roomnumber
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
	public boolean createHabitation(/* Habitation: */Date start, Date end,
			Double price, String deposit, Date created /* room: */,
			String roomnumber, /* guest: */String firstname, String lastname,
			Date birthday,/* address: */String street, String city, String zip,
			String email, String phone, String fax, /*country: */String countryname, String countrynameshort) throws Exception {
		if (_status.equals(CheckInStatus.availableRoomsChecked)) {
			// if the start date is after the end date throw an exception
			if (start.after(end)) {
				throw new ArrivalAfterLeavingException(
						"The start-date is after the end-date");
			}

			IHabitationManager habManager = ManagerFactory
					.getHabitationManager();

			// creating habitation
			Habitation tempHabitation = new Habitation();

			// getting room from persistance
			Room temproom = null;
			if (!roomnumber.equals("")) {
				temproom = (Room) _catController.getRoom(roomnumber);
				Status tempStatus = _catController.loadStates("Occupied - Clean");
				temproom.addStatus(tempStatus, start, end);
				tempHabitation.setRoom(temproom);
			}else{
				throw new NoRoomException("no room was chosen");
			}

			if (start != null) {
				tempHabitation.setStart(start);
			}
			if (end != null) {
				tempHabitation.setEnd(end);
			}
			if (price != null) {

				CategoryController catController = new CategoryController();
				IPrice p = catController.loadPriceForSeason(start, temproom
						.getCategory().getName());
				if (p.getPriceMin() > price) {
					throw new UnderMinimumPriceException("the price is under the minimum");
				}
				tempHabitation.setPrice(price);
			}
			if (!deposit.equals("")) {
				tempHabitation.setDeposit(deposit);
			} else {
				tempHabitation.setDeposit("no deposit");
			}
			if (created != null) {
				tempHabitation.setCreated(created);
			}

			// adding address
			Address tempAddress = loadReservationForChange(firstname, lastname)
					.getGuest().getAddress();// new Address();
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
			if(!countryname.equals("")){
				tempAddress.getCountry().setName(countryname);
			}
			if (!countrynameshort.equals("")) {
				tempAddress.getCountry().setShortName(countrynameshort);
			}

			// adding guest
			Guest tempguest = loadReservationForChange(firstname, lastname)
					.getGuest();// new Guest();
			if (!firstname.equals("")) {
				tempguest.setFirstName(firstname);
			}
			if (!lastname.equals("")) {
				tempguest.setLastName(lastname);
			}
			if (birthday != null) {
				tempguest.setBirthday(birthday);
			}

			tempguest.setAddress(tempAddress);
			tempHabitation.addGuest(tempguest);

			// save
			// save address
			PersonManager perManager = ManagerFactory.getPersonmanager();
			perManager.saveAddress(tempAddress);

			// save person
			perManager.saveGuest(tempguest);

			UserController uc = new UserController();
			User tempUser = (User) uc.loadFirstUser();

			tempHabitation.setUser(tempUser);

			ServiceController sc = new ServiceController();
			Type t = sc.loadTypeByName("Habitation");
			tempHabitation.setType(t);
			
			IRoomManager roomManager = ManagerFactory.getRoomManager();
			roomManager.saveRoomStatus(temproom.getStatus().get(temproom.getStatus().size() - 1));
			
			habManager.saveHabitation(tempHabitation);

			return true;
		}
		return false;
	}

	/**
	 * changes the status of the process to habitationcreated
	 * 
	 * @return a value if all habatitations have been created
	 */
	public boolean createdAllHabitation() {
		if (_status.equals(CheckInStatus.availableRoomsChecked)) {
			_status = CheckInStatus.habitationCreated;
			return true;
		}
		return false;
	}

}
