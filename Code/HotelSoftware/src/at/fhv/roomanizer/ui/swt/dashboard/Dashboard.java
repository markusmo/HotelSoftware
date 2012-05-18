package at.fhv.roomanizer.ui.swt.dashboard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;


public class Dashboard extends Composite{
	
	public Dashboard(Composite parent, int style) {		
		super(parent, style);
		
		GridLayout layout = new GridLayout();
	    layout.numColumns = 3;
	    layout.makeColumnsEqualWidth = false;
	  
	    this.setLayout(layout);

		initUI();
	}

	/**
	 * Creates the parts of the Dashboard userinterface
	 */
	private void initUI() {
		HotelInfo hotelinfo = new HotelInfo(this, SWT.NONE);
		hotelinfo.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		
		RoomInfo roominfo = new RoomInfo(this, SWT.NONE);
		roominfo.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		
		GuestInfo guestinfo = new GuestInfo(this, SWT.NONE);
		guestinfo.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		
		HotelStats hotelstats = new HotelStats(this, SWT.NONE);
		hotelstats.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		
		ExtraInfo extrainfo = new ExtraInfo(this, SWT.NONE);
		extrainfo.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		
		Notes notes = new Notes(this, SWT.NONE);
		notes.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
	}
	
}
