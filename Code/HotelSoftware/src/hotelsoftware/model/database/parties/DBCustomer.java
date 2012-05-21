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
@Table(name = "customers", catalog = "`roomanizer-dev`", schema = "")
@PrimaryKeyJoinColumn(name="idParties", referencedColumnName="idParties")
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement
public class DBCustomer extends DBParty implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @OneToMany(mappedBy = "customer")
    private Set<DBInvoice> invoicesCollection;
    
    @JoinColumn(name = "idAddresses", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, cascade= CascadeType.ALL)
    private DBAddress invoiceAddress;

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
        final DBCustomer other = (DBCustomer) obj;
        if (this.invoicesCollection != other.invoicesCollection && (this.invoicesCollection == null || !this.invoicesCollection.equals(other.invoicesCollection)))
        {
            return false;
        }
        if (this.invoiceAddress != other.invoiceAddress && (this.invoiceAddress == null || !this.invoiceAddress.equals(other.invoiceAddress)))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + (this.invoicesCollection != null ? this.invoicesCollection.hashCode() : 0);
        hash = 97 * hash + (this.invoiceAddress != null ? this.invoiceAddress.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Customers[ id=" + getIdParties() + " ]";
    }   
}
