/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnection;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mohi
 */
public class DBController
{
    private Session session;
    
    public DBController()
    {
        initConnection();
    }
    
    private void initConnection()
    {
        this.session = HibernateUtilTest.getSessionFactory().openSession();
    }
    
    public void closeConnection()
    {
        session.close();
    }
    
    public void insertUser(User user)
    {
        Transaction ts = session.beginTransaction();
        
        session.save(user);
        
        ts.commit();
    }
    
    public User getUser(String firstname, String lastname)
    {
        String querystring = "from User user where user.firstname" + "="+firstname
                +" and " + "user.lastname" + "=" + lastname;
        Transaction ts = session.beginTransaction();
        
        List<User> userlist = (List<User>) session.createQuery(querystring);
        
        for(User u : userlist)
        {
            return u;
        }
        
        return null;
    }
}
