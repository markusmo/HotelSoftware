/**
 * @author Raf
 * This class hold the buttons for
 * the check-in
 * canceling a reservation
 * creating a reservation
 */
package at.fhv.roomanizer.ui.swt.reservation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import at.fhv.roomanizer.ui.swt.GUIReservationController;

public class ReservationActionButtons extends Composite {
	
	public ReservationActionButtons(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new FillLayout());
		initUI();
	}

	private void initUI() {

		/**
		 *  Create a Container for the ReservationTable and the form
		 */
		RowLayout rowLayout = new RowLayout();
		rowLayout.wrap = false;
		rowLayout.pack = true;
		rowLayout.justify = false;
		rowLayout.type = SWT.VERTICAL;
		rowLayout.fill = true;

		Composite container = new Composite(this, SWT.NONE);
		container.setLayout(rowLayout);

		/**
		 * Check-In button
		 */
		Button submit = new Button(container, SWT.PUSH);
		submit.setText("Check-In reservation");
		submit.setEnabled(false);
		GUIReservationController.addCheckDialogInListener(submit);
		GUIReservationController.set_checkIn(submit);

		/**
		 * Storno button
		 */
		Button stornoB = new Button(container, SWT.PUSH);
		stornoB.setText("Cancel reservation");
		stornoB.setEnabled(false);
		GUIReservationController.set_cancle(stornoB);

		Label separtor = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		/**
		 * Create Reservation button
		 */
		Button reservationC = new Button(container, SWT.PUSH);
		reservationC.setText("Create reservation");
		
		this.pack();
	}

}
