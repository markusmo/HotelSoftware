/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import java.math.BigDecimal;

/**
 *
 * @author Johannes
 */
public class RoomCategoryPrice
{
    private Season dBSeason;
    protected RoomCategoryPricePK roomcategorypricesPK;
    private BigDecimal price;
    private BigDecimal priceMin;
  
    private Season seasons;
  
    private RoomCategory roomcategories;

    public RoomCategoryPrice()
    {
    }

    public Season getdBSeason()
    {
        return dBSeason;
    }

    public void setdBSeason(Season dBSeason)
    {
        this.dBSeason = dBSeason;
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

    public RoomCategory getRoomcategories()
    {
        return roomcategories;
    }

    public void setRoomcategories(RoomCategory roomcategories)
    {
        this.roomcategories = roomcategories;
    }

    public RoomCategoryPricePK getRoomcategorypricesPK()
    {
        return roomcategorypricesPK;
    }

    public void setRoomcategorypricesPK(RoomCategoryPricePK roomcategorypricesPK)
    {
        this.roomcategorypricesPK = roomcategorypricesPK;
    }

    public Season getSeasons()
    {
        return seasons;
    }

    public void setSeasons(Season seasons)
    {
        this.seasons = seasons;
    }
    
}
