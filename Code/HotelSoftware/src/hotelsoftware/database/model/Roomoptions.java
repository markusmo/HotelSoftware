/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.database.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "roomoptions", catalog = "roomanizer", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "name"
    })
})
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Roomoptions.findAll", query = "SELECT r FROM Roomoptions r"),
    @NamedQuery(name = "Roomoptions.findById", query = "SELECT r FROM Roomoptions r WHERE r.id = :id"),
    @NamedQuery(name = "Roomoptions.findByName", query = "SELECT r FROM Roomoptions r WHERE r.name = :name")
})
public class Roomoptions implements Serializable
{
    private static final long serialVersionUID = 1L;

    public static void safeNewRoomOption(String name)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void getRoomoptions()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    public Roomoptions()
    {
    }

    public Roomoptions(Integer id)
    {
        this.id = id;
    }

    public Roomoptions(Integer id, String name)
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
        if(!(object instanceof Roomoptions))
        {
            return false;
        }
        Roomoptions other = (Roomoptions) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Roomoptions[ id=" + id + " ]";
    }
    
}
