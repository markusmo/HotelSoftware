package at.fhv.roomanizer.domain.room;

import java.util.Date;

public class RoomStatus implements IRoomStatus {

	private int _id;
	
	private Room _room;
	
	private Status _status;
	
	private Date _start;
	
	private Date _end;
	
	public int getId(){
		return _id;
	}
	
	public void setId(int id){
		this._id = id;
	}
	
	public Room getRoom() {
		return _room;
	}

	public void setRoom(Room room) {
		this._room = room;
	}

	public Status getStatus() {
		return _status;
	}

	public void setStatus(Status status) {
		this._status = status;
	}

	public Date getStart() {
		return _start;
	}

	public void setStart(Date start) {
		this._start = start;
	}

	public Date getEnd() {
		return _end;
	}

	public void setEnd(Date end) {
		this._end = end;
	}

	@Override
	public IStatus getIStatus() {
		return (IStatus) _status;
	}

}
