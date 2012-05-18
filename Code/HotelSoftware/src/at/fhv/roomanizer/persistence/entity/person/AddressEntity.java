package at.fhv.roomanizer.persistence.entity.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * Every Person has exactly one Address, which represents
 * the "main living place" and foremost, the billing address.
 * 
 * To simplify matters, address also contains informations like email, phone
 * and fax. The hotel contacts every type of person through this piece of data. 
 * 
 * @author Sinz
 *
 */
@Entity
@Table(name="addresses")
public class AddressEntity {

	/**
	 * ID of the Address
	 */
	@Id
	@Generated(GenerationTime.INSERT)
	@Column(name="id")
	private int _id;
	
	
	/*@ManyToOne
	@JoinColumn(name="idCountries")
	private CountryEntity _country;*/
	
	/**
	 * Street and Housenumber of the Address
	 */
	@Column(name="street")
	private String _street;
	
	/**
	 * City of the Address
	 */
	@Column(name="city")
	private String _city;
	/**
	 * ZIP number of the Address
	 */
	@Column(name="zip")
	private String _zip;
	/**
	 * Email address
	 */
	@Column(name="email")
	private String _email;
	/**
	 * Phone of the Address
	 */
	@Column(name="phone")
	private String _phone;
	/**
	 * Fax of the Address
	 */
	@Column(name="fax")
	private String _fax;
	

	@ManyToOne
	@JoinColumn(name="idCountries")
	private CountryEntity _country;
	
	/*------------- GETTER AND SETTER -----------*/
	
	/**
	 * Returns The id of the address
	 * @return String id
	 */
	public int getId() {
		return _id;
	}
	/**
	 * Sets the ID of the address
	 * @param id int ID of the address
	 */
	public void setId(int id) {
		_id = id;
	}
	
	/**
	 * Returns the street name and number
	 * @return String street and number
	 */
	public String getStreet() {
		return _street;
	}
	
	/**
	 * Sets the name and number of the street
	 * @param street String street name and number
	 */
	public void setStreet(String street) {
		_street = street;
	}
	
	/**
	 * Returns the name of the city
	 * @return String the city
	 */
	public String getCity() {
		return _city;
	}
	
	/**
	 * Sets the city of the Address
	 * @param city String name of the city
	 */
	public void setCity(String city) {
		_city = city;
	}
	
	/**
	 * Returns the street name and number
	 * @return String zip number
	 */
	public String getZip() {
		return _zip;
	}
	
	/**
	 * Sets ZIP address of the address
	 * @param zip String zip number
	 */
	public void setZip(String zip) {
		_zip = zip;
	}
	
	/**
	 * Returns the street name and number
	 * @return String email address
	 */
	public String getEmail() {
		return _email;
	}
	
	/**
	 * Sets the email address of the address
	 * @param email String email address
	 */
	public void setEmail(String email) {
		_email = email;
	}
	
	/**
	 * Returns phone number
	 * @return String phone number
	 */
	public String getPhone() {
		return _phone;
	}
	/**
	 * Sets the phone number
	 * @param phone String phone number
	 */
	public void setPhone(String phone) {
		_phone = phone;
	}
	
	/**
	 * Returns the fax number
	 * @return String fax number
	 */
	public String getFax() {
		return _fax;
	}
	/**
	 * Sets the fax number of the address
	 * @param fax String Fax number
	 */
	public void setFax(String fax) {
		_fax = fax;
	}
	/**
	 * Returns the country of the address
	 * @return Country of the address
	 */
	public CountryEntity getCountry() {
		return _country;
	}
	
	/**
	 * Sets the country of the address
	 * @param country Country of the address
	 */
	public void setCountry(CountryEntity country) {
		this._country = country;
	}
}
