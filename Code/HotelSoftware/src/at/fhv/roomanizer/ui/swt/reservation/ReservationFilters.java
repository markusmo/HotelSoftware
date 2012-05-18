package at.fhv.roomanizer.ui.swt.reservation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import at.fhv.roomanizer.ui.swt.GUIReservationController;

public class ReservationFilters extends Composite {
	
	private Text _numberText;
	private Text _nameText;
	
	public Text getNumberText(){
		return _numberText;
	}
	
	public Text getNameText(){
		return _nameText;
	}

	public ReservationFilters(Composite parent, int style) {
		super(parent, style);

		GridLayout layout = new GridLayout();
		layout.numColumns = 6;
		layout.makeColumnsEqualWidth = false;

		RowLayout rowLayout = new RowLayout();
		rowLayout.wrap = true;
		rowLayout.pack = true;
		rowLayout.justify = false;
		rowLayout.center = true;
		rowLayout.spacing = 10;
		rowLayout.type = SWT.HORIZONTAL;

		this.setLayout(rowLayout);

		initUI();
	}

	private void initUI() {

		// Filter Reservationsnummer
		Label resNr = new Label(this, SWT.NONE);
		resNr.setText("Res. Nr.:");
		_numberText = new Text(this, SWT.BORDER);

		// Filter Lastname
		Label name = new Label(this, SWT.NONE);
		name.setText("Last-Name.:");
		_nameText = new Text(this, SWT.BORDER);

		// Filter button
		Button btnFilter = new Button(this, SWT.PUSH);
		btnFilter.setText("Filter");
		GUIReservationController.addFilterListener(btnFilter);
	}
}
