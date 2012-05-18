/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

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
    }
}
