package hotelsoftware.model.domain.parties;

import hotelsoftware.model.database.manager.PartyManager;
import hotelsoftware.support.CompanyNotFoundException;
import hotelsoftware.support.GuestNotFoundException;
import hotelsoftware.support.PrivateCustomerNotFoundException;
import java.util.Collection;
import java.util.Set;

/**
 * Privatperson die als Ansprechpartner für Rechnungen dient. Sie besitzt einen
 * Geschlecht, einen Vornamen und einen Nachnamen. Da diese Klasse von Customer
 * erbt, erbt sie auch von der Klasse Party und hat somit eine Addresse und eine
 * Id.
 *
 * @author Hubert
 *
 */
public class PrivateCustomer extends Customer implements IPrivateCustomer
{
    private String fname;
    private String lname;
    private Character gender;

    public PrivateCustomer()
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
     * Eine neue Instanz
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

    @Override
    public String getFname()
    {
        return fname;
    }

    @Override
    public void setFname(String fname)
    {
        this.fname = fname;
    }

    @Override
    public String getLname()
    {
        return lname;
    }

    @Override
    public void setLname(String lname)
    {
        this.lname = lname;
    }

    @Override
    public Character getGender()
    {
        return gender;
    }

    @Override
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
    public static IPrivateCustomer getPrivateCustomerByName(String firstname, String lastname)
            throws CompanyNotFoundException, PrivateCustomerNotFoundException, GuestNotFoundException
    {
        return PartyManager.getInstance().getPrivateCustomerByName(firstname,
                lastname);
    }
    
    /**
     * Sucht Privatkunden nach Vornamen
     * @param firstname der Vorname des Privatkunden
     * @return ein Set von Privatkunden mit ähnlichen Namen
     * @throws CompanyNotFoundException
     * @throws PrivateCustomerNotFoundException
     * @throws GuestNotFoundException 
     */
    public static Set<IPrivateCustomer> getPrivateCustomerByFName(String firstname)
            throws CompanyNotFoundException, PrivateCustomerNotFoundException, GuestNotFoundException
    {
        return PartyManager.getInstance().getPrivateCustomerByFName(firstname);
    }
    
    /**
     * Sucht Privatkunden nach Nachnamen
     * @param lastname der Nachname des Privatkunden
     * @return ein Set von Privatkunden mit ähnlichen Namen
     * @throws CompanyNotFoundException
     * @throws PrivateCustomerNotFoundException
     * @throws GuestNotFoundException 
     */
    public static Set<IPrivateCustomer> getPrivateCustomerByLName(String lastname)
            throws CompanyNotFoundException, PrivateCustomerNotFoundException, GuestNotFoundException
    {
        return PartyManager.getInstance().getPrivateCustomerByLName(lastname);
    }

    @Override
    public String toString()
    {
        return fname + " " + lname;
    }
}
