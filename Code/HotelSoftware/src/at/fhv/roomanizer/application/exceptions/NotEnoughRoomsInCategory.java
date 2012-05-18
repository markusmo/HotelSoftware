package at.fhv.roomanizer.application.exceptions;

/**
 * if not enough rooms are available in a category
 * 
 * @author phils
 * 
 */
public class NotEnoughRoomsInCategory extends Exception {
	public NotEnoughRoomsInCategory(String s) {
		super(s);
	}
}
