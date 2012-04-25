package hotelsoftware.model.domain.parties;

import hotelsoftware.model.domain.parties.data.PartyData;
import hotelsoftware.model.domain.parties.data.AddressData;

/**
 * Klasse die eine oder Mehrere Personen beschreibt. Sie ist abstrakt, da von ihr alle Kunden und auch die Gäste erben.
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Party implements PartyData
{
    protected Address address;
    protected Integer id;

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

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        if (this.id == null)
        {
            this.id = id;
        }
    }

    public AddressData getAddressData()
    {
        return address;
    }
}
