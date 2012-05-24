package hotelsoftware.model.domain.room;

import hotelsoftware.support.NoPriceDefinedException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

/**
 * Dies Klasse bildet die Zimmerkategorie ab, mit der das System arbeitet.
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class RoomCategory implements IRoomCategory
{
    private String name;
    private Collection<IRoomCategoryPrice> price;
    private Collection<IRoom> rooms;
    private Integer bedCount;
    private Integer id;

    public RoomCategory()
    {
    }
    
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

    /**
     * Sucht eine Kategorie nach einem Namen
     *
     * @param name Der Name der gesuchten Kategorie
     * @return Die gesuchte Kategorie mit dem eingegebenen Namen
     */
    public static IRoomCategory getCategoryByName(String name)
    {
        return RoomManager.getInstance().getCategoryByName(name);
    }

    /**
     * such alle Kategorien heraus die vorhanden sin
     *
     * @return Alle Kategorieen, die verfuegbar sind.
     */
    public static Collection<IRoomCategory> getAllCategorys()
    {
        return RoomManager.getInstance().getAllCategorys();
    }

    /**
     * sucht nach freien Räumen in einer Periode in der Datenbank
     *
     * @param start Der Start der Periode
     * @param end Das ende der Periode
     * @return Alle freien Zimmer in der angegebenen Periode
     */
    @Override
    public Collection<IRoom> getFreeRooms(Date start, Date end)
    {
        Collection<IRoom> freeRooms = new LinkedList<IRoom>();
        
        for (IRoom r : this.rooms)
        {
            if (r.isFree(start, end))
            {
                freeRooms.add(r);
            }
        }
        
        return freeRooms;
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

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 19 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 19 * hash + (this.price != null ? this.price.hashCode() : 0);
        hash = 19 * hash + (this.rooms != null ? this.rooms.hashCode() : 0);
        hash = 19 * hash + (this.bedCount != null ? this.bedCount.hashCode() : 0);
        hash = 19 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public Collection<IRoom> getRooms()
    {
        return rooms;
    }

    @Override
    public void setRooms(Collection<IRoom> rooms)
    {
        this.rooms = rooms;
    }

}
