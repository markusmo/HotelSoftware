/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

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
    private Set<RoomCategoryPrice> roomcategoryprices;

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

    public Set<RoomCategoryPrice> getRoomcategoryprices()
    {
        return roomcategoryprices;
    }

    public void setRoomcategoryprices(Set<RoomCategoryPrice> roomcategoryprices)
    {
        this.roomcategoryprices = roomcategoryprices;
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
