package hotelsoftware.model.adapter;

import at.fhv.roomanizer.domain.person.Guest;
import hotelsoftware.model.domain.parties.IGuest;
import java.util.Date;

/**
 *
 * @author Johannes
 */
public class GuestAdapter extends Guest implements Adapter<hotelsoftware.model.domain.parties.IGuest>
{
    private hotelsoftware.model.domain.parties.Guest guest;
    
    public GuestAdapter()
    {
        
    }
    
    public GuestAdapter(hotelsoftware.model.domain.parties.Guest guest)
    {
        this.guest = guest;
    }

    @Override
    public Date getBirthday()
    {
        return guest.getBirthday();
    }

    @Override
    public String getFirstName()
    {
        return guest.getFname();
    }

    @Override
    public String getLastName()
    {
        return guest.getLname();
    }

    @Override
    public void setBirthday(Date birthday)
    {
        guest.setBirthday(birthday);
    }

    @Override
    public void setFirstName(String firstName)
    {
        guest.setFname(firstName);
    }

    @Override
    public void setLastName(String lastName)
    {
        guest.setLname(lastName);
    }

    @Override
    public IGuest getOurType()
    {
        return guest;
    }

    @Override
    public void setOurType(IGuest type)
    {
        guest = (hotelsoftware.model.domain.parties.Guest) type;
    }
}
