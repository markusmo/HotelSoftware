/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.checkin;

import hotelsoftware.domain.parties.Address;
import hotelsoftware.domain.parties.Guest;
import hotelsoftware.domain.room.Category;
import hotelsoftware.domain.room.Room;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dunst
 */
public abstract class ChangeDataState extends CheckInState
{    
    @Override
    public Guest changeGuestData(GuestData guest, String firstName, String lastName, Date birthday, Address address)
    {
        Guest g = (Guest)GuestData;
        g.setFirstName(firstName);
        g.setLasttName(lastName);
        g.setBirthday(birthday);
        g.setAddress(address);
    }
    
    @Override
    public GuestData addGuest(String firstName, String lastName, Date birthday, AddressData address)
    {
        GuestData guest = new Guest(firstName, lastName, birthday, address);
        
        //TODO save somehow
        
        return guest;
    }
    
    @Override
    public void assignRoom(GuestData guest, RoomData room)
    {
        room.addGuest(guest);
    }
    
    @Override
    public int addRoomSelection()
    {
        throw new IllegalStateException();
    }
    
    @Override
    public void removeRoomSelection(int selectionIndex)
    {
        throw new IllegalStateException();
    }
    
    @Override
    public List<RoomData> changeRoomCategory(int selectionIndex, Category category)
    {
        //TODO umwandeln
        category.getAllRooms();
    }
    
    @Override
    public void changeRoom(int selectionIndex, int roomNumber)
    {
        throw new IllegalStateException();
    }
}
