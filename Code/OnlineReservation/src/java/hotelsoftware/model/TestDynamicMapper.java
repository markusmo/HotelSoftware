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
import hotelsoftware.model.domain.users.IPermission;
import hotelsoftware.model.domain.users.IRole;
import hotelsoftware.model.domain.users.IUser;
import hotelsoftware.model.domain.users.Role;
import hotelsoftware.model.domain.users.User;
import java.util.Date;
import java.util.LinkedHashSet;

/**
 *Diese Klasse ist dafür da, um Dynamic Mapper tests durchzuführen
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
        LinkedHashSet<IRole> roles = new LinkedHashSet<IRole>();
        
        roles.add(Role.create("Harry", new LinkedHashSet<IPermission>()));
        roles.add(Role.create("Todes", new LinkedHashSet<IPermission>()));
        
        IUser user = User.create("Markus", "Mohanty", roles);
        
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
        
        LinkedHashSet<DBHabitation> habitations = new LinkedHashSet<DBHabitation>();
        
        dbGuest.setHabitations(habitations);
        
        Guest guest = (Guest) DynamicMapper.map(dbGuest);
        
        System.out.println(guest.getBirthday());
        System.out.println(guest.getHabitations().size());
    }
}
