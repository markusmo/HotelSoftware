/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.beans;

import java.io.Serializable;

/**
 *
 * @author mohi
 */
public abstract class CustomerBean implements Serializable
{
    private String username;
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
}
