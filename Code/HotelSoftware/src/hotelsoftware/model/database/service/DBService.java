/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.service;

import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "services", catalog = "roomanizer", schema = "")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Services.findAll", query = "SELECT s FROM Services s"),
    @NamedQuery(name = "Services.findById", query = "SELECT s FROM Services s WHERE s.id = :id")
})
public class DBService implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @JoinColumn(name = "idServiceTypes", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DBServiceType idServiceTypes;

    public DBService()
    {
    }

    public DBService(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public DBServiceType getIdServiceTypes()
    {
        return idServiceTypes;
    }

    public void setIdServiceTypes(DBServiceType idServiceTypes)
    {
        this.idServiceTypes = idServiceTypes;
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
        if(!(object instanceof DBService))
        {
            return false;
        }
        DBService other = (DBService) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Services[ id=" + id + " ]";
    }
    
}