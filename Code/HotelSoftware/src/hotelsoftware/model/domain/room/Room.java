package hotelsoftware.model.domain.room;

import hotelsoftware.controller.data.room.*;
import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.room.DBRoom;
import hotelsoftware.model.database.room.DBRoomCategory;
import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.service.IHabitation;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.Date;

/**
 * Bildet ein Zimmer ab, mit dem das System arbeitet.
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Room implements IRoom
{

    private String number;
    private Collection<IRoomOption> options;
    private IRoomCategory category;
    private Collection<IRoomRoomStatus> status;
    private Collection<IHabitation> habitations;
    private Integer id;

    private Room(String number, IRoomCategory category)
    {
        this.number = number;
        this.category = category;
    }

    public static IRoom create(String number, IRoomCategory category)
    {
        return new Room(number, category);
    }

    public Room()
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
    public IRoomCategory getCategory()
    {
        return category;
    }

    @Override
    public void setCategory(IRoomCategory category)
    {
        this.category = category;
    }
    /*
     * public Collection<Habitation> getHabitations() { return habitations; }
     *
     * public void setHabitations(Collection<Habitation> habitations) {
     * this.habitations = habitations; }
     */

    @Override
    public String getNumber()
    {
        return number;
    }

    @Override
    public void setNumber(String number)
    {
        this.number = number;
    }

    @Override
    public Collection<IRoomOption> getOptions()
    {
        return options;
    }

    @Override
    public void setOptions(Collection<IRoomOption> options)
    {
        this.options = options;
    }

    @Override
    public Collection<IRoomRoomStatus> getStatus()
    {
        return status;
    }

    @Override
    public void setStatus(Collection<IRoomRoomStatus> status)
    {
        this.status = status;
    }

    @Override
    public RoomCategoryData getCategoryData()
    {
        return (RoomCategoryData) getCategory();
    }

    @Override
    public Collection<HabitationData> getHabitationCollectionData()
    {
        return HelperFunctions.castCollectionUp(habitations, HabitationData.class, IHabitation.class);
    }

    @Override
    public Collection<RoomOptionData> getOptionsData()
    {
        return new HelperFunctions<RoomOptionData, IRoomOption>().castCollectionUp(
                getOptions());
    }

    @Override
    public Collection<RoomsRoomStatusData> getStatusData()
    {
        return new HelperFunctions<RoomsRoomStatusData, IRoomRoomStatus>().castCollectionUp(
                getStatus());
    }

    /**
     * Gibt ein Zimmer nach der Zimmernummer aus
     *
     * @param number Die gewuenschte Zimmernummer
     * @return Das Zimmer mit der gesuchten Zimmernummer
     */
    public static IRoom getRoomByNumber(String number)
    {
        return RoomFacade.getInstance().getRoomByNumber(number);
    }

//    /**
//     * Gibt alle Zimmer mit einer angegebenen Kategorie aus
//     *
//     * @param category Die Kategorie nach der gesucht wird
//     * @return Alle Zimmer nach dieser Kategorie
//     */
//    public static Collection<IRoom> getRoomsByCategory(IRoomCategory category)
//    {
//        DBRoomCategory cat = (DBRoomCategory) DynamicMapper.map(category);
//        return (Collection<IRoom>) DynamicMapper.map(DBRoom.getRoomsByCategory(
//                cat));
//    }

    @Override
    public void changeStatus(IRoomRoomStatus status)
    {
        this.status.add(status);
    }

    @Override
    public void addOption(IRoomOption option)
    {
        this.options.add(option);
    }

    @Override
    public Collection<IHabitation> getHabitations()
    {
        return habitations;
    }

    @Override
    public void setHabitations(Collection<IHabitation> habitations)
    {
        this.habitations = habitations;
    }

    public boolean isFree(Date start, Date end)
    {
        //TODO implement - see RoomCategory
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
