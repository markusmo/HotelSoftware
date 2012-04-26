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
public class DBUser implements Serializable
{
    @ManyToMany(mappedBy = "users", fetch= FetchType.EAGER)
    private Set<DBRole> roles;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Set<DBHabitation> habitations;
    
    @OneToMany(mappedBy = "idUsers")
    private Set<DBReservation> reservations;
    
    @Basic(optional = false)
    @Column(name = "active")
    private boolean active;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsers")
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
        if(!(object instanceof DBUser))
        {
            return false;
        }
        DBUser other = (DBUser) object;
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
    
    /**
     * Gibt alle Benutzer in dem System aus
     * @return
     * eine Liste aller User, die verfuegbar sind
     * @throws HibernateException 
     */
    public static List<DBUser> getUsers() throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(DBUser.class);
        List<DBUser> retList = criteria.list();
        ;

        return retList;
    }
    
    /**
     * Ueberprueft, ob ein User mit dem Passwort(MD5-Hash) und Usernamen in der
     * Datenbank ist und gibt diesen zurueck
     * @param username
     * Der Benutzername, des gesuchten Benutzers
     * @param password
     * Das MD5-gehashte Passwort, des gesuchten Benutzers
     * @return
     * Den  Benutzer, der eingeloggt werden sollte
     * @throws HibernateException 
     * Wirft einen Fehler, wenn etwas mit der Transaktion fehllschlaegt.
     */
    public static DBUser login(String username, String password) throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        DBUser retUser = (DBUser) session.createCriteria(DBUser.class).add(Restrictions
                .and(Restrictions.eq("username", username),Restrictions.eq("password", password)))
                .uniqueResult();
        ;
        return retUser;
    }

    public boolean getActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    @XmlTransient
    public Set<DBInvoice> getInvoices()
    {
        return invoices;
    }

    public void setInvoices(Set<DBInvoice> invoices)
    {
        this.invoices = invoices;
    }

    @XmlTransient
    public Set<DBReservation> getReservations()
    {
        return reservations;
    }

    public void setReservations(Set<DBReservation> reservations)
    {
        this.reservations = reservations;
    }

    @XmlTransient
    public Set<DBHabitation> getHabitations()
    {
        return habitations;
    }

    public void setHabitations(Set<DBHabitation> habitations)
    {
        this.habitations = habitations;
    }

    @XmlTransient
    public Set<DBRole> getRoles()
    {
        return roles;
    }

    public void setRoles(Set<DBRole> roles)
    {
        this.roles = roles;
    }
    
     

}
