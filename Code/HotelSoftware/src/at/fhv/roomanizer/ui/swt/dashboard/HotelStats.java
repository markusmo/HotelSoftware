package at.fhv.roomanizer.ui.swt.dashboard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class HotelStats extends Composite{

	public HotelStats(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new GridLayout(2, true));
		initUI();
	}

	private void initUI() {
		// Create Title
		Label title = new Label(this, SWT.NONE);
		title.setText("Hotelstatus");
		GridData data = new GridData(GridData.BEGINNING, GridData.CENTER, true, false, 2, 1);
		title.setLayoutData(data);

		// Create a new label which is used as a separator
		// Use GridData to span it over the 2 columns
		Label separtor = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		data = new GridData(GridData.FILL, GridData.BEGINNING, true, false, 2, 1);
		data.horizontalSpan = 2;
		separtor.setLayoutData(data);
		
		// Storno procent
		Label storno = new Label(this, SWT.NONE);
		storno.setText("Storno percentage:");
		Text text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		text.setText("12 %");
		
		// Avg Stays
		Label avgStays = new Label(this, SWT.NONE);
		avgStays.setText("Average stay:");
		text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		text.setText("4 days");
		
		// Load Faktor
		Label loadFaktor = new Label(this, SWT.NONE);
		loadFaktor.setText("Load Faktor:");
		text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		text.setText("80 %");
		
		// Load Faktor
		Label overbooking = new Label(this, SWT.NONE);
		overbooking.setText("Overbooking:");
		text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		text.setText("5 %");
	}
	
}
