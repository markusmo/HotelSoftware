/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.users;

import hotelsoftware.model.database.FailedToSaveToDatabaseException;
import hotelsoftware.model.database.invoice.DBInvoice;
import hotelsoftware.model.database.reservation.DBReservation;
import hotelsoftware.model.database.service.DBHabitation;
import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *Diese Klasse wird benötigt für die Datenbank der Tabelle User
 * @author mohi
 */
@Entity
@Table(name = "users", catalog = "`roomanizer-dev`", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "username"
    })
})
@XmlRootElement
public class DBUser implements Serializable
{
    @ManyToMany(mappedBy = "users", fetch= FetchType.EAGER)
    private Set<DBRole> roles;
    
    @OneToMany(mappedBy = "users")
    private Set<DBHabitation> habitations;
    
    @OneToMany(mappedBy = "user")
    private Set<DBReservation> reservations;
    
    @Basic(optional = false)
    @Column(name = "active")
    private Boolean active;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<DBInvoice> invoices;
    
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

    public DBUser()
    {
    }

    public DBUser(Integer id)
    {
        this.id = id;
    }

    public DBUser(Integer id, String username, String password)
    {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public DBUser(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public DBUser(String username, String password, Set<DBRole> roleCollection)
    {
        this.username = username;
        this.password = password;
        this.roles = roleCollection;
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
        final DBUser other = (DBUser) obj;
        if (this.roles != other.roles && (this.roles == null || !this.roles.equals(other.roles)))
        {
            return false;
        }
        if (this.habitations != other.habitations && (this.habitations == null || !this.habitations.equals(other.habitations)))
        {
            return false;
        }
        if (this.reservations != other.reservations && (this.reservations == null || !this.reservations.equals(other.reservations)))
        {
            return false;
        }
        if (this.active != other.active && (this.active == null || !this.active.equals(other.active)))
        {
            return false;
        }
        if (this.invoices != other.invoices && (this.invoices == null || !this.invoices.equals(other.invoices)))
        {
            return false;
        }
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
        {
            return false;
        }
        if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username))
        {
            return false;
        }
        if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 53 * hash + (this.roles != null ? this.roles.hashCode() : 0);
        hash = 53 * hash + (this.habitations != null ? this.habitations.hashCode() : 0);
        hash = 53 * hash + (this.reservations != null ? this.reservations.hashCode() : 0);
        hash = 53 * hash + (this.active != null ? this.active.hashCode() : 0);
        hash = 53 * hash + (this.invoices != null ? this.invoices.hashCode() : 0);
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 53 * hash + (this.username != null ? this.username.hashCode() : 0);
        hash = 53 * hash + (this.password != null ? this.password.hashCode() : 0);
        return hash;
    }


    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Users[ id=" + id + " ]";
    }
    
//    /**
//     * Ueberprueft, ob ein User mit dem Passwort und Usernamen in der
//     * Datenbank ist und gibt diesen zurueck
//     * @param username
//     * Der Benutzername, des gesuchten Benutzers
//     * @param password
//     * Das Passwort, des gesuchten Benutzers
//     * @return
//     * Den  Benutzer, der eingeloggt werden sollte
//     * @throws HibernateException 
//     * Wirft einen Fehler, wenn etwas mit der Transaktion fehllschlaegt.
//     */
//    public static DBUser login(String username, String password) throws HibernateException
//    {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction ts = session.beginTransaction();
//        ts.begin();
//        DBUser retUser = (DBUser) session.createCriteria(DBUser.class).add(Restrictions.and(Restrictions.eq("username", username), 
//                Restrictions.eq("password", password))).uniqueResult();
//        
//        return retUser;
//    }

    public Boolean getActive()
    {
        return active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }

    @XmlTransient
    public Collection<DBInvoice> getInvoices()
    {
        return invoices;
    }

    public void setInvoices(Set<DBInvoice> invoices)
    {
        this.invoices = invoices;
    }

    @XmlTransient
    public Collection<DBReservation> getReservations()
    {
        return reservations;
    }

    public void setReservations(Set<DBReservation> reservations)
    {
        this.reservations = reservations;
    }

    @XmlTransient
    public Collection<DBHabitation> getHabitations()
    {
        return habitations;
    }

    public void setHabitations(Set<DBHabitation> habitations)
    {
        this.habitations = habitations;
    }

    @XmlTransient
    public Collection<DBRole> getRoles()
    {
        return roles;
    }

    public void setRoles(Set<DBRole> roles)
    {
        this.roles = roles;
    }
    
     

}
