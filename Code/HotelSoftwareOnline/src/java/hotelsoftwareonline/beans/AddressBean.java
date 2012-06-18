/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.beans;

import hotelsoftware.model.domain.parties.ICountry;
import hotelsoftwareonline.controller.ReservationController;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class AddressBean implements Serializable
{

    private Integer id;
    private String street;
    private String city;
    private String zip;
    private String email;
    private String phone;
    private String fax;
    private ICountry domainCountry;

    public ICountry getDomainCountry()
    {
        return domainCountry;
    }

    public void setDomainCountry(ICountry domainCountry)
    {
        this.domainCountry = domainCountry;
    }
    
    public String getCountry()
    {
        return domainCountry.getName();
    }

    public void setCountry(String country)
    {
        this.domainCountry.setName(country);
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFax()
    {
        return fax;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    public Collection<String> getCountries()
    {
        return ReservationController.getCountries();
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        if (id == null)
        {
            this.id = id;
        }
    }

    @Override
    public String toString()
    {
        StringBuilder returnstring = new StringBuilder();
        String newline = "\n";

        returnstring.append(street);
        returnstring.append(newline);

        returnstring.append(zip);
        returnstring.append(" ");
        returnstring.append(city);
        returnstring.append(newline);

        returnstring.append(this.getCountry());
        returnstring.append(newline);

        returnstring.append("Phone: ");
        returnstring.append(phone);
        returnstring.append(newline);

        returnstring.append("Fax: ");
        returnstring.append(fax);
        returnstring.append(newline);

        returnstring.append(email);

        return returnstring.toString();
    }
}
