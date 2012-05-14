package hotelsoftware.model.domain.parties;

import hotelsoftware.controller.data.parties.PartyData;
import hotelsoftware.controller.data.parties.AddressData;
import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.parties.DBCompany;
import hotelsoftware.model.database.parties.DBGuest;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Klasse die eine oder Mehrere Personen beschreibt. Sie ist abstrakt, da von ihr alle Kunden und auch die Gäste erben.
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Party implements PartyData
{
    protected Address address;
    protected Integer idParties;

    public Party()
    {
    }

    protected Party(Address address)
    {
        this.address = address;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public Integer getIdParties()
    {
        return idParties;
    }

    public void setIdParties(Integer id)
    {
        if (this.idParties == null)
        {
            this.idParties = id;
        }
    }

    public AddressData getAddressData()
    {
        return address;
    }
    
    public static Collection<Party> searchParties(String text){
        String[] words = text.split(" ");
        LinkedList<Party> list = new LinkedList<Party>();
        
        for(String s : words)
        {
            list.addAll(DynamicMapper.mapCollection(DBGuest.getGuestsByFName(s)));
            list.addAll(DynamicMapper.mapCollection(DBGuest.getGuestsByLName(s)));
            list.addAll(DynamicMapper.mapCollection(DBCompany.getCompaniesByName(s)));
        }
        return list;
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
        final Party other = (Party) obj;
        if (this.idParties != other.idParties && (this.idParties == null || !this.idParties.equals(other.idParties)))
        {
            return false;
        }
        return true;
    }
}
