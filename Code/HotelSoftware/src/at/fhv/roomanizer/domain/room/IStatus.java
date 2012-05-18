package at.fhv.roomanizer.domain.room;

/**
 * This status represents the status of a room, which includes whether it is free or occupied,
 * and if it is cleaned or not.
 * 
 * @author Daniel Rotter <daniel.rotter@students.fhv.at>
 */
public interface IStatus {
	
	/**
	 * Returns the id of the status
	 * @return id of the status
	 */
	public int getId();
	
	/**
	 * Returns the name of the status
	 * @return The name of the status
	 */
	public String getName();
}
