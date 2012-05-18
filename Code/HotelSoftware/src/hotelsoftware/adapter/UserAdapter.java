/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

<<<<<<< HEAD
/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class UserAdapter extends at.fhv.roomanizer.domain.person.User
{
    private hotelsoftware.model.domain.users.IUser ourUser;
    
    public UserAdapter(hotelsoftware.model.domain.users.IUser ourUser)
    {
        this.ourUser = ourUser;
    }
    
    @Override
    public void setId(int id)
    {
        this.ourUser.setId(id);
    }

    @Override
    public String getUsername()
    {
        return this.ourUser.getUsername();
    }

    @Override
    public void setUsername(String username)
    {
        this.ourUser.setUsername(username);
    }

    @Override
    public String getPassword()
    {
        return this.ourUser.getPassword();
    }

    @Override
    public void setPassword(String password)
    {
        this.ourUser.setPassword(password);
    }

    @Override
    public boolean getActive()
    {
        return this.ourUser.getActive();
    }

    @Override
    public void setActive(boolean active)
    {
        this.ourUser.setActive(active);
=======
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
>>>>>>> ff6384fd29d4d97e384c6d2f677a9a5655214f02
    }
}
