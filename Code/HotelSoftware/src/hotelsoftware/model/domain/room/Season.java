package hotelsoftware.model.domain.room;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * Bildet eine Saison ab, mit das System arbeitet.
 * @author Johannes
 */
public class Season implements ISeason
{
    private Date start;
    private Date end;
    private Integer id;
    private String name;
    private Set<IRoomCategoryPrice> roomcategoryprices;

    public Season()
    {
    }

    @Override
    public Date getEnd()
    {
        return end;
    }

    @Override
    public void setEnd(Date end)
    {
        this.end = end;
    }

    @Override
    public Integer getId()
    {
        return id;
    }

    @Override
    public void setId(Integer id)
    {
        this.id = id;
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
    public Set<IRoomCategoryPrice> getRoomcategoryprices()
    {
        return roomcategoryprices;
    }

    @Override
    public void setRoomcategoryprices(Set<IRoomCategoryPrice> roomcategoryprices)
    {
        this.roomcategoryprices = roomcategoryprices;
    }

    @Override
    public Date getStart()
    {
        return start;
    }

    @Override
    public void setStart(Date start)
    {
        this.start = start;
    }
    
}
