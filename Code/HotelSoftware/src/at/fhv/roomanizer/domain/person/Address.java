package at.fhv.roomanizer.domain.person;

/**
 * Every Person has exactly one Address, which represents the
 * "main living place" and foremost, the billing address.
 * 
 * To simplify matters, address also contains informations like email, phone and
 * fax. The hotel contacts every type of person through this piece of data.
 * 
 * @author Shady
 * 
 */
public class Address implements IAddress {

	/**
	 * ID of the Address
	 */
	private int _id;
	/**
	 * Street and Housenumber of the Address
	 */
	private String _street;

	/**
	 * City of the Address
	 */
	private String _city;
	/**
	 * ZIP number of the Address
	 */
	private String _zip;
	/**
	 * Country of the address
	 */
	private Country _country;
	/**
	 * Email address
	 */
	private String _email;
	/**
	 * Phone of the Address
	 */
	private String _phone;
	/**
	 * Fax of the Address
	 */
	private String _fax;

	/*------------- GETTER AND SETTER -----------*/

	/**
	 * @see IAddress#getId()
	 */
	public int getId() {
		return _id;
	}

	/**
	 * Sets the ID of the address
	 * 
	 * @param id
	 *            int ID of the address
	 */
	public void setId(int id) {
		_id = id;
	}

	/**
	 * @see IAddress#getStreet()
	 */
	public String getStreet() {
		return _street;
	}

	/**
	 * Sets the name and number of the street
	 * 
	 * @param street
	 *            String street name and number
	 */
	public void setStreet(String street) {
		_street = street;
	}

	/**
	 * @see IAddress#getCity()
	 */
	public String getCity() {
		return _city;
	}

	/**
	 * Sets the city of the Address
	 * 
	 * @param city
	 *            String name of the city
	 */
	public void setCity(String city) {
		_city = city;
	}

	/**
	 * @see IAddress#getZip()
	 */
	public String getZip() {
		return _zip;
	}

	/**
	 * Sets ZIP address of the address
	 * 
	 * @param zip
	 *            String zip number
	 */
	public void setZip(String zip) {
		_zip = zip;
	}

	/**
	 * @see IAddress#getEmail()
	 */
	public String getEmail() {
		return _email;
	}

	/**
	 * Sets the email address of the address
	 * 
	 * @param email
	 *            String email address
	 */
	public void setEmail(String email) {
		_email = email;
	}

	/**
	 * @see IAddress#getPhone()
	 */
	public String getPhone() {
		return _phone;
	}

	/**
	 * Sets the phone number
	 * 
	 * @param phone
	 *            String phone number
	 */
	public void setPhone(String phone) {
		_phone = phone;
	}

	/**
	 * @see IAddress#getFax()
	 */
	public String getFax() {
		return _fax;
	}

	/**
	 * Sets the fax number of the address
	 * 
	 * @param fax
	 *            String Fax number
	 */
	public void setFax(String fax) {
		_fax = fax;
	}
	/**
	 * Sets the country of a person
	 * @param country
	 */
	public void setCountry(Country country){
		_country = country;
	}
	
	/**
	 * Returns the country of the address
	 * @return country of the address
	 */
	public Country getCountry() {
		return _country;
	}

	/**
	 * see IAddress#getICountry 
	 * @return a country interface
	 */
	public ICountry getICountry() {
		return (ICountry) _country;
	}
}
