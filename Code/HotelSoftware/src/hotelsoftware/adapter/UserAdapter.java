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
public class UserAdapter extends User implements Adapter<hotelsoftware.model.domain.users.IUser>
{
    private hotelsoftware.model.domain.users.User user;

    public UserAdapter(hotelsoftware.model.domain.users.IUser user)
    {
       this.user = (hotelsoftware.model.domain.users.User) user;
    }

    public UserAdapter(User user)
    {
        this.user = new hotelsoftware.model.domain.users.User();
        this.user.setActive(user.getActive());
        this.user.setId(user.getId());
        this.user.setPassword(user.getPassword());
        this.user.setUsername(user.getUsername());
    }
    
    public UserAdapter()
    {
    }

    @Override
    public boolean getActive()
    {
        return user.getActive();
    }

    @Override
    public int getId()
    {
        return user.getId();
    }

    @Override
    public String getPassword()
    {
        return user.getPassword();
    }

    @Override
    public String getUsername()
    {
     return user.getUsername();
    }

    @Override
    public void setActive(boolean active)
    {
        user.setActive(active);
    }

    @Override
    public void setId(int id)
    {
        user.setId(id);
    }

    @Override
    public void setPassword(String password)
    {
       user.setPassword(password);
    }

    @Override
    public void setUsername(String username)
    {
        user.setUsername(username);
    }

    
   
    @Override
    public hotelsoftware.model.domain.users.IUser getOurType()
    {
       return user;
    }

    @Override
    public void setOurType(hotelsoftware.model.domain.users.IUser type)
    {
        user = (hotelsoftware.model.domain.users.User) type;
    }
}
