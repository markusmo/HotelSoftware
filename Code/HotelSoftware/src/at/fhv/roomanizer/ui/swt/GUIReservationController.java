package at.fhv.roomanizer.ui.swt;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import at.fhv.roomanizer.application.CategoryController;
import at.fhv.roomanizer.application.CheckInController;
import at.fhv.roomanizer.application.ReservationController;
import at.fhv.roomanizer.application.exceptions.ArrivalAfterLeavingException;
import at.fhv.roomanizer.application.exceptions.NoRoomException;
import at.fhv.roomanizer.application.exceptions.UnderMinimumPriceException;
import at.fhv.roomanizer.domain.person.IGuest;
import at.fhv.roomanizer.domain.reservation.IReservation;
import at.fhv.roomanizer.domain.reservation.IReservationItem;
import at.fhv.roomanizer.domain.room.ICategory;
import at.fhv.roomanizer.ui.swt.errors.ShowError;
import at.fhv.roomanizer.ui.swt.reservation.ReservationDetailView;
import at.fhv.roomanizer.ui.swt.reservation.ReservationView;
import at.fhv.roomanizer.ui.swt.reservation.dialogs.AutoFillDialog;
import at.fhv.roomanizer.ui.swt.reservation.dialogs.CheckInDialog;
import at.fhv.roomanizer.ui.swt.reservation.dialogs.CheckInRoom;
import at.fhv.roomanizer.ui.swt.success.ShowSuccess;

/**
 * GUIReservationController The GUIReservationController is the key
 * part between the view and the controllers of the applicationlayer.
 * The GUIReservationController implements the listener for the
 * reservationview.
 * 
 * @author Raf 
 */
public class GUIReservationController {

	/**
	 * create the GUIReservationController
	 */
	private static GUIReservationController guiControllerInstance = new GUIReservationController();

	/**
	 * Static method returns the only instance
	 */
	public static GUIReservationController getInstance() {
		return guiControllerInstance;
	}

	CheckInController _checkInController;

	ReservationView _reservationView;
	Table _table;
	AutoFillDialog _autoFillDialog;

	private IReservation _selectedReservation;
	private int _selectedReservationId;
	private Integer _selectedReservationRoomsAmmount;
	List<IReservation> _iReservationsList = new ArrayList<IReservation>();

	public static List<Combo> _selectedReservationRoomComboBox = new ArrayList<Combo>();
	public static List<Text> _selectedReservationPricesField = new ArrayList<Text>();

	public static CheckInDialog _checkInDialog;
	public static Text _depositeTextField;

	private static Table _guestTable;
	

	/**
	 * Setters
	 */
	ReservationDetailView _detailView;

	/**
	 * Sets the detail view to the controller
	 * @param _detailView The detail view of the reservation
	 */
	public static void set_detailView(ReservationDetailView _detailView) {
		guiControllerInstance._detailView = _detailView;
	}

	Button _cancle;

	/**
	 * @param _cancle
	 */
	public static void set_cancle(Button _cancle) {
		guiControllerInstance._cancle = _cancle;
	}

	Button _checkIn;

	/**
	 * @param _checkIn
	 */
	public static void set_checkIn(Button _checkIn) {
		guiControllerInstance._checkIn = _checkIn;
	}

	Button _update;

	/**
	 * @param update
	 */
	public static void set_update(Button update) {
		guiControllerInstance._update = update;
	}

	Button _autoFill;

	public static void set_autoFill(Button autoFill) {
		guiControllerInstance._autoFill = autoFill;
	}

