/**
 * @author Raf
 */
package at.fhv.roomanizer.ui.swt.reservation.dialogs;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import at.fhv.roomanizer.application.CategoryController;
import at.fhv.roomanizer.application.CheckInController;
import at.fhv.roomanizer.application.exceptions.NotEnoughRoomsException;
import at.fhv.roomanizer.domain.reservation.IReservation;
import at.fhv.roomanizer.domain.reservation.IReservationItem;
import at.fhv.roomanizer.domain.room.ICategory;
import at.fhv.roomanizer.domain.room.IRoom;
import at.fhv.roomanizer.ui.swt.GUIReservationController;
import at.fhv.roomanizer.ui.swt.errors.ShowError;

/**
 * @author Raf The CheckInDialog Displays the detailled information to check-in
 *         a reservation. The check-in process will initiate when the sumbmit
 *         button is clicked.
 */

public class CheckInDialog extends Dialog {

	/**
	 * Viewspecific
	 */
	public Shell _dialog;

	/**
	 * Specific fields for the check-in process.
	 */
	CheckInController _checkInController;
	IReservation _reservation;
	List<IReservationItem> _reservationItemList;
	List<ICategory> _categoryList;
	CategoryController _categoryController;

	GUICategoryController _guiCategoryController;
	List<CheckInRoom> _compositeRoomList = new ArrayList<CheckInRoom>();

	String _enoughRooms = "";

	public CheckInDialog(Shell parent) {
		super(parent);
	}

	/**
	 * This Method is called from the controller to open the dialog window and
	 * display the detailed information. The information is passed over the
	 * method interface.
	 * 
	 * @param rId
	 * @param categoryList
	 * @param categoryController
	 * @param checkInController
	 * @throws ClassCastException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public void open(int rId, List<ICategory> categoryList,
			CategoryController categoryController,
			CheckInController checkInController) throws ClassCastException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException, IllegalArgumentException,
			InvocationTargetException {
		try {
			_checkInController = checkInController;
			_checkInController.start();
			_reservation = _checkInController.loadReservation(rId);
			_categoryList = categoryList;
			_categoryController = categoryController;

			Shell parent = getParent();
			_dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
			_dialog.setText("Check-In");

			RowLayout rowLayout = new RowLayout();
			rowLayout.wrap = false;
			rowLayout.pack = false;
			rowLayout.justify = true;
			rowLayout.type = SWT.VERTICAL;
			_dialog.setLayout(rowLayout);
			GUIReservationController._checkInDialog = this;

			_guiCategoryController = new GUICategoryController(
					_categoryController, _reservation);

			initUI(_dialog);

			_dialog.open();

			/**
			 * if there are not enough rooms available in the chosen category of
			 * the reservation
			 */
			if (!_enoughRooms.equals("")) {
				ShowError errorDialog = new ShowError(new Shell(),
						"Not enough rooms in category: " + _enoughRooms);
				errorDialog.open();
			}

			Display display = parent.getDisplay();

