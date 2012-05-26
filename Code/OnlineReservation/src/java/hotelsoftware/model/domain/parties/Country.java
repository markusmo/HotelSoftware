/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import java.util.Collection;

/**
 *Diese Klasse stellt ein Land da, von welchem eine Person oder eine Firma kommt
 * @author Dunst
 */
public class Country implements ICountry
{
    private Integer id;
    private String name;
    private String nameShort;

    @Override
    public Integer getId()
    {
        return id;
    }

    @Override
    public void setId(Integer id)
    {
        this.id = id;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }
    
    @Override
    public String getNameShort()
    {
        return nameShort;
    }

    @Override
    public void setNameShort(String nameShort)
    {
        this.nameShort = nameShort;
    }
    
    public static Collection<ICountry> getAllCountries()
    {
        return PartyManager.getInstance().getAllCountries();
    }
    
     @Override
     public String toString()
     {
         return name;
     }
     
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

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 67 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 67 * hash + (this.nameShort != null ? this.nameShort.hashCode() : 0);
        return hash;
    }

  
   

  
}
