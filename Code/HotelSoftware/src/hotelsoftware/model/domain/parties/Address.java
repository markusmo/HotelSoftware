package hotelsoftware.model.domain.parties;

/**
 * Klasse die die Attribute einer Addresse hällt. Hier werden alle Kontaktdaten einer Party (Gruppierung oder Person) gehalten.
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Address implements IAddress
{
    private Integer id;
    private String street;
    private String city;
    private String zip;
    private String email;
    private String phone;
    private String fax;
    private ICountry idCountry;

    public Address()
    {
    }

    @Override
    public String getStreet()
    {
        return street;
    }

    @Override
    public void setStreet(String street)
    {
        this.street = street;
    }

    @Override
    public String getCity()
    {
        return city;
    }

    @Override
    public void setCity(String city)
    {
        this.city = city;
    }

    @Override
    public String getZip()
    {
        return zip;
    }

    @Override
    public void setZip(String zip)
    {
        this.zip = zip;
    }

    @Override
    public String getEmail()
    {
        return email;
    }

    @Override
    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public String getPhone()
    {
        return phone;
    }

    @Override
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    @Override
    public String getFax()
    {
        return fax;
    }

    @Override
    public void setFax(String fax)
    {
        this.fax = fax;
    }

    @Override
    public ICountry getIdCountry()
    {
        return idCountry;
    }

    @Override
    public void setIdCountry(ICountry country)
    {
        this.idCountry = country;
    }

    @Override
    public Integer getId()
    {
        return id;
    }

    @Override
    public void setId(Integer id)
    {
        if (this.id == null)
        {
            this.id = id;
        }
    }
}
