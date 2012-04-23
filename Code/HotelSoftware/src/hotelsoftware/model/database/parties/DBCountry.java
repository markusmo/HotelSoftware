/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.parties;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *Dies ist die Klasse für die Tabelle "countries". Hier werden alle Laender gespeichert.
 * @author mohi
 */
@Entity
@Table(name = "countries", catalog = "roomanizer", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "name"
    }),
    @UniqueConstraint(columnNames =
    {
        "nameShort"
    })
})
@XmlRootElement
//@NamedQueries(
//{
//    @NamedQuery(name = "Countries.findAll", query = "SELECT c FROM Countries c"),
//    @NamedQuery(name = "Countries.findById", query = "SELECT c FROM Countries c WHERE c.id = :id"),
//    @NamedQuery(name = "Countries.findByName", query = "SELECT c FROM Countries c WHERE c.name = :name"),
//    @NamedQuery(name = "Countries.findByNameShort", query = "SELECT c FROM Countries c WHERE c.nameShort = :nameShort")
//})
public class DBCountry implements Serializable
{
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCountry")
    private Collection<DBAddress> dBAddressCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic(optional = false)
    @Column(name = "nameShort", nullable = false, length = 2)
    private String nameShort;

    public DBCountry()
    {
    }

    public DBCountry(Integer id)
    {
        this.id = id;
    }

    public DBCountry(Integer id, String name, String nameShort)
    {
        this.id = id;
        this.name = name;
        this.nameShort = nameShort;
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

    public String getNameShort()
    {
        return nameShort;
    }

    public void setNameShort(String nameShort)
    {
        this.nameShort = nameShort;
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
        if(!(object instanceof DBCountry))
        {
            return false;
        }
        DBCountry other = (DBCountry) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Countries[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<DBAddress> getDBAddressCollection()
    {
        return dBAddressCollection;
    }

    public void setDBAddressCollection(Collection<DBAddress> dBAddressCollection)
    {
        this.dBAddressCollection = dBAddressCollection;
    }
    
}
