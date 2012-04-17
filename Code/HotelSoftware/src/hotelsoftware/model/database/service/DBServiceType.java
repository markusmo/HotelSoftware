/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.service;

import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "servicetypes", catalog = "roomanizer", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "name"
    })
})
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Servicetypes.findAll", query = "SELECT s FROM Servicetypes s"),
    @NamedQuery(name = "Servicetypes.findById", query = "SELECT s FROM Servicetypes s WHERE s.id = :id"),
    @NamedQuery(name = "Servicetypes.findByName", query = "SELECT s FROM Servicetypes s WHERE s.name = :name"),
    @NamedQuery(name = "Servicetypes.findByTaxRate", query = "SELECT s FROM Servicetypes s WHERE s.taxRate = :taxRate")
})
public class DBServiceType implements Serializable
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "taxRate", nullable = false, precision = 5, scale = 2)
    private BigDecimal taxRate;

    public DBServiceType()
    {
    }

    public DBServiceType(Integer id)
    {
        this.id = id;
    }

    public DBServiceType(Integer id, String name, BigDecimal taxRate)
    {
        this.id = id;
        this.name = name;
        this.taxRate = taxRate;
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

    public BigDecimal getTaxRate()
    {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate)
    {
        this.taxRate = taxRate;
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
        if(!(object instanceof DBServiceType))
        {
            return false;
        }
        DBServiceType other = (DBServiceType) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Servicetypes[ id=" + id + " ]";
    }
    
    public static Collection<DBServiceType> getAllServiceTypes(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Collection<DBServiceType> serviceType = (Collection<DBServiceType>)session.createCriteria(DBServiceType.class).list();
        session.close();
        return serviceType;
    }
}
