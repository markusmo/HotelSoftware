/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.database.model;

import hotelsoftware.database.Exceptions.FaildToDeleteFromDatabaseException;
import hotelsoftware.database.Exceptions.FailedToSaveToDatabaseException;
import hotelsoftware.database.HibernateUtil;
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
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "permissions", catalog = "roomanizer", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "name"
    })
})
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Permissions.findAll", query = "SELECT p FROM Permissions p"),
    @NamedQuery(name = "Permissions.findById", query = "SELECT p FROM Permissions p WHERE p.id = :id"),
    @NamedQuery(name = "Permissions.findByName", query = "SELECT p FROM Permissions p WHERE p.name = :name")
})
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
    @JoinTable(name = "rolepermissions", joinColumns =
    {
        @JoinColumn(name = "idPermissions", referencedColumnName = "id", nullable = false)
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idRoles", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany
    private Collection<DBRole> rolesCollection;

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

    @XmlTransient
    public Collection<DBRole> getRolesCollection()
    {
        return rolesCollection;
    }

    public void setRolesCollection(Collection<DBRole> rolesCollection)
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
     * establishes a connection to the database and retrieves all permissions
     * available in the database
     * @return
     * a list of permissions
     * @throws HibernateException 
     */
    public static List<DBPermission> getPermissions() throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(DBPermission.class);
        List<DBPermission> retList = criteria.list();
        session.close();

        return retList;
    }
    
    /**
     * establishes a connection to the database and retrieves a single permission
     * by name
     * @param permission
     * the permission to be fetched
     * @return
     * a model class of permissions
     * @throws HibernateException 
     */
    public static DBPermission getPermissionByName(String permission) throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(DBPermission.class);
        List<DBPermission> retList = criteria.list();
        session.close();

        for (DBPermission permissions : retList)
        {
            if (permissions.getName().equals(permission))
            {
                return permissions;
            }
        }
        return null;
    }
    

    
     /**
     * establishes a connection to the database and creates a new permission
     * in the database
     * @param name
     * the new permission to be created
     * @throws HibernateException
     * @throws FailedToSaveToDatabaseException 
     */
    public static void savePermission(String name) throws FailedToSaveToDatabaseException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        try
        {
            session.save(new DBPermission(name));
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
     * deletes a permission from the database
     * @param name
     * the name of the permission to delete
     * @throws FaildToDeleteFromDatabaseException 
     */
    public static void deletePermission(String name) throws FaildToDeleteFromDatabaseException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        try
        {
            DBPermission permission = (DBPermission) session.createCriteria(
                    DBPermission.class).add(Restrictions.like("name", name)).uniqueResult();
            session.delete(permission);
        } catch (HibernateException ex)
        {
            ts.rollback();
            throw new FaildToDeleteFromDatabaseException();
        }
    }
    
}
