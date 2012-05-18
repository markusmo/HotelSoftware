package at.fhv.roomanizer.domain.person;

public interface IAddress {
	/**
	 * Returns The id of the address
	 * @return String id
	 */
	public int getId();

		/**
	 * Returns the street name and number
	 * @return String street and number
	 */
	public String getStreet();
	
	/**
	 * Returns the name of the city
	 * @return String the city
	 */
	public String getCity();
	
	/**
	 * Returns the street name and number
	 * @return String zip number
	 */
	public String getZip();

	 /**
	 * Returns the street name and number
	 * @return String email address
	 */
	public String getEmail();
	
	/**
	 * Returns phone number
	 * @return String phone number
	 */
	public String getPhone();
	
	/**
	 * Returns the fax number
	 * @return String fax number
	 */
	public String getFax() ;
	
	/**
	 * Returns the country as interface of the address
	 * @return a country interface
	 */
	public ICountry getICountry();
}
