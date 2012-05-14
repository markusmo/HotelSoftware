/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.controller.data.parties.AddressData;

/**
 *
 * @author Kno
 */
public interface IAddress extends AddressData {

    String getCity();

    String getEmail();

    String getFax();

    Integer getId();

    Country getIdCountry();

    String getPhone();

    String getStreet();

    String getZip();

    void setCity(String city);

    void setEmail(String email);

    void setFax(String fax);

    void setId(Integer id);

    void setIdCountry(Country country);

    void setPhone(String phone);

    void setStreet(String street);

    void setZip(String zip);
    
}
