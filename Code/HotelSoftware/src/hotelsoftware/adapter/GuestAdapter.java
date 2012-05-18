package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.person.Guest;
import hotelsoftware.model.domain.parties.IGuest;

/**
 *
 * @author Johannes
 */
public class GuestAdapter extends Guest implements Adapter<hotelsoftware.model.domain.parties.IGuest>
{
    private hotelsoftware.model.domain.parties.Guest guest;

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
