package at.fhv.roomanizer.ui.swt.dashboard;


import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class HotelInfo extends Composite{

	public HotelInfo(Composite parent, int style) {
		super(parent, style);
		GridLayout g = new GridLayout(2,false);
		g.horizontalSpacing = 0;
		g.verticalSpacing = 0;
		this.setLayout(g);
		initUI();
	}

	private void initUI() {
		
		// Create Title
		Label title = new Label(this, SWT.NONE);
		title.setText("Hotelinformation");
		GridData data = new GridData(GridData.BEGINNING, GridData.BEGINNING, true, false, 2, 1);

		//Create a new label which is used as a separator
	    //Use GridData to span it over the 2 columns
		Label separtor = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		data = new GridData(GridData.FILL, GridData.BEGINNING, true, false, 2, 1);
		data.horizontalSpan=2;
		separtor.setLayoutData(data);
		
		// Hotelname
		Label name = new Label(this, SWT.NONE);
		name.setText("Name:");
		Text text = new Text(this,SWT.READ_ONLY | SWT.BORDER);
		text.setText("Mustername");
		
		// Hotel Tel. Nr
		Label telNr = new Label(this, SWT.NONE);
		telNr.setText("Tel.:");
		text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setText("0800 / 4711 0815");
		
		// Street
		Label street = new Label(this, SWT.NONE);
		street.setText("Street:");
		text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setText("Musterstraße");
		// Number
		Label number = new Label(this, SWT.NONE);
		number.setText("Nr.:");
		text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setText("15");
		
		// Website
		Label hp = new Label(this, SWT.NONE);
		hp.setText("Homepage:");
		text = new Text(this, SWT.READ_ONLY | SWT.BORDER);
		text.setText("www.roomanizer.at");
		
		this.pack();
	}

}
