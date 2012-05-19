package hotelsoftware.model.domain.room;

import hotelsoftware.controller.data.room.RoomStatusData;

/**
 * Diese Klasse bildte alle Zimmerstauts ab, mit der das System arbeitet
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class RoomStatus implements IRoomStatus
{
    private String statusName;
    private Integer id;

    @Override
    public String getStatusName()
    {
        return statusName;
    }

    @Override
    public void setStatusName(String statusName)
    {
        this.statusName = statusName;
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
    
    public static IRoomStatus getRoomStatusByName(String name)
    {
        return RoomFacade.getInstance().getRoomStatusByName(name);
    }
   
    
    
}
