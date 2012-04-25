package hotelsoftware.model.domain.room;

import hotelsoftware.model.domain.room.data.RoomStatusData;

/**
 * Diese Klasse bildte alle Zimmerstauts ab, mit der das System arbeitet
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class RoomStatus implements RoomStatusData
{
    private String statusName;
    private Integer id;

    @Override
    public String getStatusName()
    {
        return statusName;
    }

    public void setStatusName(String statusName)
    {
        this.statusName = statusName;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    
    

   
    
    
}
