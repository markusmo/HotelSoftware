/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinereservation.beans;

import java.io.Serializable;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class AddressBean implements Serializable
{
    private String street;
    private String city;
    private String zip;
    private String email;
    private String phone;
    private String fax;

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
}
