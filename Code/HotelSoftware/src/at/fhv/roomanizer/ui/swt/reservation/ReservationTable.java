package at.fhv.roomanizer.ui.swt.reservation;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import at.fhv.roomanizer.domain.reservation.IReservation;
import at.fhv.roomanizer.ui.swt.GUIReservationController;

public class ReservationTable extends Composite {

	Table _table;
	TableColumn _column;
	String[] _columnHeadline = { "Reservationnumber", "Firstname","Lastname", "Arrival", "Leaving", "Phone" };

	public ReservationTable(Composite parent, int style) {
		super(parent, style);
		FillLayout layout = new FillLayout();
		this.setLayout(layout);
		_table = new Table(this, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);
		
		for (int i = 0; i < _columnHeadline.length; i++) {
			_column = new TableColumn(_table, SWT.NONE);
			_column.setText(_columnHeadline[i]);
		}
		
		initUI();
	}

	private void initUI() {
		FillLayout layout = new FillLayout();
		_table.setLinesVisible(true);
		_table.setHeaderVisible(true);
		_table.setLayout(layout);
		_table.setSize(600, 400);
		GUIReservationController.addSelectionListnerToReservationTable(_table);
	}

	public void paintReservationTable(List<IReservation> reservationList) {
		
		String DATE_FORMAT = "dd.MM.yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		
		for (int i = 0; i < reservationList.size(); i++) {
				TableItem item = new TableItem(_table,SWT.NONE);
				item.setText(0, reservationList.get(i).getNumber());
				item.setText(1, reservationList.get(i).getIGuest().getFirstName());
				item.setText(2, reservationList.get(i).getIGuest().getLastName());
				item.setText(3, sdf.format(reservationList.get(i).getStart()));
				item.setText(4, sdf.format(reservationList.get(i).getEnd()));
				item.setText(5, reservationList.get(i).getIGuest().getIAddress().getPhone());
				item.setData(reservationList.get(i));
		}
		for (int i = 0; i < 6; i++) {
			_table.getColumn(i).pack();
		}
	}
	public void repaintTable(List<IReservation> reservationList){
		
		//Clear the items
		TableItem[] tableItems = _table.getItems();
		for(int i = 0; i < tableItems.length; i ++){
			tableItems[i].dispose();
		}
		
		_table.clearAll();
		paintReservationTable(reservationList);
	}
}
