package at.fhv.roomanizer.ui.swt.errors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class ShowError extends Dialog {

	String _message;
	private Shell _dialog;

	public ShowError(Shell parent, String message) {
		super(parent);
		_message = message;
	}

	public void open() {

		Shell parent = getParent();
		_dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		_dialog.setText("Warning");

		RowLayout rowLayout = new RowLayout();
		rowLayout.wrap = false;
		rowLayout.pack = true;
		rowLayout.justify = true;
		rowLayout.center = true;
		rowLayout.type = SWT.VERTICAL;
		rowLayout.marginTop = 30;
		rowLayout.marginBottom = 30;
		rowLayout.marginLeft = 30;
		rowLayout.marginRight = 30;
		rowLayout.spacing = 20;
		_dialog.setLayout(rowLayout);

		Label price = new Label(_dialog, SWT.NONE);
		price.setText(_message);

		Button cancel = new Button(_dialog, SWT.PUSH);
		cancel.setText("OK");
		cancel.addListener(SWT.Selection, listener);

		_dialog.open();
		_dialog.pack();

	}

	Listener listener = new Listener() {
		public void handleEvent(Event event) {
			_dialog.close();
		}
	};

}