package at.fhv.roomanizer.ui.swt.dashboard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class Notes extends Composite{

	public Notes(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new GridLayout(1, true));
		initUI();
	}

	private void initUI() {	
		// Create Title
		Label title = new Label(this, SWT.NONE);
		title.setText("Notes");
		GridData data = new GridData(GridData.BEGINNING, GridData.CENTER, true, false, 2, 1);
		title.setLayoutData(data);

		// Create a new label which is used as a separator
		// Use GridData to span it over the 2 columns
		Label separtor = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		data = new GridData(GridData.FILL, GridData.BEGINNING, true, false, 2, 1);
		data.horizontalSpan = 2;
		separtor.setLayoutData(data);
		
		// Create a multiple line text field
	    Text t = new Text(this, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
	    t.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.BEGINNING ));
		
	}

}
