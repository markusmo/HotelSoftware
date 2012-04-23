/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Johannes
 */
public class Season
{
    private Date start;
    private Date end;
    private Integer id;
    private String name;
    private Collection<RoomCategoryPrice> roomcategorypricesCollection;

    public Season()
    {
    }

    public Date getEnd()
    {
        return end;
    }

    public void setEnd(Date end)
    {
        this.end = end;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Collection<RoomCategoryPrice> getRoomcategorypricesCollection()
    {
        return roomcategorypricesCollection;
    }

    public void setRoomcategorypricesCollection(Collection<RoomCategoryPrice> roomcategorypricesCollection)
    {
        this.roomcategorypricesCollection = roomcategorypricesCollection;
    }

    public Date getStart()
    {
        return start;
    }

    public void setStart(Date start)
    {
        this.start = start;
    }
    
}
