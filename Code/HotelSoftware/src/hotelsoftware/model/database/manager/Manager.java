/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.manager;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Dunst
 */
public abstract class Manager
{
    private SessionContainer sessionContainer;
    
    
    public Manager()
    {
        sessionContainer = SessionContainer.getInstance();
    }
    
    public void startTransaction()
    {
        sessionContainer.startTransaction();
    }
    
    public void rollback()
    {
        sessionContainer.rollback();
    }
    
    public void commit()
    {
        sessionContainer.commit();
    }
    
    private Transaction getTransaction()
    {
        return sessionContainer.getTransaction();
    }
    
    protected boolean isTransactionOpen()
    {
        return getTransaction().isActive();
    }
    
    protected Session getSession()
    {
        return sessionContainer.getSession();
    }
}
