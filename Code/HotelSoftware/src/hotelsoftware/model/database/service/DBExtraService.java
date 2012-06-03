package hotelsoftware.model.database.service;

import hotelsoftware.model.database.reservation.DBReservedExtraServices;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Diese Klasse bildet einen Extraservice auf die Datenbank ab.
 * @author mohi
 */
@Entity
@Table(name = "extraservices", catalog = "`roomanizer`", schema = "")
@PrimaryKeyJoinColumn(name = "idServices", referencedColumnName = "idServices")
@XmlRootElement
public class DBExtraService extends DBService implements Serializable
{
    @JoinColumn(name = "idServices", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private DBService dBService;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dBExtraService")
    private Set<DBReservedExtraServices> reservedextraservicesSet;
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    
    @Basic(optional = false)
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    public DBExtraService()
    {
    }

    public DBExtraService(Integer id, String name, BigDecimal price)
    {
        this.name = name;
        this.price = price;
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
    public String toString()
    {
        return "hotelsoftware.database.model.Extraservices[ id=" + getIdServices() + " ]";
    }

    public DBService getDBService()
    {
        return dBService;
    }

    public void setDBService(DBService dBService)
    {
        this.dBService = dBService;
    }

    @XmlTransient
    public Set<DBReservedExtraServices> getReservedextraservicesSet()
    {
        return reservedextraservicesSet;
    }

    public void setReservedextraservicesSet(Set<DBReservedExtraServices> reservedextraservicesSet)
    {
        this.reservedextraservicesSet = reservedextraservicesSet;
    }
}
