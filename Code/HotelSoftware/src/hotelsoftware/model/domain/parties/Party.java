package hotelsoftware.model.domain.parties;

import hotelsoftware.controller.data.parties.AddressData;
import hotelsoftware.support.CompanyNotFoundException;
import hotelsoftware.support.GuestNotFoundException;
import hotelsoftware.support.PrivateCustomerNotFoundException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    /**
     * Sucht Parteien nach einem Text-Pattern
     * @param text das Pattern
     * @return eine Liste von Parteien, die dem Pattern ähnlich sind
     */
    public static Collection<IParty> searchParties(String text)
    {
        String[] words = text.split(" ");
        LinkedList<IParty> list = new LinkedList<IParty>();

        for (String s : words)
        {
            list.addAll(Guest.getGuestByFName(s));
            list.addAll(Guest.getGuestByLName(s));
            list.addAll(Company.getCompaniesByName(s));
            try
            {
                list.addAll(PrivateCustomer.getPrivateCustomerByFName(s));
                list.addAll(PrivateCustomer.getPrivateCustomerByLName(s));
            }
            catch (CompanyNotFoundException ex)
            {
                Logger.getLogger(Party.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (PrivateCustomerNotFoundException ex)
            {
                Logger.getLogger(Party.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (GuestNotFoundException ex)
            {
                Logger.getLogger(Party.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + (this.address != null ? this.address.hashCode() : 0);
        hash = 23 * hash + (this.idParties != null ? this.idParties.hashCode() : 0);
        return hash;
    }
}
