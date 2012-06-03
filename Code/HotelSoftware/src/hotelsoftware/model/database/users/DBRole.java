package hotelsoftware.model.database.users;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Bildet eine Rolle (z.B. Administrator) im System auf die Datenbank ab.
 * @author mohi
 */
@Entity
@Table(name = "roles", catalog = "`roomanizer`", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "name"
    })
})
@XmlRootElement
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
    private Set<DBUser> users;
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
    @ManyToMany(fetch= FetchType.EAGER)
    private Set<DBPermission> permissions;

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
        return permissions;
    }

    public void setPermissions(Set<DBPermission> permissionsCollection)
    {
        this.permissions = permissionsCollection;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Roles[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<DBUser> getUsers()
    {
        return users;
    }

    public void setUsers(Set<DBUser> userCollection)
    {
        this.users = userCollection;
    }
    
}
