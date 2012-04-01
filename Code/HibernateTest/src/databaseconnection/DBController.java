/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnection;

import hibernatetest.DBObserver;
import hibernatetest.GUIObserver;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mohi
 */
public class DBController implements DBObserver
{
    private Session session;
    private GUIObserver observer;
    
    public DBController(GUIObserver observer)
    {
        this.observer = observer;
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
    
    public void getUser(String firstname, String lastname)
    {
        String querystring = "from User user where user.firstname" + "="+firstname
                +" and " + "user.lastname" + "=" + lastname;
        Transaction ts = session.beginTransaction();
        
        List<User> userlist = (List<User>) session.createQuery(querystring);
        
        for(User u : userlist)
        {
            observer.getUser(u);
        }        
    }

    public void insertUser(String firstname, String lastname, String email, String somedata)
    {
        Transaction ts = session.beginTransaction();
        
        User user = new User(email, firstname, lastname);
        if(somedata != null)
        {
            user.setSomeData(somedata);
        }
        
        session.save(user);
        
        ts.commit();    
    }
}
