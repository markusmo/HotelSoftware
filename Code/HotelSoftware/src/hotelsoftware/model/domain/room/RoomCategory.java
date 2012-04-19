/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.parties.DBCompanyType;
import hotelsoftware.model.database.room.DBRoom;
import hotelsoftware.model.database.room.DBRoomCategory;
import hotelsoftware.model.domain.parties.CompanyType;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

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
    private int id;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public RoomCategory()
    {
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

    public RoomCategory create(String name, BigDecimal price, BigDecimal minprice,
            int bedAmound)
    {
        return new RoomCategory(name, price, minprice, bedAmount);
    }

    private RoomCategory(String name, BigDecimal price, BigDecimal minprice,
            int bedAmount)
    {
        this.name = name;
        this.price = price;
        this.minPrice = minprice;
        this.bedAmount = bedAmount;
    }

    public static RoomCategory getCategoryByName(String name)
    {
        DBRoomCategory c = DBRoomCategory.getRoomCategoryByName(name);
        return (RoomCategory) DynamicMapper.map(c);
    }

    public static Collection<RoomCategory> getAllCategorys()
    {
        Collection<DBRoomCategory> dbc = DBRoomCategory.getAllCategorys();
        return (Collection<RoomCategory>) DynamicMapper.map(dbc);
    }

    public Collection<RoomCategory> getFreeRooms(Date start, Date ende)
    {
        Collection<RoomCategory> dbc = DBRoomCategory.getFreeRooms(this, start, ende);
        return (Collection<RoomCategory>) DynamicMapper.map(dbc);
    }

    public Collection<Room> getAllRooms()
    {
        return Room.getRoomsByCategory(this);
    }

    public int getBedAmount()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
