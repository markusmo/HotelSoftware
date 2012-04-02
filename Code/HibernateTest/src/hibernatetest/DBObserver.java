/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernatetest;

/**
 *
 * @author mohi
 */
public interface DBObserver
{
    public void insertUser(String firstname, String lastname, String email, String somedata);
    
    public void getUser(String firstname, String lastname);
}
