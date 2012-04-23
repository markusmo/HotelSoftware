/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

/**
 * 
 * @author Lins Christian (christian.lins87@gmail.com)
 * @author Hubert
 */
public class Address implements AddressData {

	private Integer id;
	private String street;
	private String city;
	private String zip;
	private String email;
	private String phone;
	private String fax;
	private String country;

	public static Address create(String street, String city, String zip,
			String email, String phone, String fax, String country) {
		return new Address(street, city, zip, email, phone, fax, country);
	}

	Address() {
	}

	private Address(String street, String city, String zip, String email,
			String phone, String fax, String country) {

		this.street = street;
		this.city = city;
		this.zip = zip;
		this.email = email;
		this.phone = phone;
		this.fax = fax;
		this.country = country;

	}

    @Override
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

    @Override
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

    @Override
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

    @Override
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    @Override
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

    @Override
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

    @Override
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		if (id == null)
			this.id = id;
	}

}
