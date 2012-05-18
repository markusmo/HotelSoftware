package at.fhv.roomanizer.domain.room;

import java.util.List;

public interface ICategory {
	public int getId();
	/**
	 * Get the name of the category
	 * @return name of the category
	 */
	public String getName();
	
	/**
	 * Get a list of all the rooms in this category
	 * @return List<Room> all the rooms in this category
	 */
	public List<Room> getRooms();
	
	/**
	 * Returns prices of the category
	 * @return List<Price> 
	 */
	public List<Price> getPrice();
}
