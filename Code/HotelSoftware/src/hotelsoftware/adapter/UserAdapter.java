/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.person.User;



/**
 *
 * @author Johannes
 */
public class UserAdapter extends User implements Adapter<hotelsoftware.model.domain.users.User>
{
    private hotelsoftware.model.domain.users.User user;

    UserAdapter(hotelsoftware.model.domain.users.IUser user)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int getId()
    {
        return user.getId();
    }

    @Override
    public hotelsoftware.model.domain.users.User getOurType()
    {
       return user;
    }

    @Override
    public void setOurType(hotelsoftware.model.domain.users.User type)
    {
        user = type;
    }
}
