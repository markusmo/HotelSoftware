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

    @Override
    String getCity();

    @Override
    String getEmail();

    @Override
    String getFax();

    Integer getId();

    @Override
    ICountry getIdCountry();

    @Override
    String getPhone();

    @Override
    String getStreet();

    @Override
    String getZip();

    void setCity(String city);

    void setEmail(String email);

    void setFax(String fax);

    void setId(Integer id);

    void setIdCountry(ICountry country);

    void setPhone(String phone);

    void setStreet(String street);

    void setZip(String zip);
    
}
