/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import hotelsoftware.model.domain.room.data.RoomCategoryData;
import hotelsoftware.model.domain.room.data.RoomStatusData;
import hotelsoftware.model.domain.room.data.RoomData;
import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.room.DBRoom;
import hotelsoftware.model.database.room.DBRoomCategory;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.service.data.HabitationData;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.Set;

/**
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Room implements RoomData
{
    private String number;
    private Collection<RoomOption> options;
    private RoomCategory category;
    //private Collection<Habitation> habitations;
    private Collection<RoomStatus> status;
    private Integer id;

    private Room(String number, RoomCategory category)
    {
        this.number = number;
        this.category = category;
    }

    public static Room create(String number, RoomCategory category)
    {
        return new Room(number, category);
    }

    public Room()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    
    public RoomCategory getCategory()
    {
        return category;
    }

    public void setCategory(RoomCategory category)
    {
        this.category = category;
    }
/*
    public Collection<Habitation> getHabitations()
    {
        return habitations;
    }

    public void setHabitations(Collection<Habitation> habitations)
    {
        this.habitations = habitations;
    }
*/
    @Override
    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public Collection<RoomOption> getOptions()
    {
        return options;
    }

    public void setOptions(Collection<RoomOption> options)
    {
        this.options = options;
    }

    public Collection<RoomStatus> getStatus()
    {
        return status;
    }

    public void setStatus(Collection<RoomStatus> status)
    {
        this.status = status;
    }

    public RoomCategoryData getCategoryData()
    {
        return (RoomCategoryData) getCategory();
    }

    public Collection<HabitationData> getHabitationCollectionData()
    {
        return null;
        //TODO
        //return new HelperFunctions<HabitationData, Habitation>().castCollectionUp(getHabitations());
    }

    public Collection<RoomOptionData> getOptionsData()
    {
        return new HelperFunctions<RoomOptionData, RoomOption>().castCollectionUp(getOptions());
    }

    public Collection<RoomStatusData> getStatusData()
    {
        return new HelperFunctions<RoomStatusData, RoomStatus>().castCollectionUp(getStatus());
    }

    public static Room getRoomByNumber(String number)
    {
        return (Room) DynamicMapper.map(DBRoom.getRoomByNumber(number));
    }

    public static Collection<Room> getRoomsByCategory(RoomCategory category)
    {
        DBRoomCategory cat = (DBRoomCategory) DynamicMapper.map(category);
        return (Collection<Room>) DynamicMapper.map(DBRoom.getRoomsByCategory(cat));
    }

    public void changeStatus(RoomStatus status)
    {
        this.status.add(status);
    }

    public void addOption(RoomOption option)
    {
        this.options.add(option);
    }
}
