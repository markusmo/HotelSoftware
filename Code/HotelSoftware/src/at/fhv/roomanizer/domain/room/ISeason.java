package at.fhv.roomanizer.domain.room;

import java.util.Date;

public interface ISeason {
	
	/**
	 * Returns the id of the sason
	 * @return int id of the season
	 */
	public int getId();
	
	/**
	 * Returns the name of the season
	 * @return The name of the season
	 */
	public String getName();
	
	/**
	 * Returns the start of the season
	 * @return The start of the season
	 */
	public Date getStart();
	
	/**
	 * Returns the end of the season
	 * @return The end of the season
	 */
	public Date getEnd();
}
