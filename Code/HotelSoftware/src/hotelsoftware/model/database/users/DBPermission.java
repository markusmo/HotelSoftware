package hotelsoftware.model.database.users;

import hotelsoftware.model.database.FaildToDeleteFromDatabaseException;
import hotelsoftware.model.database.FailedToSaveToDatabaseException;
import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
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
 * Diese Klasse bildet die Befugnisse fuer das System auf die Datenbank ab.
 * @author mohi
 */
@Entity
@Table(name = "permissions", catalog = "`roomanizer-dev`", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "name"
    })
})
@XmlRootElement
//@NamedQueries(
//{
//    @NamedQuery(name = "Permissions.findAll", query = "SELECT p FROM Permissions p"),
//    @NamedQuery(name = "Permissions.findById", query = "SELECT p FROM Permissions p WHERE p.id = :id"),
//    @NamedQuery(name = "Permissions.findByName", query = "SELECT p FROM Permissions p WHERE p.name = :name")
//})
public class DBPermission implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    public DBPermission()
    {
    }

    public DBPermission(Integer id)
    {
        this.id = id;
    }

    public DBPermission(Integer id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public DBPermission(String name) {
        this.name = name;
    }
    
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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
        if(!(object instanceof DBPermission))
        {
            return false;
        }
        DBPermission other = (DBPermission) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Permissions[ id=" + id + " ]";
    }
    
    /**
     * Gibt alle Befugnisse aus
     * @return
     * Ein Set mit allen Befugnissen, die verfuegbar sind
     * @throws HibernateException 
     */
    public static Set<DBPermission> getPermissions() throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(DBPermission.class);
        List<DBPermission> retList = criteria.list();
        ;

        return new LinkedHashSet<DBPermission>(retList);
    }
    
    /**
     * Sucht eine spezielle Befugnis in der Datenbank
     * @param permission
     * Die Befugnis, nach der gesucht wird
     * @return
     * Die Befugnis, nach der gesucht wird
     * @throws HibernateException 
     * Wirft einen Fehler, wenn etwas bei der Transaktion fehleschlaegt
     */
    public static DBPermission getPermissionByName(String permission) throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(DBPermission.class);
        List<DBPermission> retList = criteria.list();
        ;

        for (DBPermission permissions : retList)
        {
            if (permissions.getName().equals(permission))
            {
                return permissions;
            }
        }
        return null;
    }   
}