			while (!_dialog.isDisposed()) {
				_dialog.pack();
				if (!display.readAndDispatch()) {
					display.sleep();
				}

			}
		}catch (NotEnoughRoomsException e) {
			ShowError errorDialog = new ShowError(new Shell(), e.getMessage() + "\nPlease contact the hotel manager");
			errorDialog.open();
		}
		catch (Exception e) {
			ShowError errorDialog = new ShowError(new Shell(), e.getMessage());
			errorDialog.open();
		}
	}

	/**
	 * This method is responsible to add all parts for the checkin. It manges
	 * this with a loop a iteration over the reservation informations
	 * 
	 * @param dialog
	 * @throws Exception
	 */
	private void initUI(Shell dialog) throws Exception {

		_reservationItemList = _reservation.getIReservationItems();

		RowLayout verticalRowLayout = new RowLayout();
		verticalRowLayout.wrap = false;
		verticalRowLayout.pack = true;
		verticalRowLayout.justify = false;
		verticalRowLayout.type = SWT.VERTICAL;

		RowLayout horizRowLayout = new RowLayout();
		horizRowLayout.wrap = false;
		horizRowLayout.pack = true;
		horizRowLayout.justify = false;
		horizRowLayout.type = SWT.HORIZONTAL;

		_enoughRooms = "";
		/**
		 * Iteration to create the rows with the information for the reservation
		 * In this version a row has following parts: Category Roomnumber Price
		 * The first loop iterates over all reservationitem, every reservation
		 * can hold one or more rooms with the second foor loop the rooms are
		 * iterated
		 */
		
		HashSet<String> alreadySelectedRooms = new HashSet<String>();
		int selectedRoomIndex = 0;
		
		for (IReservationItem ri : _reservationItemList) {

			for (int i = 0; i < ri.getAmount(); i++) {

				Composite verticalContainer = new Composite(dialog, SWT.BORDER);
				verticalContainer.setLayout(verticalRowLayout);

				int categoryPreSelector = 0;
				String[] categoryArray = new String[_categoryList.size()];
				/**
				 * This loop adds the values into the Stringarray
				 */
				for (int k = 0; k < categoryArray.length; k++) {
					categoryArray[k] = _categoryList.get(k).getName();
					if (_categoryList.get(k).getName()
							.equals(ri.getICategory().getName())) {
						categoryPreSelector = k;
					}
				}

				CheckInRoom cir = new CheckInRoom(verticalContainer,
						getStyle(), categoryArray, categoryPreSelector);
				
				cir.setPriceText(_categoryController.loadPriceForSeason(_reservation.getStart(), ri.getICategory().getName()).getPrice());
				CategoryController cc = new CategoryController();
				cir.addCategoryListener(_guiCategoryController);
				List<IRoom> rooms = cc.availableRooms(_reservation.getStart(),
						_reservation.getEnd(), ri.getICategory().getName(),
						ri.getAmount());

				/**
				 * if there are not enough rooms in a category
				 */
				if (rooms.size() < ri.getAmount()
						&& !_enoughRooms.equals(ri.getICategory().getName())) {
					_enoughRooms += ri.getICategory().getName();
				}

				boolean roomChoosed = false;
				String[] roomList = new String[rooms.size()];
				for (int j = 0; j < roomList.length; j++) {
					roomList[j] = rooms.get(j).getNumber();
					
					if(!roomChoosed && !alreadySelectedRooms.contains(roomList[j])) {
						alreadySelectedRooms.add(roomList[j]);
						selectedRoomIndex = j;
						roomChoosed = true;
					}
				}

				cir.setRoomList(roomList);						
				cir.selectRoom(selectedRoomIndex);
				_compositeRoomList.add(cir);
			}
		}

		/**
		 * A layout definition for the deposite
		 */
		Composite depositeContainer = new Composite(dialog, SWT.NONE);
		RowLayout rowLayout = new RowLayout();
		rowLayout.wrap = false;
		rowLayout.pack = false;
		rowLayout.justify = false;
		rowLayout.type = SWT.HORIZONTAL;
		depositeContainer.setLayout(rowLayout);

		/**
		 * The deposite part
		 */
		Label deposite = new Label(depositeContainer, SWT.NONE);
		deposite.setText("Deposite:");
		Text depositeText = new Text(depositeContainer, SWT.BORDER);
		GUIReservationController._depositeTextField = depositeText;

		/**
		 * CheckInButton
		 */
		Composite buttonContainer = new Composite(dialog, SWT.NONE);
		buttonContainer.setLayout(rowLayout);

		Button checkIn = new Button(buttonContainer, SWT.PUSH);
		checkIn.setText("Checkin");
		GUIReservationController.addCheckInListener(checkIn);

		/**
		 * Anon-Class to close the dialog
		 */
		Listener listener = new Listener() {
			public void handleEvent(Event event) {
				_dialog.close();
			}
		};

		/**
		 * Cancle Button
		 */
		Button cancelButton = new Button(buttonContainer, SWT.PUSH);
		cancelButton.setText("Cancel");
		cancelButton.addListener(SWT.Selection, listener);

	}

	public List<CheckInRoom> getSelectedRooms() {
		return _compositeRoomList;
	}

	public void Close() {
		_dialog.close();
	}
}
