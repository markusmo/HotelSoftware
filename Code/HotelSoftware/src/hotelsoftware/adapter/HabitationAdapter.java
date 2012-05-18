/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.Habitation;
import at.fhv.roomanizer.domain.person.Guest;
import at.fhv.roomanizer.domain.person.User;
import at.fhv.roomanizer.domain.room.Room;
import at.fhv.roomanizer.domain.service.IType;
import at.fhv.roomanizer.domain.service.Type;
import hotelsoftware.model.domain.service.IHabitation;
import hotelsoftware.util.HelperFunctions;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Johannes
 */
public class HabitationAdapter extends Habitation implements Adapter<hotelsoftware.model.domain.service.IHabitation>
{
    private hotelsoftware.model.domain.service.Habitation habitation;
    
    public HabitationAdapter()
    {
    }

    public HabitationAdapter(hotelsoftware.model.domain.service.IHabitation habitation)
    {
        this.habitation = (hotelsoftware.model.domain.service.Habitation) habitation;
    }

    public HabitationAdapter(Habitation habitation)
    {
        this.habitation = new hotelsoftware.model.domain.service.Habitation();
        this.habitation.setCreated(habitation.getCreated());
        this.habitation.setEnd(habitation.getEnd());
        this.habitation.setStart(habitation.getStart());
        this.habitation.setIdServices(habitation.getId());
        this.habitation.setPrice(BigDecimal.valueOf(habitation.getPrice()));
        this.habitation.setUsers((new UserAdapter(habitation.getUser())).getOurType());
    }

    @Override
    public IType getIType()
    {
        return getType();
    }

    @Override
    public int getId()
    {
        return this.habitation.getIdServices();
    }

    @Override
    public Type getType()
    {
        return new TypeAdapter(this.habitation.getServiceType());
    }

    @Override
    public void setId(int id)
    {
        this.habitation.setIdServices(id);
    }

    @Override
    public void setType(Type type)
    {
        this.habitation.setServiceType((new TypeAdapter(type)).getOurType());
    }
    
    

    @Override
    public void addGuest(Guest guest)
    {
        //FIXME 
        habitation.addGuests(null);
    }

    @Override
    public Date getCreated()
    {
        return habitation.getCreated();
    }

    @Override
    public String getDeposit()
    {
        //TODO
        return "Fixme";
    }

    @Override
    public Date getEnd()
    {
        return habitation.getEnd();
    }

    @Override
    public List<Guest> getGuests()
    {
        return new LinkedList(HelperFunctions.getAdaptedList(habitation.getGuests(), GuestAdapter.class));
    }

    @Override
    public double getPrice()
    {
        return habitation.getPrice().doubleValue();
    }

    @Override
    public Room getRoom()
    {
        return new RoomAdapter(habitation.getRooms());
    }

    @Override
    public Date getStart()
    {
        return habitation.getStart();
    }

    @Override
    public User getUser()
    {
        return new UserAdapter(habitation.getUsers());
    }

    @Override
    public boolean isBetween(Date start, Date end)
    {
        return super.isBetween(start, end);
    }

    @Override
    public void setCreated(Date created)
    {
        super.setCreated(created);
    }

    @Override
    public void setDeposit(String deposit)
    {
        super.setDeposit(deposit);
    }

    @Override
    public void setEnd(Date end)
    {
        super.setEnd(end);
    }

    @Override
    public void setGuests(List<Guest> guests)
    {
        super.setGuests(guests);
    }

    @Override
    public void setPrice(double price)
    {
        super.setPrice(price);
    }

    @Override
    public void setRoom(Room room)
    {
        super.setRoom(room);
    }

    @Override
    public void setStart(Date start)
    {
        super.setStart(start);
    }

    @Override
    public void setUser(User user)
    {
        super.setUser(user);
    }

    @Override
    public hotelsoftware.model.domain.service.Habitation getOurType()
    {
        return habitation;
    }

    @Override
    public void setOurType(hotelsoftware.model.domain.service.IHabitation type)
    {
        this.habitation = (hotelsoftware.model.domain.service.Habitation) type;
    }
}
