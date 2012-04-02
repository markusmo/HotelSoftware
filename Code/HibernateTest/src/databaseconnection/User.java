/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnection;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author mohi
 */
@Entity
@Table(name="usertest")
public class User implements Serializable
{
    private int id;
    private String email;
    private String firstname;
    private String lastname;
    private String someData;

    public User()
    {
    }

    public User(String email, String firstname, String lastname, String someData)
    {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.someData = someData;
    }
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    
    @Column(name="email")
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Column(name="firstname")
    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    @Column(name="lastname")
    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }
    
    @Column(name="somedata")
    public String getSomeData()
    {
        return someData;
    }

    public void setSomeData(String someData)
    {
        this.someData = someData;
    }

    @Override
    public String toString()
    {
        return "User{" + "email=" + email + ", firstname=" + firstname + ", lastname=" + lastname + ", someData=" + someData + '}';
    }
    
}
