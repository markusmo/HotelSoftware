/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.controller.data.parties.CountryData;
import java.util.Collection;

/**
 *Diese Klasse stellt ein Land da, von welchem eine Person oder eine Firma kommt
 * @author Dunst
 */
public class Country implements CountryData
{
    private Integer id;
    private String name;
    private String nameShort;

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
    
    public String getNameShort()
    {
        return nameShort;
    }

    public void setNameShort(String nameShort)
    {
        this.nameShort = nameShort;
    }
    
    public static Collection<Country> getAllCountries()
    {
        return PartyFacade.getInstance().getAllCountries();
    }
    
     @Override
     public String toString()
     {
         return name;
     }
/**
 * In dieser Methode wird geprüft, ob das objekt zu dieser Klasse gehört
 * @param obj
 * @return 
 */
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Country other = (Country) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
        {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name))
        {
            return false;
        }
        if ((this.nameShort == null) ? (other.nameShort != null) : !this.nameShort.equals(other.nameShort))
        {
            return false;
        }
        return true;
    }

  
   

  
}
