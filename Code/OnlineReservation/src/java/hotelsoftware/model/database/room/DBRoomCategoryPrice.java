package hotelsoftware.model.database.room;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Diese Klasse bildet die Preise pro Kategorie auf der Datenbank ab.
 * @author mohi
 */
@Entity
@Table(name = "roomcategoryprices", catalog = "`roomanizer-dev`", schema = "")
@XmlRootElement
public class DBRoomCategoryPrice implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected DBRoomCategoryPricePK roomcategorypricesPK;

    @Basic(optional = false)
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    @Basic(optional = false)
    @Column(name = "priceMin", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceMin;
    
    @JoinColumn(name = "idSeasons", referencedColumnName = "id", updatable=false, insertable=false)
    @ManyToOne(optional = false)
    private DBSeason seasons;
    
    @JoinColumn(name = "idRoomCategories", referencedColumnName = "id", updatable=false, insertable=false)
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
    public String toString()
    {
        return "hotelsoftware.database.model.Roomcategoryprices[ roomcategorypricesPK=" + roomcategorypricesPK + " ]";
    }
    
}
