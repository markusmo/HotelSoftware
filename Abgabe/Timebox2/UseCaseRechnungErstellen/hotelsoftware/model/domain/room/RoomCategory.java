package hotelsoftware.model.domain.room;

import hotelsoftware.support.NoPriceDefinedException;
import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.room.DBRoom;
import hotelsoftware.model.database.room.DBRoomCategory;
import hotelsoftware.controller.data.room.RoomCategoryData;
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
public class RoomCategory implements IRoomCategory
{
    private String name;
    private Collection<IRoomCategoryPrice> price;
    private Integer bedCount;
    private Integer id;

    @Override
    public Integer getId()
    {
        return id;
    }

    @Override
    public void setId(int id)
    {
        this.id = id;
    }

    public RoomCategory()
    {
    }

    @Override
    public void setBedCount(int bedCount)
    {
        this.bedCount = bedCount;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public Collection<IRoomCategoryPrice> getPrice()
    {
        return price;
    }

    @Override
    public void setPrice(Collection<IRoomCategoryPrice> price)
    {
        this.price = price;
    }

    private RoomCategory(String name, Set<IRoomCategoryPrice> price,
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
    public static IRoomCategory getCategoryByName(String name)
    {
        DBRoomCategory c = DBRoomCategory.getRoomCategoryByName(name);
        return (RoomCategory) DynamicMapper.map(c);
    }

    /**
     * such alle Kategorien heraus die vorhanden sin
     *
     * @return Alle Kategorieen, die verfuegbar sind.
     */
    public static Collection<IRoomCategory> getAllCategorys()
    {
        Collection<DBRoomCategory> dbc = DBRoomCategory.getAllCategories();
        return (Collection<IRoomCategory>) DynamicMapper.mapCollection(dbc);
    }

    /**
     * sucht nach freien Räumen in einer Periode in der Datenbank
     *
     * @param start Der Start der Periode
     * @param ende Das ende der Periode
     * @return Alle freien Zimmer in der angegebenen Periode
     */
    @Override
    public Collection<IRoom> getFreeRooms(Date start, Date ende)
    {
        DBRoomCategory cat = (DBRoomCategory) DynamicMapper.map(this);
        Collection<DBRoom> dbc = cat.getFreeRooms(start, ende);
        return (Collection<IRoom>) DynamicMapper.mapCollection((Set<DBRoom>) dbc);
    }

    /**
     * Gibt alle Zimmer aus die vorhanden sind
     * @return 
     * Alle Zimmer, die das System kennt
     */
    @Override
    public Collection<IRoom> getAllRooms()
    {
        return Room.getRoomsByCategory(this);
    }

    @Override
    public Integer getBedCount()
    {
        return bedCount;
    }

    @Override
    public BigDecimal getPriceFor(Date startDate) throws NoPriceDefinedException
    {        
        for (IRoomCategoryPrice p : price)
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
    
    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final RoomCategory other = (RoomCategory) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name))
        {
            return false;
        }
        return true;
    }

}