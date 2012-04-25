package hotelsoftware.model.domain.room;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.room.DBRoom;
import hotelsoftware.model.database.room.DBRoomCategory;
import hotelsoftware.model.domain.room.data.RoomCategoryData;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * Dies Klasse bildet die Zimmerkategorie ab, mit der das System arbeitet.
 *
 * @author Lins Christian (christian.lins87@gmail.com)
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

    private RoomCategory(String name, Set<RoomCategoryPrice> price,
            int bedAmount)
    {
        this.name = name;
        this.price = price;
        this.bedCount = bedAmount;
    }

    /**
     * Sucht eine Kategorie nach einem Namen
     *
     * @param name Der Name der gesuchten Kategorie
     * @return Die gesuchte Kategorie mit dem eingegebenen Namen
     */
    public static RoomCategory getCategoryByName(String name)
    {
        DBRoomCategory c = DBRoomCategory.getRoomCategoryByName(name);
        return (RoomCategory) DynamicMapper.map(c);
    }

    /**
     * such alle Kategorien heraus
     *
     * @return Alle Kategorieen, die verfuegbar sind.
     */
    public static Collection<RoomCategory> getAllCategorys()
    {
        Collection<DBRoomCategory> dbc = DBRoomCategory.getAllCategories();
        return (Collection<RoomCategory>) DynamicMapper.mapCollection(dbc);
    }

    /**
     * sucht nach freien Räumen in einer Periode in der Datenbank
     *
     * @param start Der Start der Periode
     * @param ende Das ende der Periode
     * @return Alle freien Zimmer in der angegebenen Periode
     */
    public Collection<Room> getFreeRooms(Date start, Date ende)
    {
        DBRoomCategory cat = (DBRoomCategory) DynamicMapper.map(this);
        Collection<DBRoom> dbc = cat.getFreeRooms(start, ende);
        return (Collection<Room>) DynamicMapper.mapCollection((Set<DBRoom>) dbc);
    }

    /**
     * Gibt alle Zimmer aus
     * @return 
     * Alle Zimmer, die das System kennt
     */
    public Collection<Room> getAllRooms()
    {
        return Room.getRoomsByCategory(this);
    }

    public Integer getBedCount()
    {
        return bedCount;
    }

    public BigDecimal getPriceFor(Date startDate) throws NoPriceDefinedException
    {        
        for (RoomCategoryPrice p : price)
        {
            Calendar dateCal = Calendar.getInstance();
            dateCal.setTime(startDate);
            int searchDay = dateCal.get(Calendar.DAY_OF_YEAR);
            
            Calendar startCal = Calendar.getInstance();
            startCal.setTime(p.getSeasons().getStart());
            int startDay = dateCal.get(Calendar.DAY_OF_YEAR);
            
            Calendar endCal = Calendar.getInstance();
            endCal.setTime(p.getSeasons().getStart());
            int endDay = dateCal.get(Calendar.DAY_OF_YEAR);

            if (startDay <= searchDay && endDay >= searchDay)
            {
                return p.getPrice();
            }
            //Geht über den Jahresübergang
            else if (startDay > endDay)
            {
                //Im neuen Jahr vor dem Ende oder im alten Jahr nach dem Anfang
                if (searchDay <= endDay || searchDay >= startDay)
                {
                    return p.getPrice();
                }
            }
        }
        
         throw new NoPriceDefinedException();
    }
    
    public String toString()
    {
        return name;
    }
}
