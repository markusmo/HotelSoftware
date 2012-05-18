package at.fhv.roomanizer.domain.room;

import java.util.Date;

public interface IRoomStatus {
	public Room getRoom();

	public IStatus getIStatus();

	public Date getStart();

	public Date getEnd();
}
