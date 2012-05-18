package at.fhv.roomanizer.domain.room;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Every room must be a member of a category. The category is asked
 * if we want to know if rooms are available. 
 * 
 * Categorys are the deciding
 * factor when deciding about pricing, but prices are stored in the Price
 * class and are depending of the actual season.
 * 
 * @author Shady
 */
public class Category implements ICategory {

	/**
	 * The id of the category
	 */
	public int _id;
	
	/**
	 * Name of the category
	 */
	private String _name;
	
	/**
	 * Price of the category
	 */
	private  List<Price> _price = new ArrayList<Price>();
	
	/**
	 * Rooms in this category
	 */
	private List<Room> _rooms = new ArrayList<Room>();

	/**
	 * Get the id of the category
	 * @return id of the category
	 */
	public int getId() {
		return _id;
	}

	/**
	 * Set the id of the cateory
	 * @param id of the category
	 */
	public void setId(int id) {
		_id = id;
	}
	
	/**
	 * @see ICategory#getName()
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Set the name of the cateory
	 * @param name of the category
	 */
	public void setName(String name) {
		_name = name;
	}

	/**
	 * @see ICategory#getPrice()
	 */
	public List<Price> getPrice() {
		return _price;
	}

	/**
	 * Set a list of prices for the category
	 * @param price List<price>
	 */
	public void setPrice(List<Price> price) {
		this._price = price;
	}

	/**
	 * @see ICategory#getRooms()
	 */
	public List<Room> getRooms(){
		return _rooms;
	}
	
	/**
	 * Sets the rooms of the category
	 * @param rooms The rooms of the category
	 */
	public void setRooms(List<Room> rooms){
		_rooms = rooms;
	}
	
	/**
	 * Add a room to the category
	 * @param r Room
	 */
	public void addRoom(Room r){
		_rooms.add(r);
	}
	
	/**
	 * Check every room in this category for the availability
	 * in the given timespan. The goal is to return as much rooms
	 * as given in the amount-parameter. If there arent enough rooms
	 * available, we return null.
	 * @param start StartDate of the availability
	 * @param end EndDate of the availability
	 * @param amount Amount of needed rooms in this category and timespan
	 * @return List<Room> of available rooms
	 */
	public List<Room> checkAvailability(Date start, Date end, int amount){
		List<Room> availableRooms = new ArrayList<Room>();
		int i = 0;
		//We check all the rooms of the category
		while(i < getRooms().size()){
			Room r = getRooms().get(i);
			if(r.checkAvailability(start, end)){
				//This room is free, add it to the returned list
				availableRooms.add(r);
			}
			i++;
		}
		return availableRooms;
	}
}
