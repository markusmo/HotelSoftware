package hotelsoftware.model.domain.parties;

import hotelsoftware.controller.data.parties.PartyData;
import hotelsoftware.controller.data.parties.AddressData;

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
}
