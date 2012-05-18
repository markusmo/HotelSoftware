package at.fhv.roomanizer.ui.swt.reservation.dialogs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import at.fhv.roomanizer.application.CategoryController;
import at.fhv.roomanizer.application.CheckInController;
import at.fhv.roomanizer.domain.person.IGuest;
import at.fhv.roomanizer.domain.reservation.IReservation;
import at.fhv.roomanizer.domain.room.ICategory;
import at.fhv.roomanizer.ui.swt.GUIReservationController;

public class AutoFillDialog extends Dialog {

	/**
	 * Viewspecific
	 */
	public Shell _dialog;
	Table _table;
	TableColumn _column;
	List<TableItem> _itemList;
	String[] _columnHeadline = { "Firstname", "Lastname", "Birthdate" };
	Button _autoFillButton;

	/**
	 * Dataspecific
	 */
	List<IGuest> _guestList;

	public AutoFillDialog(Shell parent) {
		super(parent);
	}

	public void open(List<IGuest> guestList) {
		_guestList = guestList;
		Shell parent = getParent();
		_dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		_dialog.setText("AutoFill");
		FillLayout layout = new FillLayout();
		_dialog.setLayout(layout);
		initUI();
	}

	private void initUI() {

		_table = new Table(_dialog, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);
		
		for (int i = 0; i < _columnHeadline.length; i++) {
			_column = new TableColumn(_table, SWT.NONE);
			_column.setText(_columnHeadline[i]);
		}
		
		RowLayout layout = new RowLayout();
		layout.wrap = false;
		layout.pack = true;
		layout.justify = false;
		layout.center = true;
		layout.type = SWT.VERTICAL;
		_dialog.setLayout(layout);
		
		_table.setLinesVisible(true);
		_table.setHeaderVisible(true);
		
		_autoFillButton = new Button(_dialog, SWT.PUSH);
		_autoFillButton.setText("Choose");
		
		
		paintReservationTable(_guestList);
		GUIReservationController.addAutoFillListener(_autoFillButton, _table);
		
		_dialog.open();
	}

	private void paintReservationTable(List<IGuest> guestList) {
		
		String DATE_FORMAT = "dd.MM.yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		
		for (int i = 0; i < guestList.size(); i++) {
				TableItem item = new TableItem(_table,SWT.NONE);
				item.setText(0, guestList.get(i).getFirstName());
				item.setText(1, guestList.get(i).getLastName());
				item.setText(2, sdf.format(guestList.get(i).getBirthday()));
				item.setData(guestList.get(i)); 
		}
		
		for (int i = 0; i < _columnHeadline.length; i++) {
			_table.getColumn(i).pack();
		}
		_table.setSelection(0);
		_table.pack();
		_dialog.pack();
	}
	
	public void close(){
		_dialog.close();
	}

}
