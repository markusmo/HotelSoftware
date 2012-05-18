package at.fhv.roomanizer.ui.swt.dashboard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class GuestInfo extends Composite {

	public GuestInfo(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new GridLayout(2, true));
		initUI();
	}

	private void initUI() {
		
		// Create Title
		Label title = new Label(this, SWT.NONE);
		title.setText("Guestinformation");
		GridData data = new GridData(GridData.BEGINNING, GridData.CENTER, true, false, 2, 1);
		title.setLayoutData(data);

		// Create a new label which is used as a separator
		// Use GridData to span it over the 2 columns
		Label separtor = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		data = new GridData(GridData.FILL, GridData.BEGINNING, true, false, 2, 1);
		data.horizontalSpan = 2;
		separtor.setLayoutData(data);

		// Total
		Label total = new Label(this, SWT.NONE);
		total.setText("Guests total:");
		Text text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setText("60");
		text.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		// Incomings today
		Label incoming = new Label(this, SWT.NONE);
		incoming.setText("Incomings, today:");
		text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setText("8");
		text.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));

		// Incomings 2 weeks
		Label incomingPeriod = new Label(this, SWT.NONE);
		incomingPeriod.setText("Incomings, 2 weeks:");
		text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setText("20");
		text.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		// Groups present
		Label groups = new Label(this, SWT.NONE);
		groups.setText("Groups present:");
		text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setText("5");
		text.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		// Businessguests
		Label businessG = new Label(this, SWT.NONE);
		businessG.setText("Business Guests:");
		text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setText("10");
		text.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		// Privateguests
		Label privateG = new Label(this, SWT.NONE);
		privateG.setText("Private Guests:");
		text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setText("50");
		text.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		
		//VIPS
		Label vip = new Label(this, SWT.NONE);
		vip.setText("VIP Guests:");
		text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setText("2");
		text.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
	}

}
