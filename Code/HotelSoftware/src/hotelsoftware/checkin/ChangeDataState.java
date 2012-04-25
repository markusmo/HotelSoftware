/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.checkin;

import hotelsoftware.model.domain.parties.Address;
import hotelsoftware.model.domain.parties.data.AddressData;
import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.parties.data.CountryData;
import hotelsoftware.model.domain.parties.data.GuestData;
import hotelsoftware.model.domain.reservation.data.ReservationItemData;
import hotelsoftware.model.domain.room.Room;
import hotelsoftware.model.domain.room.RoomCategory;
import hotelsoftware.model.domain.room.data.RoomData;
import hotelsoftware.model.domain.room.data.RoomCategoryData;
import hotelsoftware.model.domain.service.data.*;
import hotelsoftware.model.domain.service.*;
import hotelsoftware.util.HelperFunctions;
import java.util.*;

/**
 * Diese Klasse ist abstrakt und beinhaltet alle Methoden fuer Reservations und Walk-Ins Check-In.
 * 
 * @author Dunst
 */
public abstract class ChangeDataState extends CheckInState
{
    public ChangeDataState(CheckInController context, int counter, Map<Integer, RoomSelection> roomSelections, Collection<ReservationItemData> reservationItems)
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
    public Collection<CountryData> getAllCountries()
    {
        return new HelperFunctions<CountryData, Country>().castCollectionUp(Country.getAllCountries());
    }

    @Override
    public void removeRoomSelection(int selectionIndex)
    {
        throw new IllegalStateException();
    }

    @Override
    public Collection<RoomData> changeRoomCategory(int selectionIndex, RoomCategoryData category)
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
    public Collection<RoomCategoryData> getAllCategories()
    {
        return new HelperFunctions<RoomCategoryData, RoomCategory>().castCollectionUp(RoomCategory.getAllCategorys());
    }

    @Override
    public RoomData getRoomData(int selectionIndex)
    {
        return roomSelections.get(selectionIndex).getRoom();
    }

    @Override
    public Collection<ExtraServiceData> getAllHabitationServices()
    {
        return new HelperFunctions<ExtraServiceData, ExtraService>().castCollectionUp(ExtraService.getAllHabitationServices()); 
    }
}
