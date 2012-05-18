package at.fhv.roomanizer.ui.swt.dashboard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class RoomInfo extends Composite {

	public RoomInfo(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new GridLayout(2, true));
		initUI();
	}

	private void initUI() {

		// Create Title
		Label title = new Label(this, SWT.NONE);
		title.setText("Roominformation");
		GridData data = new GridData(GridData.BEGINNING, GridData.CENTER, true, false, 2, 1);
		title.setLayoutData(data);

		// Create a new label which is used as a separator
		// Use GridData to span it over the 2 columns
		Label separtor = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		data = new GridData(GridData.FILL, GridData.BEGINNING, true, false, 2,
				1);
		data.horizontalSpan = 2;
		separtor.setLayoutData(data);

		// Roomcount
		Label roomcount = new Label(this, SWT.NONE);
		roomcount.setText("Rooms total:");
		Text text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setText("120");
		text.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));

		// Roomsfree
		Label roomsFree = new Label(this, SWT.NONE);
		roomsFree.setText("Rooms free:");
		text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setText("60");
		text.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));

		// Rooms occupied
		Label roomsOcc = new Label(this, SWT.NONE);
		roomsOcc.setText("Rooms occupied:");
		text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setText("60");
		text.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));

		// Rooms clean
		Label roomsClean = new Label(this, SWT.NONE);
		roomsClean.setText("Rooms, clean:");
		text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setText("60");
		text.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));

		// Rooms uncleaned
		Label roomsUnclean = new Label(this, SWT.NONE);
		roomsUnclean.setText("Rooms, unclean:");
		text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setText("0");
		text.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		//Rooms out of order
		Label roomsO3 = new Label(this, SWT.NONE);
		roomsO3.setText("Rooms out of order:");
		text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setText("0");
		text.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
	}

}
