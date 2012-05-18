package at.fhv.roomanizer.ui.swt.reservation.dialogs;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Event;

public class CategorySelectionEvent extends SelectionEvent {

	public CategorySelectionEvent(Event event, Object source) {
		super(event);
		this.source = source;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6123377305602886407L;

}
