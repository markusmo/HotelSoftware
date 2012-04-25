package hotelsoftware.model.domain.parties;

import hotelsoftware.model.domain.parties.data.AddressData;

/**
 * Klasse die die Attribute einer Addresse hällt. Hier werden alle Kontaktdaten einer Party (Gruppierung oder Person) gehalten.
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Address implements AddressData {

	private Integer id;
	private String street;
	private String city;
	private String zip;
	private String email;
	private String phone;
	private String fax;
	private String idCountry;

        /**
         * Instanziert eine neue Adresse
         * @param street
         * Die Strasse zu dieser Adresse
         * @param city
         * Die Stadt zu dieser Adresse
         * @param zip
         * Die Postleitzahl zu dieser Adresse
         * @param email
         * Die Email, zugehoerig zu dieser Adresse
         * @param phone
         * Der Telefonanschluss zu dieser Adresse
         * @param fax
         * Der Faxanschluss zu dieser Adresse
         * @param country
         * Das Land, in dem diese Adresse zu finden ist
         * @return 
         * Eine neue Adresse
         */
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
		this.idCountry = country;

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
	public String getIdCountry() {
		return idCountry;
	}

	public void setIdCountry(String country) {
		this.idCountry = country;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		if (id == null)
			this.id = id;
	}

}
