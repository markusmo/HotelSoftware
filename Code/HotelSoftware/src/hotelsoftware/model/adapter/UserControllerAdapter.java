/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.adapter;

import at.fhv.roomanizer.application.IUserController;
import at.fhv.roomanizer.domain.person.IUser;
import hotelsoftware.controller.login.LoginController;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class UserControllerAdapter implements IUserController
{

    @Override
    public List<IUser> loadAllUser() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IUser loadFirstUser() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException
    {
        return new UserAdapter(LoginController.getInstance().getCurrentUser());
    }
    
}
