/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.manager;

import hotelsoftware.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Dunst
 */
public class SessionContainer
{
    private Session session;
    private Transaction transaction;
    
    public Session getSession()
    {
        return session;
    }
    
    public Transaction getTransaction()
    {
        return transaction;
    }
    
    void startTransaction()
    {
        transaction = getSession().beginTransaction();
        transaction.begin();
    }
    
    void rollback()
    {
        transaction.rollback();
    }
    
    void commit()
    {
        transaction.commit();
    }
    
    private SessionContainer()
    {
        session = HibernateUtil.getSessionFactory().openSession();
    }
    
    public static SessionContainer getInstance()
    {
        return SessionContainerHolder.INSTANCE;
    }
    
    private static class SessionContainerHolder
    {
        private static final SessionContainer INSTANCE = new SessionContainer();
    }
}
