/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.service;

import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;
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
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Diese Klasse bildet die verschiedenen Servicetypen (Essen, Getraenke, Aufenthalte, ...) mit Steuern auf die Datenbank ab.
 *
 * @author mohi
 */
@Entity
@Table(name = "servicetypes", catalog = "`roomanizer-dev`", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "name"
    })
})
@XmlRootElement
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
    @Basic(optional = false)
    @Column(name = "taxRate", nullable = false, precision = 5, scale = 2)
    private BigDecimal taxRate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceType")
    private Set<DBService> dBServiceCollection;

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
        if (!(object instanceof DBServiceType))
        {
            return false;
        }
        DBServiceType other = (DBServiceType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
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

    /**
     * Gibt alle Servicetypen aus
     *
     * @return
     * Alle Servicetypen, die verfuegbar sind
     */
    public static Set<DBServiceType> getAllServiceTypes()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        Set<DBServiceType> serviceType = (Set<DBServiceType>) session.createCriteria(DBServiceType.class).list();

        return serviceType;
    }

    public static DBServiceType getTypeByName(String name)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        SQLQuery query = session.createSQLQuery("SELECT * FROM servicetypes WHERE name = :name");
        query.setString("name", name);
        query.addEntity(DBServiceType.class);
        
        DBServiceType serviceType = (DBServiceType) query.uniqueResult();
        
        ts.commit();
        return serviceType;
    }

    @XmlTransient
    public Set<DBService> getDBServiceCollection()
    {
        return dBServiceCollection;
    }

    public void setDBServiceCollection(Set<DBService> dBServiceCollection)
    {
        this.dBServiceCollection = dBServiceCollection;
    }
}
