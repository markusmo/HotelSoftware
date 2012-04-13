/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.users;

import hotelsoftware.database.model.Users;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class User
{
    private String username;
    private Permission permission;

    public User(String username, Permission permission)
    {
        this.username = username;
        this.permission = permission;
       //TODO create Users on db-level
        //generated id!!!, password?
        //everytime creating new user to ask permissions etc.?
    }
    
    

    
}
