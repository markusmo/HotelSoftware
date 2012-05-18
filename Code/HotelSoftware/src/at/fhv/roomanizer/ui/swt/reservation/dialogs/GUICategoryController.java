package at.fhv.roomanizer.ui.swt.reservation.dialogs;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Shell;

import at.fhv.roomanizer.application.CategoryController;
import at.fhv.roomanizer.application.exceptions.NotEnoughRoomsException;
import at.fhv.roomanizer.application.exceptions.NotEnoughRoomsInCategory;
import at.fhv.roomanizer.domain.reservation.IReservation;
import at.fhv.roomanizer.domain.room.IRoom;
import at.fhv.roomanizer.ui.swt.errors.ShowError;

public class GUICategoryController implements SelectionListener {
	private CategoryController _categoryController;
	private IReservation _reservation;
	
	public GUICategoryController(CategoryController categoryController, IReservation reservation) {
		_categoryController = categoryController;
		this._reservation = reservation;
	}
	
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		if(arg0 instanceof CategorySelectionEvent) {
			CategorySelectionEvent cse = (CategorySelectionEvent) arg0;
			
			CheckInRoom cir = (CheckInRoom) cse.getSource();
			String categoryName = cir.getSelectedCategory();
			
			try {
				cir.setPriceText(_categoryController.loadPriceForSeason(_reservation.getStart(), categoryName).getPrice());
				List<IRoom> rooms = _categoryController.availableRooms(_reservation.getStart(), _reservation.getEnd(), categoryName, 0);
				String[] roomList = new String[rooms.size()];
				for(int i = 0; i < roomList.length; i++) {
					roomList[i] = rooms.get(i).getNumber();
				}
				
				cir.setRoomList(roomList);
				
			} catch (IllegalArgumentException e) {
				ShowError errorDialog = new ShowError(new Shell(), e.getMessage());
				errorDialog.open();
			} catch (InstantiationException e) {
				ShowError errorDialog = new ShowError(new Shell(), e.getMessage());
				errorDialog.open();
			} catch (IllegalAccessException e) {
				ShowError errorDialog = new ShowError(new Shell(), e.getMessage());
				errorDialog.open();
			} catch (ClassNotFoundException e) {
				ShowError errorDialog = new ShowError(new Shell(), e.getMessage());
				errorDialog.open();
			} catch (InvocationTargetException e) {
				ShowError errorDialog = new ShowError(new Shell(), e.getMessage());
				errorDialog.open();
			} catch (NotEnoughRoomsInCategory e) {
				ShowError errorDialog = new ShowError(new Shell(), e.getMessage());
				errorDialog.open();
			} catch (NotEnoughRoomsException e) {
				ShowError errorDialog = new ShowError(new Shell(), e.getMessage() + "\nPlease contact the hotel manager");
				errorDialog.open();
			}
		}
	}
		
}
