package hotelsoftware.model.domain.parties;

import hotelsoftware.controller.data.parties.PartyData;
import hotelsoftware.controller.data.parties.AddressData;

/**
 * Klasse die eine oder Mehrere Personen beschreibt. Sie ist abstrakt, da von ihr alle Kunden und auch die Gäste erben.
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Party implements IParty
{
    protected IAddress address;
    protected Integer idParties;

    public Party()
    {
    }

    protected Party(IAddress address)
    {
        this.address = address;
    }

    @Override
    public IAddress getAddress()
    {
        return address;
    }

    @Override
    public void setAddress(IAddress address)
    {
        this.address = address;
    }

    @Override
    public Integer getIdParties()
    {
        return idParties;
    }

    @Override
    public void setIdParties(Integer id)
    {
        if (this.idParties == null)
        {
            this.idParties = id;
        }
    }

    @Override
    public AddressData getAddressData()
    {
        return address;
    }
}
