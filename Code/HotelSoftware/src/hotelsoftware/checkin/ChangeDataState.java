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
public class ChangeDataState extends CheckInState
{
    @Override
    public List<Guest> getGuests()
    {
        throw new IllegalStateException();
    }
    
    @Override
    public Guest changeGuestData(Guest guest, String firstName, String lastName, Date birthday, Address address)
    {
        throw new IllegalStateException();
    }
    
    @Override
    public Guest addGuest(String firstName, String lastName, Date birthday, Address address)
    {
        throw new IllegalStateException();
    }
    
    @Override
    public void assignRoom(Guest guest, Room room)
    {
        throw new IllegalStateException();
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
    public List<Room> changeRoomCategory(int selectionIndex, Category category)
    {
        throw new IllegalStateException();
    }
    
    @Override
    public void changeRoom(int selectionIndex, int roomNumber)
    {
        throw new IllegalStateException();
    }
}
