/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.users;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "roles", catalog = "roomanizer", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "name"
    })
})
@XmlRootElement
//@NamedQueries(
//{
//    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r"),
//    @NamedQuery(name = "Roles.findById", query = "SELECT r FROM Roles r WHERE r.id = :id"),
//    @NamedQuery(name = "Roles.findByName", query = "SELECT r FROM Roles r WHERE r.name = :name")
//})
public class DBRole implements Serializable
{
    @JoinTable(name = "userroles", joinColumns =
    {
        @JoinColumn(name = "idRoles", referencedColumnName = "id", nullable = false)
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idUsers", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany
    private Collection<DBUser> dBUserCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    
    @JoinTable(name = "rolePermissions", joinColumns =
    {
        @JoinColumn(name = "idRoles", referencedColumnName = "id", nullable = false)
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "idPermissions", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany(cascade = CascadeType.ALL)
    private Collection<DBPermission> permissionsCollection;

    public DBRole()
    {
    }

    public DBRole(Integer id)
    {
        this.id = id;
    }

    public DBRole(Integer id, String name)
    {
        this.id = id;
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
    public Collection<DBPermission> getPermissions()
    {
        return permissionsCollection;
    }

    public void setPermissions(Collection<DBPermission> permissionsCollection)
    {
        this.permissionsCollection = permissionsCollection;
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
        if(!(object instanceof DBRole))
        {
            return false;
        }
        DBRole other = (DBRole) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Roles[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<DBUser> getDBUserCollection()
    {
        return dBUserCollection;
    }

    public void setDBUserCollection(Collection<DBUser> dBUserCollection)
    {
        this.dBUserCollection = dBUserCollection;
    }
    
}
