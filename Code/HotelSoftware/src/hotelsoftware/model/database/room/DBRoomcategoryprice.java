/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.room;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
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
public class DBRoomCategoryPrice implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DBRoomCategoryPricePK roomcategorypricesPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    @Basic(optional = false)
    @Column(name = "priceMin", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceMin;
    @JoinColumn(name = "idSeason", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DBSeason seasons;
    @JoinColumn(name = "idRoomCategories", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DBRoomCategory roomcategories;

    public DBRoomCategoryPrice()
    {
    }

    public DBRoomCategoryPrice(DBRoomCategoryPricePK roomcategorypricesPK)
    {
        this.roomcategorypricesPK = roomcategorypricesPK;
    }

    public DBRoomCategoryPrice(DBRoomCategoryPricePK roomcategorypricesPK, BigDecimal price, BigDecimal priceMin)
    {
        this.roomcategorypricesPK = roomcategorypricesPK;
        this.price = price;
        this.priceMin = priceMin;
    }

    public DBRoomCategoryPrice(int idRoomCategories, int idSeason)
    {
        this.roomcategorypricesPK = new DBRoomCategoryPricePK(idRoomCategories, idSeason);
    }

    public DBRoomCategoryPricePK getRoomcategorypricesPK()
    {
        return roomcategorypricesPK;
    }

    public void setRoomcategorypricesPK(DBRoomCategoryPricePK roomcategorypricesPK)
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

    public DBSeason getSeasons()
    {
        return seasons;
    }

    public void setSeasons(DBSeason seasons)
    {
        this.seasons = seasons;
    }

    public DBRoomCategory getRoomcategories()
    {
        return roomcategories;
    }

    public void setRoomcategories(DBRoomCategory roomcategories)
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
        if(!(object instanceof DBRoomCategoryPrice))
        {
            return false;
        }
        DBRoomCategoryPrice other = (DBRoomCategoryPrice) object;
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
