/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.database.model;

import hotelsoftware.database.HibernateUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "extraservices", catalog = "roomanizer", schema = "")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Extraservices.findAll", query = "SELECT e FROM Extraservices e"),
    @NamedQuery(name = "Extraservices.findById", query = "SELECT e FROM Extraservices e WHERE e.id = :id"),
    @NamedQuery(name = "Extraservices.findByName", query = "SELECT e FROM Extraservices e WHERE e.name = :name"),
    @NamedQuery(name = "Extraservices.findByPrice", query = "SELECT e FROM Extraservices e WHERE e.price = :price")
})
public class Extraservices implements Serializable
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
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    @JoinColumn(name = "idServices", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Services idServices;

    public Extraservices()
    {
    }

    public Extraservices(Integer id)
    {
        this.id = id;
    }

    public Extraservices(Integer id, String name, BigDecimal price)
    {
        this.id = id;
        this.name = name;
        this.price = price;
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

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public Services getIdServices()
    {
        return idServices;
    }

    public void setIdServices(Services idServices)
    {
        this.idServices = idServices;
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
        if(!(object instanceof Extraservices))
        {
            return false;
        }
        Extraservices other = (Extraservices) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }
    
    public static List<Extraservices> getExtraServices() throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(Extraservices.class);
        List<Extraservices> retList = criteria.list();
        session.close();

        return retList;
    }
    
    public static Extraservices getExtraServicesByName(String name) throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(Extraservices.class);
        Extraservices extraService = (Extraservices) session.createCriteria(Extraservices.class).add(Restrictions.eq(
                "name", name)).uniqueResult();
        session.close();

        return extraService;
    }
    
    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Extraservices[ id=" + id + " ]";
    }
    
}
