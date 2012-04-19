/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.checkin;

import hotelsoftware.model.domain.parties.Address;
import hotelsoftware.model.domain.parties.AddressData;
import hotelsoftware.model.domain.parties.Guest;
<<<<<<< HEAD
import hotelsoftware.model.domain.parties.GuestData;
import hotelsoftware.model.domain.room.Category;
import hotelsoftware.model.domain.room.CategoryData;
=======
import hotelsoftware.model.domain.room.RoomCategory;
>>>>>>> f691635ff1877864b8b81a3e5b46bc9ead6ff6e6
import hotelsoftware.model.domain.room.Room;
import hotelsoftware.model.domain.room.RoomData;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dunst
 */
public abstract class ChangeDataState extends CheckInState
{    
    public ChangeDataState(CheckInController context)
    {
        super(context);
        
        roomSelections = new HashMap<Integer, RoomSelection>();
        counter = 0;
    }
    
    @Override
    public GuestData changeGuestData(GuestData guest, String firstName, String lastName, char gender, Date birthday, AddressData address)
    {
        Guest g = (Guest)guest;
        g.setFname(firstName);
        g.setLname(lastName);
        g.setGender(gender);
        g.setBirthday(birthday);
        g.setAddress((Address)address);
        
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
        roomSelections.put(counter++, new RoomSelection(new Category(), new Room()));
        
        return counter;
    }
    
    @Override
    public void removeRoomSelection(int selectionIndex)
    {
        throw new IllegalStateException();
    }
    
    @Override
<<<<<<< HEAD
    public Collection<RoomData> changeRoomCategory(int selectionIndex, CategoryData category)
=======
    public Collection<RoomData> changeRoomCategory(int selectionIndex, RoomCategory category)
>>>>>>> f691635ff1877864b8b81a3e5b46bc9ead6ff6e6
    {
        Category cat = (Category)category;
        
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
    public Collection<CategoryData> getAllCategories()
    {        
        return new HelperFunctions<CategoryData, Category>().castCollectionUp(Category.getAllCategories());
    }
}
