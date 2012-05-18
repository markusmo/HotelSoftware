package at.fhv.roomanizer.ui.swt.reservation;

import java.awt.Color;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;

import at.fhv.roomanizer.ui.swt.GUIReservationController;
import at.fhv.roomanizer.domain.reservation.IReservation;

public class ReservationView extends Composite {

	ReservationTable _rTable;
	ReservationFilters _rFilter;

	public ReservationTable get_rTable() {
		return _rTable;
	}
	
	public ReservationFilters get_rFilter(){
		return _rFilter;
	}

	public ReservationView(Composite parent, int style) {

		super(parent, style);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.makeColumnsEqualWidth = false;
		this.setLayout(layout);
		initUI();
	}

	private void initUI() {

		// Add the Filters at the top
		GridData data = new GridData(SWT.FILL, SWT.FILL, false, false);
		_rFilter = new ReservationFilters(this, SWT.NONE);
		_rFilter.setLayoutData(data);
		//Create a Container for the ReservationTable and the form
		RowLayout verticalRowLayout = new RowLayout();
		verticalRowLayout.wrap = false;
		verticalRowLayout.pack = true;
		verticalRowLayout.justify = false;
		verticalRowLayout.type = SWT.VERTICAL;
		verticalRowLayout.fill = true;
		
		//Create a Container for the ReservationTable and the form
		RowLayout rowLayout = new RowLayout();
		rowLayout.wrap = false;
		rowLayout.pack = true;
		rowLayout.justify = false;
		rowLayout.type = SWT.HORIZONTAL;
		rowLayout.fill = true;
		
		Composite mainContainer = new Composite(this, SWT.NONE);
		mainContainer.setLayout(verticalRowLayout);
		
		Composite container = new Composite(mainContainer, SWT.NONE);
		container.setLayout(rowLayout);
		
		// Add the Table
		data = new GridData(SWT.FILL, SWT.FILL, true, true);
		_rTable = new ReservationTable(container, SWT.NONE);

		// Add the DetailView of a Reservation
		data = new GridData(SWT.LEFT, SWT.BEGINNING, false, false);
		ReservationDetailView details = new ReservationDetailView(container,SWT.NONE);

		// Add the Buttons
		ReservationActionButtons buttons = new ReservationActionButtons(mainContainer, SWT.NONE);
		GUIReservationController.init(this);
	}

	public void paintTable(List<IReservation> list) {
		_rTable.paintReservationTable(list);
	}

}
