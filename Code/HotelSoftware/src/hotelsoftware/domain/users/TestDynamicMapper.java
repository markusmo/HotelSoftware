/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.users;

import hotelsoftware.database.model.DBRole;
import hotelsoftware.database.model.DBUser;
import hotelsoftware.util.DynamicMapper;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author Dunst
 */
public class TestDynamicMapper
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        LinkedList<Role> roles = new LinkedList<Role>();
        
        roles.add(Role.create("Harry", new LinkedList<Permission>()));
        roles.add(Role.create("Todes", new LinkedList<Permission>()));
        
        User user = User.create("Markus", "Mohatny", roles);
        
        DBUser dbUser = (DBUser) DynamicMapper.map(user);
        
        System.out.println(dbUser.getUsername());
        System.out.println(dbUser.getPassword());
        
        for (DBRole role : dbUser.getRoles())
        {
            System.out.println(role.getName());
        }
    }
}
