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
public class RoomOption implements IRoomOption
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

    public static IRoomOption create(String name)
    {
        return new RoomOption(name);
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
    public Integer getId()
    {
        return id;
    }

    @Override
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
    public static Set<IRoomOption> getRoomOptions()
    {
        Set<DBRoomOption> dbro = DBRoomOption.getRoomOptions();
        return (Set<IRoomOption>) DynamicMapper.map(dbro);
    }
}
