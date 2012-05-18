package at.fhv.roomanizer.domain.room;

import java.util.Date;

public interface IRoomStatus {
	public IRoom getRoom();

	public IStatus getIStatus();

	public Date getStart();

	public Date getEnd();
}
