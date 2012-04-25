package hotelsoftware.model.domain.parties;

import hotelsoftware.model.domain.parties.data.AddressData;
import hotelsoftware.model.domain.invoice.data.InvoiceData;

import java.util.Collection;
import java.util.Set;

/**
 * Privatperson die als Ansprechpartner für Rechnungen dient. Sie besitzt einen
 * Geshlecht, einen Vornamen und einen Nachnamen. Da diese Klasse von Customer
 * erbt, erbt sie auch von der Klasse Party und hat somit eine Addresse und eine
 * Id.
 *
 * @author Hubert
 *
 */
public class PrivateCustomer extends Customer
{

    private String fname;
    private String lname;
    private Character gender;

    PrivateCustomer()
    {
    }

    /**
     * Instanziert einen neuen Privatkunden
     *
     * @param fname Vorname des Kunden
     * @param lname Nachname des Kunden
     * @param gender Geschlecht des Kunden (im Falle, das der Gast == Kunde ist)
     * @param invoiceAddress Rechungsadresse des Kunden
     * @param address Die Adresse des Kunden
     * @return
     */
    public static PrivateCustomer create(String fname, String lname,
            Character gender, Address invoiceAddress, Address address)
    {
        return new PrivateCustomer(fname, lname, gender, invoiceAddress,
                address);
    }

    private PrivateCustomer(String fname, String lname, Character gender,
            Address invoiceAddress, Address address)
    {
        super(address, invoiceAddress);
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
    }

    @Override
    public String getName()
    {
        return fname + " " + lname;
    }

    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    public String getLname()
    {
        return lname;
    }

    public void setLname(String lname)
    {
        this.lname = lname;
    }

    public Character getGender()
    {
        return gender;
    }

    public void setGender(Character gender)
    {
        this.gender = gender;
    }

    /**
     * sucht in der datenbank nach einem PrivateCustomer
     *
     * @param firstname vorname
     * @param lastname nachname
     * @return Objekt von PrivateCustomer
     * @throws CompanyNotFoundException Firma nicht gefunden
     * @throws PrivateCustomerNotFoundException Kunde nicht gefunden
     * @throws GuestNotFoundException Gast nicht gefunden
     */
    public static PrivateCustomer getPrivateCustomerByName(String firstname,
            String lastname)
            throws CompanyNotFoundException, PrivateCustomerNotFoundException, GuestNotFoundException
    {
        return PartyFacade.getInstance().getPrivateCustomerByName(firstname,
                lastname);
    }

    @Override
    public AddressData getInvoiceAddressData()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<InvoiceData> getInvoicesData()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AddressData getAddressData()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
