/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.controller.data.parties.CountryData;

/**
 *
 * @author Kno
 */
public interface ICountry extends CountryData{

    boolean equals(Object obj);

    Integer getId();

    String getName();

    String getNameShort();

    int hashCode();

    void setId(Integer id);

    void setName(String name);

    void setNameShort(String nameShort);

    String toString();
    
}