	public static void init(ReservationView reservationview) {
		try {
			guiControllerInstance._reservationView = reservationview;
			// Create an instance of the reservation controller
			ReservationController reservationController = new ReservationController();
			// get the list of reservations
			guiControllerInstance._iReservationsList = reservationController
					.loadReservations();
			// paint the reservations in the table
			reservationview
					.paintTable(guiControllerInstance._iReservationsList);
		} catch (ClassCastException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Default-Constructor, no access from outside.
	 */
	private GUIReservationController() {
	}

	/**
	 * Enable the buttons of the reservation view
	 */
	private static void enableButtons() {
		guiControllerInstance._cancle.setEnabled(true);
		guiControllerInstance._checkIn.setEnabled(true);
		guiControllerInstance._update.setEnabled(true);
		guiControllerInstance ._autoFill.setEnabled(true);
	}

	/**
	 * Disable the buttons of the reservation view
	 */
	private static void disableButtons() {
		guiControllerInstance._cancle.setEnabled(false);
		guiControllerInstance._checkIn.setEnabled(false);
		guiControllerInstance._update.setEnabled(false);
		guiControllerInstance ._autoFill.setEnabled(false);
	}

	/**
	 * Methods to add Listener to different parts of the reservation view
	 */

	public static void addSelectionListnerToReservationTable(Table table) {
		guiControllerInstance._table = table;
		table.addListener(SWT.Selection,
				guiControllerInstance.new ReservationSelectionListener());
	}

	public static void addUpdateListener(Button button) {
		button.addListener(SWT.MouseDown,
				guiControllerInstance.new ReservationSaveListener());
	}

	public static void addAutoDialogFillListener(Button autoFill) {
		autoFill.addListener(SWT.MouseDown,guiControllerInstance.new AutoFillDialogListener());

	}

	public static void addAutoFillListener(Button autoFill, Table table) {
		autoFill.addListener(SWT.MouseDown,guiControllerInstance.new AutoFillListener());
		guiControllerInstance._guestTable = table;
	}

	public static void addCheckDialogInListener(Button button) {
		button.addListener(SWT.MouseDown,
				guiControllerInstance.new CheckInDialogListener());
	}

	public static void addCheckInListener(Button button) {
		button.addListener(SWT.MouseDown,
				guiControllerInstance.new CheckInListener());
	}

	public static void addFilterListener(Button button) {
		button.addListener(SWT.MouseDown,
				guiControllerInstance.new ReservationFilterListener());
	}

	/**
	 * Inner classes implementing the listeners
	 */

	/**
	 * inner class ReservationListListener When the reservationform is changed
	 * and the user klicks the update button the content of the form is saved to
	 * the database
	 */
	class ReservationSaveListener implements Listener {

		public void handleEvent(Event event) {
			try {
				/**
				 * Calling methods to change the reservation
				 */
				// Create an instance of the reservationcontroller
				ReservationController reservationController = new ReservationController();

				// Call the method to perform the save
				IReservation _reservation = reservationController
						.loadReservation(_selectedReservationId);

				reservationController
						.saveReservation(_selectedReservationId, _detailView
								.get_fNameText().getText(), _detailView
								.get_lNameText().getText(), _detailView
								.get_streetText().getText(), _detailView
								.get_townText().getText(), _detailView
								.get_zipText().getText(), _detailView
								.get_emailText().getText(), _detailView
								.get_telText().getText(), _detailView
								.get_faxText().getText(), _detailView
								.get_numberText().getText(),
								new Date(_detailView.get_arrivalDateForm()
										.getYear() - 1900, _detailView
										.get_arrivalDateForm().getMonth(),
										_detailView.get_arrivalDateForm()
												.getDay()),
								new Date(_detailView.get_leavingDateForm()
										.getYear() - 1900, _detailView
										.get_leavingDateForm().getMonth(),
										_detailView.get_leavingDateForm()
												.getDay()), "No comment.",
								_reservation.getCreated(), _detailView
										.get_countryCombo().getText()/*
																	 * _detailView.
																	 * get_countryText
																	 * (
																	 * ).getText
																	 * ()
																	 */);

				guiControllerInstance._iReservationsList = reservationController
						.searchReservation(_reservationView.get_rFilter()
								.getNumberText().getText(), _reservationView
								.get_rFilter().getNameText().getText());
				_reservationView.get_rTable().repaintTable(
						guiControllerInstance._iReservationsList);

			} catch (ArrivalAfterLeavingException e) {
				ShowError errorDialog = new ShowError(new Shell(),
						"Error, arrivaldate after leaving date, change date to perform update!");
				errorDialog.open();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// end inner class ReservationSaveListener

	/**
	 * inner class CheckInDialogListener When the checkIn Dialog opens the
	 * reservation items have to be displayed pass the specific reservation to
	 * the dialog
	 */
	class CheckInDialogListener implements Listener {

		CheckInDialog _checkInDialog;
		IReservation _reservation;

		public void handleEvent(Event event) {
			try {
				openDialog();
			} catch (ClassCastException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private void openDialog() throws ClassCastException,
				InstantiationException, IllegalAccessException,
				ClassNotFoundException, IllegalArgumentException,
				InvocationTargetException {

			ReservationController reservationController = new ReservationController();
			_reservation = reservationController
					.loadReservation(_selectedReservationId);

			_checkInDialog = new CheckInDialog(new Shell());
			CategoryController categoryController = new CategoryController();
			List<ICategory> categoryList = categoryController.loadCategories();
			_checkInController = new CheckInController();
			// pass the data to the dialog
			_checkInDialog.open(_reservation.getId(), categoryList,
					categoryController, _checkInController);

		}

	}

	// end inner class CheckInDialogListener

	/**
	 * Inner class CheckInDialogListener When the checkIn Dialog opens the
	 * reservationdata has to be desplayed pass the specific reservation to the
	 * dialog
	 */
	class CheckInListener implements Listener {

		@Override
		public void handleEvent(Event event) {
			int totalRoomsAmmount = totalRoomCount(_selectedReservation
					.getIReservationItems());
			try {
				checkRoomSelection(_checkInDialog.getSelectedRooms());

				// Start the checkin
				CheckInController checkInController = new CheckInController();
				checkInController.start();
				checkInController.loadReservation(_selectedReservationId);
				checkInController.loadedAvailableRooms();

				for (int i = 0; i < totalRoomsAmmount; i++) {
					CheckInRoom cir = _checkInDialog.getSelectedRooms().get(i);

					/**
					 * This method check if there are no double room selections
					 * if there is a doubleroom selection it throws an exception
					 */

					String roomnumber = cir.getSelectedRoom();

					Date start = _selectedReservation.getStart();
					Date end = _selectedReservation.getEnd();
					Double price = Double.parseDouble(cir.getPriceText());
					String deposit = _depositeTextField.getText();
					Date created = _selectedReservation.getCreated();

					String firstname = _selectedReservation.getIGuest()
							.getFirstName();
					String lastname = _selectedReservation.getIGuest()
							.getLastName();
					Date birthday = _selectedReservation.getIGuest()
							.getBirthday();
					String street = _selectedReservation.getIGuest()
							.getIAddress().getStreet();
					String city = _selectedReservation.getIGuest()
							.getIAddress().getCity();
					String zip = _selectedReservation.getIGuest().getIAddress()
							.getZip();
					String email = _selectedReservation.getIGuest()
							.getIAddress().getZip();
					String phone = _selectedReservation.getIGuest()
							.getIAddress().getPhone();
					String fax = _selectedReservation.getIGuest().getIAddress()
							.getFax();
					String countryName = _selectedReservation.getIGuest().getIAddress().getICountry().getName();
					String countryNameShort = _selectedReservation.getIGuest().getIAddress().getICountry().getShortName();

					checkInController.createHabitation(start, end, price,
							deposit, created, roomnumber, firstname, lastname,
							birthday, street, city, zip, email, phone, fax, countryName, countryNameShort);
				}
				if (checkInController.createdAllHabitation()) {
					ShowSuccess success = new ShowSuccess(new Shell(),
							"The habitations were created");
					success.open();
				}else{
					ShowError error = new ShowError(new Shell(), "The habitation could not be created");
					error.open();
				}
				_checkInDialog.Close();
			} catch (DoubleRoomSelectionException e1) {
				ShowError errorDialog = new ShowError(new Shell(),
						"Error, choose a room only once!");
				errorDialog.open();
				e1.printStackTrace();
			} catch (UnderMinimumPriceException e) {
				ShowError errorDialog = new ShowError(new Shell(),
						"Error, your price is under the minimumprice!");
				errorDialog.open();
			} catch (NoRoomException e) {
				ShowError errorDialog = new ShowError(new Shell(),
						"Error, there are not enough rooms!");
				errorDialog.open();
			} catch (ClassCastException e) {
				ShowError errorDialog = new ShowError(new Shell(),
						e.getMessage());
				errorDialog.open();
				// e.printStackTrace();
			} catch (IllegalArgumentException e) {
				ShowError errorDialog = new ShowError(new Shell(),
						e.getMessage());
				errorDialog.open();
				// e.printStackTrace();
			} catch (InstantiationException e) {
				ShowError errorDialog = new ShowError(new Shell(),
						e.getMessage());
				errorDialog.open();
				// e.printStackTrace();
			} catch (IllegalAccessException e) {
				ShowError errorDialog = new ShowError(new Shell(),
						e.getMessage());
				errorDialog.open();
				// e.printStackTrace();
			} catch (ClassNotFoundException e) {
				ShowError errorDialog = new ShowError(new Shell(),
						e.getMessage());
				errorDialog.open();
				// e.printStackTrace();
			} catch (InvocationTargetException e) {
				ShowError errorDialog = new ShowError(new Shell(),
						e.getMessage());
				errorDialog.open();
				// e.printStackTrace();
			} catch (Exception e) {
				ShowError errorDialog = new ShowError(new Shell(),
						e.getMessage());
				errorDialog.open();
				// e.printStackTrace();
			}
		}

		private void checkRoomSelection(List<CheckInRoom> choosenRooms)
				throws DoubleRoomSelectionException {

			HashSet<String> roomList = new HashSet<String>();

			for (CheckInRoom cir : choosenRooms) {
				if (!roomList.contains(cir.getSelectedRoom())) {
					roomList.add(cir.getSelectedRoom());
				} else {
					throw new DoubleRoomSelectionException(
							"Choosen the same room more then once exception.");
				}
			}
		}

	}

	// end inner class CheckInListener

	class ReservationFilterListener implements Listener {
		public void handleEvent(Event e) {
			try {
				String reservationNumber = _reservationView.get_rFilter()
						.getNumberText().getText();
				String lastName = _reservationView.get_rFilter().getNameText()
						.getText();

				ReservationController resController = new ReservationController();
				List<IReservation> reservations = resController
						.searchReservation(reservationNumber, lastName);
				_reservationView.get_rTable().repaintTable(reservations);
			} catch (ClassCastException e1) {
				ShowError errorDialog = new ShowError(new Shell(),
						e1.getMessage());
				errorDialog.open();
			} catch (InstantiationException e1) {
				ShowError errorDialog = new ShowError(new Shell(),
						e1.getMessage());
				errorDialog.open();
			} catch (IllegalAccessException e1) {
				ShowError errorDialog = new ShowError(new Shell(),
						e1.getMessage());
				errorDialog.open();
			} catch (ClassNotFoundException e1) {
				ShowError errorDialog = new ShowError(new Shell(),
						e1.getMessage());
				errorDialog.open();
			} catch (IllegalArgumentException e1) {
				ShowError errorDialog = new ShowError(new Shell(),
						e1.getMessage());
				errorDialog.open();
			} catch (InvocationTargetException e1) {
				ShowError errorDialog = new ShowError(new Shell(),
						e1.getMessage());
				errorDialog.open();
			}
		}
	}

	/**
	 * Inner class ReservationSelectionListener Listens when a reservation is
	 * klicked to fill the form on the right side with the corresponding data
	 * 
	 */
	class ReservationSelectionListener implements Listener {

		public void handleEvent(Event e) {
			try {
				Integer selection = _table.getSelectionIndex();
				_selectedReservationId = ((IReservation) _table.getItem(
						selection).getData()).getId();
				_selectedReservationRoomsAmmount = roomCount(((IReservation) _table
						.getItem(selection).getData()).getIReservationItems());
				_selectedReservation = ((IReservation) _table
						.getItem(selection).getData());
				guiControllerInstance._detailView
						.fillForm(_selectedReservation);
				enableButtons();
			} catch (InstantiationException error) {
				ShowError errorDialog = new ShowError(new Shell(),
						error.getMessage());
				errorDialog.open();
			} catch (IllegalAccessException error) {
				ShowError errorDialog = new ShowError(new Shell(),
						error.getMessage());
				errorDialog.open();
			} catch (ClassNotFoundException error) {
				ShowError errorDialog = new ShowError(new Shell(),
						error.getMessage());
				errorDialog.open();
			} catch (IllegalArgumentException error) {
				ShowError errorDialog = new ShowError(new Shell(),
						error.getMessage());
				errorDialog.open();
			} catch (InvocationTargetException error) {
				ShowError errorDialog = new ShowError(new Shell(),
						error.getMessage());
				errorDialog.open();
			}
		}
	}

	// end inner class ReservationSelectionListener

	class AutoFillDialogListener implements Listener {

		@Override
		public void handleEvent(Event event) {
			try {
				_autoFillDialog = new AutoFillDialog(new Shell());
				openAutoFillDialog();
			} catch (IllegalArgumentException e) {
				ShowError errorDialog = new ShowError(new Shell(),
						e.getMessage());
				errorDialog.open();
			} catch (ClassNotFoundException e) {
				ShowError errorDialog = new ShowError(new Shell(),
						e.getMessage());
				errorDialog.open();
			} catch (InstantiationException e) {
				ShowError errorDialog = new ShowError(new Shell(),
						e.getMessage());
				errorDialog.open();
			} catch (IllegalAccessException e) {
				ShowError errorDialog = new ShowError(new Shell(),
						e.getMessage());
				errorDialog.open();
			} catch (InvocationTargetException e) {
				ShowError errorDialog = new ShowError(new Shell(),
						e.getMessage());
				errorDialog.open();
			}
		}

		private void openAutoFillDialog() throws IllegalArgumentException,
				ClassNotFoundException, InstantiationException,
				IllegalAccessException, InvocationTargetException {
			ReservationController rc = new ReservationController();
			List<IGuest> guestList = rc.loadAllGuests();
			_autoFillDialog.open(guestList);
		}

	}

	class AutoFillListener implements Listener {

		public void handleEvent(Event event) {
			Integer selectionIndex = _guestTable.getSelectionIndex();
			
			IGuest guest = (IGuest) _guestTable.getItem(selectionIndex).getData();
			Integer id = guest.getId();
			
			String personID = guest.getId() + "";
			String firstName = guest.getFirstName();
			String lastName = guest.getLastName();
			String town = guest.getIAddress().getCity();
			String zip = guest.getIAddress().getZip();
			String country = guest.getIAddress().getICountry().getName();
			String email = guest.getIAddress().getEmail();
			String street = guest.getIAddress().getStreet();
			String phone = guest.getIAddress().getPhone();
			String fax = guest.getIAddress().getFax();
			
			_detailView.get_fNameText().setText(firstName);
			_detailView.get_lNameText().setText(lastName);
			_detailView.get_townText().setText(town);
			_detailView.get_zipText().setText(zip);
			for(int i = 0; i < _detailView.get_countryCombo().getItemCount(); i ++){
				if(_detailView.get_countryCombo().getItem(i).toString().equals(country)){
						_detailView.get_countryCombo().select(i);
						break;
				}
			}
			_detailView.get_streetText().setText(email);
			_detailView.get_telText().setText(phone);
			_detailView.get_faxText().setText(fax);	
			
			_autoFillDialog.close();
		}
	}

	private int roomCount(List<IReservationItem> iReservationItems) {

		int j = 0;

		for (int i = 0; i < iReservationItems.size(); i++) {
			j += iReservationItems.get(i).getAmount();
		}

		return j;
	}

	private int totalRoomCount(List<IReservationItem> iReservationItems) {

		int j = 0;

		for (IReservationItem ri : iReservationItems) {
			j += ri.getAmount();
		}

		return j;
	}
}
