package at.fhv.roomanizer.domain.room;

/**
 * This status represents the status of a room, which includes whether it is free or occupied,
 * and if it is cleaned or not.
 * 
 * @author Daniel Rotter <daniel.rotter@students.fhv.at>
 */
public class Status implements IStatus {
	/**
	 * The id of the status
	 */
	private int _id;
	/**
	 * The name of the status
	 */
	private String _name;
	
	public Status(){
		
	}
	
	/**
	 * Returns the id of the status
	 * @return The id of the status 
	 */
	public int getId() {
		return _id;
	}
	
	/**
	 * Sets the id of the status
	 * @param id The id of the status
	 */
	public void setId(int id){
		_id = id;
	}
	
	/**
	 * Sets the name of the status
	 * @param name The name of the status
	 */
	public void setName(String name){
		_name = name;
	}
	
	/**
	 * Returns the name of the status
	 * @return The name of the status
	 */
	public String getName(){
		return _name;
	}
}
