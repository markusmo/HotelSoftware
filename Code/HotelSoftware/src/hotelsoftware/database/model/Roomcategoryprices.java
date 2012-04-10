/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.database.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "roomcategoryprices", catalog = "roomanizer", schema = "")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Roomcategoryprices.findAll", query = "SELECT r FROM Roomcategoryprices r"),
    @NamedQuery(name = "Roomcategoryprices.findByIdRoomCategories", query = "SELECT r FROM Roomcategoryprices r WHERE r.roomcategorypricesPK.idRoomCategories = :idRoomCategories"),
    @NamedQuery(name = "Roomcategoryprices.findByIdSeason", query = "SELECT r FROM Roomcategoryprices r WHERE r.roomcategorypricesPK.idSeason = :idSeason"),
    @NamedQuery(name = "Roomcategoryprices.findByPrice", query = "SELECT r FROM Roomcategoryprices r WHERE r.price = :price"),
    @NamedQuery(name = "Roomcategoryprices.findByPriceMin", query = "SELECT r FROM Roomcategoryprices r WHERE r.priceMin = :priceMin")
})
public class Roomcategoryprices implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RoomcategorypricesPK roomcategorypricesPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    @Basic(optional = false)
    @Column(name = "priceMin", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceMin;
    @JoinColumn(name = "idSeason", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Seasons seasons;
    @JoinColumn(name = "idRoomCategories", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Roomcategories roomcategories;

    public Roomcategoryprices()
    {
    }

    public Roomcategoryprices(RoomcategorypricesPK roomcategorypricesPK)
    {
        this.roomcategorypricesPK = roomcategorypricesPK;
    }

    public Roomcategoryprices(RoomcategorypricesPK roomcategorypricesPK, BigDecimal price, BigDecimal priceMin)
    {
        this.roomcategorypricesPK = roomcategorypricesPK;
        this.price = price;
        this.priceMin = priceMin;
    }

    public Roomcategoryprices(int idRoomCategories, int idSeason)
    {
        this.roomcategorypricesPK = new RoomcategorypricesPK(idRoomCategories, idSeason);
    }

    public RoomcategorypricesPK getRoomcategorypricesPK()
    {
        return roomcategorypricesPK;
    }

    public void setRoomcategorypricesPK(RoomcategorypricesPK roomcategorypricesPK)
    {
        this.roomcategorypricesPK = roomcategorypricesPK;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPriceMin()
    {
        return priceMin;
    }

    public void setPriceMin(BigDecimal priceMin)
    {
        this.priceMin = priceMin;
    }

    public Seasons getSeasons()
    {
        return seasons;
    }

    public void setSeasons(Seasons seasons)
    {
        this.seasons = seasons;
    }

    public Roomcategories getRoomcategories()
    {
        return roomcategories;
    }

    public void setRoomcategories(Roomcategories roomcategories)
    {
        this.roomcategories = roomcategories;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (roomcategorypricesPK != null ? roomcategorypricesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof Roomcategoryprices))
        {
            return false;
        }
        Roomcategoryprices other = (Roomcategoryprices) object;
        if((this.roomcategorypricesPK == null && other.roomcategorypricesPK != null) || (this.roomcategorypricesPK != null && !this.roomcategorypricesPK.equals(other.roomcategorypricesPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Roomcategoryprices[ roomcategorypricesPK=" + roomcategorypricesPK + " ]";
    }
    
}
