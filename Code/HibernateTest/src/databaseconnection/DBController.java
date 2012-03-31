/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnection;

import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mohi
 */
public class DBController
{
    private Session session;
    
    public void initConnection()
    {
        this.session = HibernateUtilTest.getSessionFactory().openSession();
    }
    
    public void closeConnection()
    {
        session.close();
    }
    
    public void insertUser(User user)
    {
        initConnection();
        Transaction ts = session.beginTransaction();
        
        session.save(user);
        
        ts.commit();
        closeConnection();
    }
}
