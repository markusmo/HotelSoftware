/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.adapter;

import at.fhv.roomanizer.domain.room.IPrice;
import at.fhv.roomanizer.domain.room.ISeason;
import at.fhv.roomanizer.domain.room.Price;
import at.fhv.roomanizer.domain.room.Season;
import hotelsoftware.model.domain.room.IRoomCategoryPrice;
import hotelsoftware.model.domain.room.RoomCategoryPrice;
import java.math.BigDecimal;

/**
 *
 * @author Johannes
 */
public class PriceAdapter extends Price implements Adapter<IRoomCategoryPrice>
{
    private RoomCategoryPrice price;
    
    public PriceAdapter()
    {
    }

    @Override
    public ISeason getISeason()
    {
        return new SeasonAdapter(price.getSeasons());
    }

    @Override
    public double getPrice()
    {
        return price.getPrice().doubleValue();
    }

    @Override
    public double getPriceMin()
    {
        return price.getPriceMin().doubleValue();
    }

    @Override
    public Season getSeason()
    {
        return super.getSeason();
    }

    @Override
    public void setPrice(double price)
    {
        this.price.setPrice(BigDecimal.valueOf(price));
    }

    @Override
    public void setPriceMin(double priceMin)
    {
        this.price.setPriceMin(BigDecimal.valueOf(priceMin));
    }

    @Override
    public void setSeason(Season season)
    {
        price.setSeasons((new SeasonAdapter(season)).getOurType());
    }

    @Override
    public IRoomCategoryPrice getOurType()
    {
        return price;
    }

    @Override
    public void setOurType(IRoomCategoryPrice type)
    {
        price = (RoomCategoryPrice) type;
    }
}
