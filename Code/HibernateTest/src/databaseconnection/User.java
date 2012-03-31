/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnection;

import java.io.Serializable;

/**
 *
 * @author mohi
 */
public class User implements Serializable
{
    private String email;
    private String firstname;
    private String lastname;
    private String someData;

    public User(String email, String firstname, String lastname)
    {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getSomeData()
    {
        return someData;
    }

    public void setSomeData(String someData)
    {
        this.someData = someData;
    }
}
