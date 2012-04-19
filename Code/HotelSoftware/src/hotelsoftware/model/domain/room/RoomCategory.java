/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import java.math.BigDecimal;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class RoomCategory implements CategoryData
{
    private String name;
    private BigDecimal price;
    private BigDecimal minPrice;
    private int bedAmount;

    @Override
    public int getBedAmount()
    {
        return bedAmount;
    }

    public void setBedAmount(int bedAmount)
    {
        this.bedAmount = bedAmount;
    }

    @Override
    public BigDecimal getMinPrice()
    {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice)
    {
        this.minPrice = minPrice;
    }

    @Override
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }
    
    
}
