package hotelsoftware.model.domain.room;

import hotelsoftware.controller.data.room.RoomOptionData;
import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.room.DBRoomOption;
import java.util.Collection;
import java.util.Set;

/**
 * Diese Klasse bildet die Zimmeroptionen (Raucher, Seeblick, usw.) ab, mit der das System arbeitet
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class RoomOption implements RoomOptionData
{
    private Integer id;
    private String name;

    public RoomOption()
    {
    }

    private RoomOption(String name)
    {
        this.name = name;
        DBRoomOption.safeNewRoomOption(name);
    }

    public RoomOption create(String name)
    {
        return new RoomOption(name);
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

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        if (this.id == null)
        {
            this.id = id;
        }
    }

    /**
     * Gibt alle Zimmeroptionen aus
     * @return 
     * Alle Zimmeroptionen, die verfuegbar sind.
     */
    public static Set<RoomOption> getRoomOptions()
    {
        Set<DBRoomOption> dbro = DBRoomOption.getRoomOptions();
        return (Set<RoomOption>) DynamicMapper.map(dbro);
    }
}
