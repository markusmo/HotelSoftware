/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model;

import hotelsoftware.model.database.parties.DBGuest;
import hotelsoftware.model.database.service.DBHabitation;
import hotelsoftware.model.database.users.DBRole;
import hotelsoftware.model.database.users.DBUser;
import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.users.Permission;
import hotelsoftware.model.domain.users.Role;
import hotelsoftware.model.domain.users.User;
import java.util.Collection;
import java.util.Date;
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
        
        System.out.println("#####################################");
        
        DBGuest dbGuest = new DBGuest();
        dbGuest.setBirthday(new Date());
        
        LinkedList<DBHabitation> habitations = new LinkedList<DBHabitation>();
        
        dbGuest.setHabitations(habitations);
        
        Guest guest = (Guest) DynamicMapper.map(dbGuest);
        
        System.out.println(guest.getBirthday());
        System.out.println(guest.getHabitations().size());
    }
}
