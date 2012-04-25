/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.model.domain.parties.data.CountryData;
import java.util.Collection;

/**
 *
 * @author Dunst
 */
public class Country implements CountryData
{
    private Integer id;
    private String name;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    public static Collection<Country> getAllCountries()
    {
        return PartyFacade.getInstance().getAllCountries();
    }
}
