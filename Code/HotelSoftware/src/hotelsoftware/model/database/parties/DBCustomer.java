package hotelsoftware.model.database.parties;

import hotelsoftware.model.database.invoice.DBInvoice;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *Dies ist die Klasse für die Tabelle "customers". Hier werden alle Kunden gespeichert.
 * @author mohi
 */
@Entity
@Table(name = "customers", catalog = "`roomanizer`", schema = "")
@PrimaryKeyJoinColumn(name="idParties", referencedColumnName="idParties")
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement
public class DBCustomer extends DBParty implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @OneToMany(mappedBy = "customer", fetch= FetchType.EAGER)
    private Set<DBInvoice> invoicesCollection;
    
    @JoinColumn(name = "idAddresses", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, cascade= CascadeType.ALL)
    private DBAddress invoiceAddress;
    
    @Column(name = "username", length = 255)
    private String username;
    
    @Column(name = "password", length = 32)
    private String password;

    public DBCustomer()
    {
    }

    @XmlTransient
    public Collection<DBInvoice> getInvoices()
    {
        return invoicesCollection;
    }

    public void setInvoices(Set<DBInvoice> invoicesCollection)
    {
        this.invoicesCollection = invoicesCollection;
    }

    public DBAddress getInvoiceAddress()
    {
        return invoiceAddress;
    }

    public void setInvoiceAddress(DBAddress address)
    {
        this.invoiceAddress = address;
    }
    
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Customers[ id=" + getIdParties() + " ]";
    }   
}
