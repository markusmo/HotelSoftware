package hotelsoftware.model.domain.room;

import hotelsoftware.controller.data.room.RoomCategoryData;
import hotelsoftware.controller.data.room.RoomOptionData;
import hotelsoftware.controller.data.room.RoomsRoomStatusData;
import hotelsoftware.controller.data.service.HabitationData;
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

    @Override
    public boolean isFree(Date start, Date end)
    {
        for (IHabitation h : this.getHabitations())
        {
            if ((h.getStart().before(start) && h.getEnd().after(start)) || (h.getStart().before(end) && h.getEnd().after(end)))
            {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Gibt ein Zimmer nach der Zimmernummer aus
     *
     * @param number Die gewünschte Zimmernummer
     * @return Das Zimmer mit der gesuchten Zimmernummer
     */
    public static IRoom getRoomByNumber(String number)
    {
        return RoomManager.getInstance().getRoomByNumber(number);
    }
}
