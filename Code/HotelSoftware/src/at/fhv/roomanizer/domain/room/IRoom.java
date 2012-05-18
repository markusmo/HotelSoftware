package at.fhv.roomanizer.domain.room;

import java.util.List;

import at.fhv.roomanizer.domain.Habitation;

public interface IRoom {
	
	/**
	 * Returns the room number
	 * @return String room number
	 */
	public String getNumber();

	public List<IRoomStatus> getIStatus();
	
	/**
	 * Returns the room status
	 * @return Status rom status
	 */
	public List<RoomStatus> getStatus();
	
	/**
	 * Returns a list of habitations of this room
	 * @return List<Habitation>
	 */
	public List<Habitation> getHabitations();
	
	/**
	 * Returns the category to which this room is added
	 * @return ICategory
	 */
	public ICategory getICategory();
}
