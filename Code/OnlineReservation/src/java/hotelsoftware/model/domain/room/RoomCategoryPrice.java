package hotelsoftware.model.domain.room;

import java.math.BigDecimal;

/**
 * Mit dieser Klasse verwaltet das System die Zimmerkategoriepreise
 * @author Johannes
 */
public class RoomCategoryPrice implements IRoomCategoryPrice
{
    protected IRoomCategoryPricePK roomcategorypricesPK;
    private BigDecimal price;
    private BigDecimal priceMin;  
    private ISeason seasons;  
    private IRoomCategory roomcategories;

    public RoomCategoryPrice()
    {
    }

   
    @Override
    public BigDecimal getPrice()
    {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    @Override
    public BigDecimal getPriceMin()
    {
        return priceMin;
    }

    @Override
    public void setPriceMin(BigDecimal priceMin)
    {
        this.priceMin = priceMin;
    }

    @Override
    public IRoomCategory getRoomcategories()
    {
        return roomcategories;
    }

    @Override
    public void setRoomcategories(IRoomCategory roomcategories)
    {
        this.roomcategories = roomcategories;
    }

    @Override
    public IRoomCategoryPricePK getRoomcategorypricesPK()
    {
        return roomcategorypricesPK;
    }

    @Override
    public void setRoomcategorypricesPK(IRoomCategoryPricePK roomcategorypricesPK)
    {
        this.roomcategorypricesPK = roomcategorypricesPK;
    }

    @Override
    public ISeason getSeasons()
    {
        return seasons;
    }

    @Override
    public void setSeasons(ISeason seasons)
    {
        this.seasons = seasons;
    }
    
}
