package at.fhv.roomanizer.domain.person;

public interface ICountry {
	/**
	 * returns the id of a country
	 * @return id
	 */
	public int getId();
	
	/**
	 * Returns the name of the country
	 * @return The name of the country
	 */
	public String getName();
	
	
	/**
	 * Returns the abbrevation of the country
	 * @return short name of the country
	 */
	public String getShortName();
}
