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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dBExtraService")
    private Set<DBReservedExtraServices> reservedextraservicesSet;
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    
    @Basic(optional = false)
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    @Basic(optional = false)
    @Column(name = "reservable", nullable = false)
    private Boolean reservable;

    public DBExtraService()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Boolean getReservable()
    {
        return reservable;
    }

    public void setReservable(Boolean reservable)
    {
        this.reservable = reservable;
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
