/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.room.DBRoom;
import hotelsoftware.model.database.room.DBRoomCategory;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.service.HabitationData;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Room implements RoomData
{
    private int number; //FIXME maybe String (DB???)
    private Collection<RoomOption> options;
    private RoomCategory category;
    private RoomStatus currentStatus;
    private Collection<Habitation> habitationCollection;
    private Collection<RoomStatus> status;

    private Room(int number, RoomCategory category)
    {
        this.number = number;
        this.category = category;
    }
            
    public static Room create(int number, RoomCategory category)
    {
        return new Room(number, category);
    }
    
    public Room(){}
    
    public RoomCategory getCategory()
    {
        return category;
    }

    public void setCategory(RoomCategory category)
    {
        this.category = category;
    }

    public Collection<Habitation> getHabitationCollection()
    {
        return habitationCollection;
    }

    public void setHabitationCollection(Collection<Habitation> habitationCollection)
    {
        this.habitationCollection = habitationCollection;
    }

    @Override
    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
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

    public CategoryData getCategoryData()
    {
        return (CategoryData) getCategory();
    }

    public Collection<HabitationData> getHabitationCollectionData()
    {
        return new HelperFunctions<HabitationData, Habitation>().castCollectionUp(getHabitationCollection());
    }

    public Collection<RoomOptionData> getOptionsData()
    {
        return new HelperFunctions<RoomOptionData, RoomOption>().castCollectionUp(getOptions());
    }

    public Collection<RoomStatusData> getStatusData()
    {
        return new HelperFunctions<RoomStatusData, RoomStatus>().castCollectionUp(getStatus());
    }
    
    public static Room getRoomByNumber(int number)
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
        this.currentStatus = status;
        this.status.add(status);
    }
    
    public void addOption(RoomOption option)
    {
        this.options.add(option);
    }
}
