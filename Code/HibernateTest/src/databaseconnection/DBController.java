/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnection;

import hibernatetest.DBObserver;
import hibernatetest.GUIObserver;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
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
    }
    
    private void initConnection()
    {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
    
    public void closeConnection()
    {
        session.close();
    }
    
    public void getUser(String firstname, String lastname)
    {
        String querystring = "from User user where user.firstname" + "="+firstname
                +" and " + "user.lastname" + "=" + lastname;
        initConnection();
        Transaction ts = session.beginTransaction();
        try
        {
            List<User> userlist = session.createQuery(querystring).list();
            for(User u : userlist)
            {
                observer.getUser(u);
            }

        } catch (HibernateException e)
        {
            e.printStackTrace();
            ts.rollback();
        }finally
        {
            closeConnection();
        }
    }

    public void insertUser(String firstname, String lastname, String email, String somedata)
    {
        initConnection();
        Transaction ts = session.beginTransaction();
        
        User user = new User();
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setSomeData(somedata);
        
        Serializable id = session.save(user);
        
        ts.commit();  
        closeConnection();
    }
}
