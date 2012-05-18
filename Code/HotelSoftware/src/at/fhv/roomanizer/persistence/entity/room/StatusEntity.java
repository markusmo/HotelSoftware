package at.fhv.roomanizer.persistence.entity.room;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * This status represents the status of a room, which includes whether it is free or occupied,
 * and if it is cleaned or not.
 * 
 * @author Daniel Rotter <daniel.rotter@students.fhv.at>
 * @author Andreas Sinz <andreas.sinz@students.fhv.at>
 */

@Entity
@Table(name="roomStatus")
public class StatusEntity {
	/**
	 * The id of the status
	 */
	@Id
	@Generated(GenerationTime.INSERT)
	@Column(name="id")
	private int _id;
	/**
	 * The name of the status
	 */
	@Column(name="name")
	private String _name;
	
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
	
	/**
	 * Sets the id of the status
	 * @param id of the status
	 */
	public void setId(int id) {
		this._id = id;
	}
	
	/**
	 * Returns the id of the status
	 * @return Id of the status
	 */
	public int getId() {
		return this._id;
	}
}
