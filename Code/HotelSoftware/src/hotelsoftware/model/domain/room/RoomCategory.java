/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import hotelsoftware.model.domain.room.data.RoomCategoryData;
import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.parties.DBCompanyType;
import hotelsoftware.model.database.room.DBRoom;
import hotelsoftware.model.database.room.DBRoomCategory;
import hotelsoftware.model.domain.parties.CompanyType;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 * @author Hubert
 */
public class RoomCategory implements RoomCategoryData
{
    private String name;
    private Set<RoomCategoryPrice> price;
    private Integer bedCount;
    private Integer id;

    public Integer getId()
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

    public void setBedCount(int bedCount)
    {
        this.bedCount = bedCount;
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
    public Set<RoomCategoryPrice> getPrice()
    {
        return price;
    }

    public void setPrice(Set<RoomCategoryPrice> price)
    {
        this.price = price;
    }

    private RoomCategory(String name, Set<RoomCategoryPrice> price, int bedAmount)
    {
        this.name = name;
        this.price = price;
        this.bedCount = bedAmount;
    }

    public static RoomCategory getCategoryByName(String name)
    {
        DBRoomCategory c = DBRoomCategory.getRoomCategoryByName(name);
        return (RoomCategory) DynamicMapper.map(c);
    }

    /**
     * such alle Kategorien heraus
     *
     * @return
     */
    public static Set<RoomCategory> getAllCategorys()
    {
        Set<DBRoomCategory> dbc = DBRoomCategory.getAllCategories();
        return (Set<RoomCategory>) DynamicMapper.mapCollection(dbc);
    }

    /**
     * sucht nach freien Räumen in der Datenbank
     *
     * @param start
     * @param ende
     * @return
     */
    public Set<Room> getFreeRooms(Date start, Date ende)
    {
        DBRoomCategory cat = (DBRoomCategory) DynamicMapper.map(this);
        Set<DBRoom> dbc = cat.getFreeRooms(start, ende);
        return (Set<Room>) DynamicMapper.mapCollection(dbc);
    }

    public Set<Room> getAllRooms()
    {
        return Room.getRoomsByCategory(this);
    }

    public Integer getBedCount()
    {
        return bedCount;
    }
}
