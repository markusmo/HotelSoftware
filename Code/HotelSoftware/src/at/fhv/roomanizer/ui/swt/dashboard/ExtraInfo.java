package at.fhv.roomanizer.ui.swt.dashboard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ExtraInfo extends Composite{

	public ExtraInfo(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new GridLayout(2, true));
		initUI();
	}

	private void initUI() {
		// Create Title
		Label title = new Label(this, SWT.NONE);
		title.setText("Extras");
		GridData data = new GridData(GridData.BEGINNING, GridData.CENTER, true, false, 2, 1);
		title.setLayoutData(data);
		
		// Create a new label which is used as a separator
		// Use GridData to span it over the 2 columns
		Label separtor = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		data = new GridData(GridData.FILL, GridData.BEGINNING, true, false, 2, 1);
		data.horizontalSpan = 2;
		separtor.setLayoutData(data);
		
		// season
		Label season = new Label(this, SWT.NONE);
		season.setText("Saison:");
		Text text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setText("Winter");
		text.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		// weatherforecast
		Label weatherforecast = new Label(this, SWT.NONE);
		weatherforecast.setText("Weatherforecast:");
		text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setText("Partly sunny; max 7; min -1;");
		text.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		// events
		Label event = new Label(this, SWT.NONE);
		event.setText("Events:");
		text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setText("Anniversary, Roomanizer 20:00");
		text.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		// cabs
		Label cabs = new Label(this, SWT.NONE);
		cabs.setText("Local Cabs:");
		Combo combo = new Combo (this, SWT.READ_ONLY);
		combo.setItems (new String [] {"Taxi Joe: 343242", "Taxi Hugo: 32323", "Taxi Jay: 32342"});
		combo.select(0);
		combo.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
	}
}
