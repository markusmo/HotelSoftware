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
@Table(name = "customers", catalog = "roomanizer", schema = "")
@PrimaryKeyJoinColumn(name="idParties", referencedColumnName="idParties")
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement
public class DBCustomer extends DBParty implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCustomers")
    private Set<DBInvoice> invoicesCollection;
    
    @JoinColumn(name = "idAddresses", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBAddress invoiceAddress;

    public DBCustomer()
    {
    }

    @XmlTransient
    public Set<DBInvoice> getInvoices()
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
    public int hashCode()
    {
        int hash = 0;
        hash += (getIdParties() != null ? getIdParties().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof DBCustomer))
        {
            return false;
        }
        DBCustomer other = (DBCustomer) object;
        if((this.getIdParties() == null && other.getIdParties() != null) 
                || (this.getIdParties() != null && !this.getIdParties().equals(other.getIdParties())))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Customers[ id=" + getIdParties() + " ]";
    }   
}
