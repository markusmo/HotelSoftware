package at.fhv.roomanizer.persistence.entity.room;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="roomsRoomStatus")
public class RoomStatusEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7298805771897364586L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int _id;

	/**
	 * Reference to the room, which the room status belongs to
	 */
	@OneToOne
	@JoinColumn(name="idRooms")
	private RoomEntity _room;
	
	/**
	 * Reference to the status, which the room status belongs to
	 */
	@OneToOne
	@JoinColumn(name="idRoomStatus")
	private StatusEntity _status;
	
	/**
	 * Date, at which the status gets active
	 */
	@Column(name="startDate")
	private Date _start;
	
	/**
	 * Date, at which the status stops to be active
	 */
	@Column(name="endDate")
	private Date _end;

	/**
	 * Returns the room, which the room status belongs to
	 * @return room, which the room status belongs to
	 */
	public RoomEntity getRoom() {
		return _room;
	}

	/**
	 * Sets the room, which the room status belongs to
	 * @param room, which the room status belongs to
	 */
	public void setRoom(RoomEntity room) {
		this._room = room;
	}

	/**
	 * Returns the status of the room status
	 * @return status, which belongs to the room status
	 */
	public StatusEntity getStatus() {
		return _status;
	}

	/**
	 * Sets the status of the room status
	 * @param status of the room status
	 */
	public void setStatus(StatusEntity status) {
		this._status = status;
	}

	/**
	 * Returns the start date of the room status
	 * @return Start date of the room status
	 */
	public Date getStart() {
		return _start;
	}

	/**
	 * Sets the start date of the room status
	 * @param start date of the room status
	 */
	public void setStart(Date start) {
		this._start = start;
	}

	/**
	 * Returns the end date of the room status
	 * @return End date of the room status
	 */
	public Date getEnd() {
		return _end;
	}

	/**
	 * Sets the end date of the room status
	 * @param end date of the room status
	 */
	public void setEnd(Date end) {
		this._end = end;
	}

	/**
	 * Returns the id of the room-status
	 * @return Id of the room status
	 */
	public int getId() {
		return _id;
	}

	/**
	 * Sets the id of the room-status
	 * @param id Id of the room-status
	 */
	public void setId(int id) {
		this._id = id;
	}
}
