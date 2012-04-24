/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.service;

import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
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
@Table(name = "extraservices", catalog = "roomanizer", schema = "")
//@PrimaryKeyJoinColumn(name = "idServices", referencedColumnName = "id")
@XmlRootElement
public class DBExtraService implements Serializable
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

    public DBExtraService()
    {
    }

    public DBExtraService(Integer id)
    {
        this.id = id;
    }

    public DBExtraService(Integer id, String name, BigDecimal price)
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
        if(!(object instanceof DBExtraService))
        {
            return false;
        }
        DBExtraService other = (DBExtraService) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }
    
    public static Set<DBExtraService> getExtraServices() throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Criteria criteria = session.createCriteria(DBExtraService.class);
        List<DBExtraService> retList = criteria.list();
        session.close();

        return new LinkedHashSet<DBExtraService>(retList);
    }
    
    public static DBExtraService getExtraServiceByName(String name) throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        DBExtraService extraService = (DBExtraService)session.createCriteria(DBExtraService.class).add(Restrictions.eq("name", name)).uniqueResult();
        session.close();
        return extraService;
    }
    
    public static Set<DBExtraService> getAllExtraServices()throws HibernateException
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        List<DBExtraService> extraServices = (List<DBExtraService>)session.createCriteria(DBExtraService.class).list();
        session.close();
        return new LinkedHashSet<DBExtraService>(extraServices);
    }
    
    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Extraservices[ id=" + id + " ]";
    }
    
}
