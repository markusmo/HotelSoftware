/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.checkin;

import hotelsoftware.model.domain.parties.Address;
import hotelsoftware.model.domain.parties.AddressData;
import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.parties.GuestData;
import hotelsoftware.model.domain.reservation.ReservationItemData;
import hotelsoftware.model.domain.room.Room;import hotelsoftware.model.domain.room.RoomCategory;
import hotelsoftware.model.domain.service.ExtraService;import hotelsoftware.model.domain.service.ExtraServiceData;
import hotelsoftware.util.HelperFunctions;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Dunst
 */
public abstract class ChangeDataState extends CheckInState
{
    public ChangeDataState(CheckInController context, int counter, Map<Integer, RoomSelection> roomSelections, Set<ReservationItemData> reservationItems)
    {
        super(context);
        this.counter = counter;
        this.roomSelections = roomSelections;
        this.reservationItems = reservationItems;
    }

    public ChangeDataState(CheckInController context)
    {
        super(context);

        /*
         * roomSelections = new HashMap<Integer, RoomSelection>();
         * counter = 0;
         */
    }

    @Override
    public GuestData changeGuestData(GuestData guest, String firstName, String lastName, char gender, Date birthday, AddressData address)
    {
        Guest g = (Guest) guest;
        g.setFname(firstName);
        g.setLname(lastName);
        g.setGender(gender);
        g.setBirthday(birthday);
        g.setAddress((Address) address);

        return g;
    }

    @Override
    public GuestData addGuest(String firstName, String lastName, char gender, Date birthday, AddressData address)
    {
        GuestData guest = Guest.create(lastName, lastName, gender, birthday, null);

        //TODO save somehow

        return guest;
    }

    @Override
    public void assignRoom(GuestData guest, RoomData room)
    {
        //TODO
    }

    @Override
    public int addRoomSelection()
    {
        roomSelections.put(counter++, new RoomSelection(new RoomCategory(), new Room()));

        return counter;
    }

    @Override
    public void removeRoomSelection(int selectionIndex)
    {
        throw new IllegalStateException();
    }

    @Override
    public Set<RoomData> changeRoomCategory(int selectionIndex, RoomCategoryData category)
    {
        RoomCategory cat = (RoomCategory) category;

        return new HelperFunctions<RoomData, Room>().castCollectionUp(cat.getFreeRooms(startDate, endDate));
    }

    @Override
    public void changeRoom(int selectionIndex, String roomNumber)
    {
        RoomSelection sel = roomSelections.get(selectionIndex);
        Room room = Room.getRoomByNumber(roomNumber);

        sel.setRoom(room);
    }

    @Override
    public Set<RoomCategoryData> getAllCategories()
    {
        return new HelperFunctions<RoomCategoryData, RoomCategory>().castCollectionUp(RoomCategory.getAllCategorys());
    }

    @Override
    public RoomData getRoomData(int selectionIndex)
    {
        return roomSelections.get(selectionIndex).getRoom();
    }

    @Override
    public Set<ExtraServiceData> getAllHabitationServices()
    {
        return new HelperFunctions<ExtraServiceData, ExtraService>().castCollectionUp(ExtraService.getAllHabitationServices()); 
    }
}
