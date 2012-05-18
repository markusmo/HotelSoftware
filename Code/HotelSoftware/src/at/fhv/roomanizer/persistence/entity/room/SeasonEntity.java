package at.fhv.roomanizer.persistence.entity.room;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name="seasons")
public class SeasonEntity {
	/**
	 * The id of the season
	 */
	@Id
	@Generated(GenerationTime.INSERT)
	@Column(name="id")
	private int _id;
	/**
	 * The name of the season
	 */
	@Column(name="name")
	private String _name;
	/**
	 * The start of the season
	 */
	@Column(name="startDate")
	private Date _start;
	/**
	 * The end of the season
	 */
	@Column(name="endDate")
	private Date _end;

	/**
	 * Sets the id of the season
	 * @param id of the season
	 */
	public void setId(int id) {
		this._id = id;
	}
	
	/**
	 * Returns the id of the season
	 * @return id of the season
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
	 * Returns the name of the season
	 * @return The name of the season
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
	 * Returns the start of the season
	 * @return The start of the season
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
	 * Returns the end of the season
	 * @return The end of the season
	 */
	public Date getEnd(){
		return _end;
	}
	
}
