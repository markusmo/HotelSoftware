/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.users;

import hotelsoftware.model.database.FailedToSaveToDatabaseException;
import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@NamedQueries(
{
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password")
})
public class DBUser implements Serializable
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
    private Collection<DBRole> rolesCollection;

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

    public DBUser(String username, String password, Collection<DBRole> rolesCollection)
    {
        this.username = username;
        this.password = password;
        this.rolesCollection = rolesCollection;
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
    public Collection<DBRole> getRoles()
    {
        return rolesCollection;
    }

    public void setRoles(Collection<DBRole> rolesCollection)
    {
        this.rolesCollection = rolesCollection;
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
     * establishes a connection to the database and retrieves all users
     * available in the database
     * @return
     * a list of usersc
     * @throws HibernateException 
     */
    public static List<DBUser> getUsers() throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(DBUser.class);
        List<DBUser> retList = criteria.list();
        session.close();

        return retList;
    }
    
     /**
     * establishes a connection to the database and creates a new user
     * in the database
     * @param username
     * the new user to be created
     * @throws HibernateException
     * @throws FailedToSaveToDatabaseException 
     */
    public static void saveUsers(DBUser users) throws FailedToSaveToDatabaseException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        try
        {
            session.save(users);
            ts.commit();
        } catch (HibernateException e)
        {
            ts.rollback();
            throw new FailedToSaveToDatabaseException();
        } finally
        {
            session.close();
        }
    }
    
    /**
     * Communicates with the database and returns a user with the specified name
     * @param username
     * @return Users-Object
     * @throws HibernateException 
     */
    public static DBUser login(String username, String password) throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        DBUser retUser = (DBUser) session.createCriteria(DBUser.class).add(Restrictions.eq(
                "username", username)).add(Restrictions.eq("password", password)).uniqueResult();
        session.close();
        return retUser;
    }
    
     

}
