/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.database.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "users", catalog = "roomanizer", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "username"
    })
})
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password")
})
public class Users implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "username", nullable = false, length = 255)
    private String username;
    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 32)
    private String password;
    @JoinTable(name = "userroles", joinColumns =
    {
        @JoinColumn(name = "idUsers", referencedColumnName = "id", nullable = false)
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idRole", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany
    private Collection<Roles> rolesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsers")
    private Collection<Invoiceitems> invoiceitemsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsers")
    private Collection<Invoices> invoicesCollection;
    @OneToMany(mappedBy = "idUsers")
    private Collection<Reservations> reservationsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsers")
    private Collection<Habitations> habitationsCollection;

    public Users()
    {
    }

    public Users(Integer id)
    {
        this.id = id;
    }

    public Users(Integer id, String username, String password)
    {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
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

    @XmlTransient
    public Collection<Roles> getRolesCollection()
    {
        return rolesCollection;
    }

    public void setRolesCollection(Collection<Roles> rolesCollection)
    {
        this.rolesCollection = rolesCollection;
    }

    @XmlTransient
    public Collection<Invoiceitems> getInvoiceitemsCollection()
    {
        return invoiceitemsCollection;
    }

    public void setInvoiceitemsCollection(Collection<Invoiceitems> invoiceitemsCollection)
    {
        this.invoiceitemsCollection = invoiceitemsCollection;
    }

    @XmlTransient
    public Collection<Invoices> getInvoicesCollection()
    {
        return invoicesCollection;
    }

    public void setInvoicesCollection(Collection<Invoices> invoicesCollection)
    {
        this.invoicesCollection = invoicesCollection;
    }

    @XmlTransient
    public Collection<Reservations> getReservationsCollection()
    {
        return reservationsCollection;
    }

    public void setReservationsCollection(Collection<Reservations> reservationsCollection)
    {
        this.reservationsCollection = reservationsCollection;
    }

    @XmlTransient
    public Collection<Habitations> getHabitationsCollection()
    {
        return habitationsCollection;
    }

    public void setHabitationsCollection(Collection<Habitations> habitationsCollection)
    {
        this.habitationsCollection = habitationsCollection;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof Users))
        {
            return false;
        }
        Users other = (Users) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Users[ id=" + id + " ]";
    }
    
}
