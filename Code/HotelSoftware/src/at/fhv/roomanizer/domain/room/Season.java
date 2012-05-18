package at.fhv.roomanizer.domain.room;

import java.util.Date;

/**
 * Represents a season, which is a duration at the hotel, with its own prices.
 * 
 * @author Daniel Rotter <daniel.rotter@students.fhv.at>
 */
public class Season implements ISeason {
	/**
	 * The id of the season
	 */
	private int _id;
	/**
	 * The name of the season
	 */
	private String _name;
	/**
	 * The start of the season
	 */
	private Date _start;
	/**
	 * The end of the season
	 */
	private Date _end;
	
	/**
	 * Sets the id of the season
	 * @param id is the id of the season
	 */
	public void setId(int id) {
		this._id = id;
	}
	
	/**
	 * @see ISeason#getId()
	 */
	public int getId() {
		return this._id;
	}
	
	/**
	 * Sets the name of the season
	 * @param name The name of the season
	 */
	public void setName(String name) {
		_name = name;
	}
	
	/**
	 * @see ISeason#getName()
	 */
	public String getName(){
		return _name;
	}
	
	/**
	 * Sets the start of the season
	 * @param start The start of the season
	 */
	public void setStart(Date start){
		_start = start;
	}
	
	/**
	 * @see ISeason#getStart()
	 */
	public Date getStart(){
		return _start;
	}
	
	/**
	 * Sets the end of the season
	 * @param end The end of the season
	 */
	public void setEnd(Date end){
		_end = end;
	}
	
	/**
	 * @see ISeason#getEnd()
	 */
	public Date getEnd(){
		return _end;
	}
}
