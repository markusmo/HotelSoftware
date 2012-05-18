package at.fhv.roomanizer.persistence.entity.room;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import at.fhv.roomanizer.persistence.entity.HabitationEntity;

/**
 * Represents a room in the hotel.
 * 
 * @author Daniel Rotter <daniel.rotter@students.fhv.at>
 * @author Andreas Sinz <andreas.sinz@students.fhv.at>
 */

@Entity
@Table(name="rooms")
public class RoomEntity {
	/**
	 * The id of the room
	 */
	@Id
	@Generated(GenerationTime.INSERT)
	@Column(name="id")
	private int _id;
	/**
	 * The number of the room
	 */
	@Column(name="roomNumber")
	private String _number;
	/**
	 * The status of the room
	 */
	@OneToMany(mappedBy="_room")
	private List<RoomStatusEntity> _statusList = new ArrayList<RoomStatusEntity>();
	/**
	 * A list containing the habitations of this room
	 */
	
	@OneToMany(mappedBy="_room")
	private List<HabitationEntity> _habitationList = new ArrayList<HabitationEntity>();
	/**
	 * The category of this room
	 */
	@ManyToOne
	@JoinColumn(name="idRoomCategories")
	private CategoryEntity _category;
	
	/**
	 * Sets the number of the room
	 * @param number The number of the room
	 */
	public void setNumber(String number){
		_number = number;
	}
	
	/**
	 * Returns the number of the room
	 * @return The number of the room
	 */
	public String getNumber(){
		return _number;
	}
	

	
	/**
	 * Returns a list with all the habitations from this room
	 * @return A list with all the habitations from this room
	 */
	public List<HabitationEntity> getHabitations(){
		return _habitationList;
	}
	
	public void setHabitations(List<HabitationEntity> habitations) {
		this._habitationList = habitations;
	}
	
	/**
	 * Sets the category of the room
	 * @param category The category of the room
	 */
	public void setCategory(CategoryEntity category){
		_category = category;
	}
	
	/**
	 * Returns the category of the room
	 * @return The category of the room
	 */
	public CategoryEntity getCategory(){
		return _category;
	}
	
	/**
	 * Sets the id of the room
	 * @param id of the room
	 */
	public void setId(int id) {
		this._id = id;
	}
	
	/**
	 * Returns the id of the room
	 * @return id of the room
	 */
	public int getId() {
		return this._id;
	}

	/**
	 * Get a list of status for this room
	 * @return A list of status for this room
	 */
	public List<RoomStatusEntity> getStatus() {
		return _statusList;
	}

	/**
	 * Sets the list of status of the room
	 * @param status list of status of the room
	 */
	public void setStatus(List<RoomStatusEntity> status) {
		this._statusList = status;
	}
}
