package hotelsoftware.checkin.console;

import hotelsoftware.database.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author mohi
 */
public class CheckinOnConsole
{
    public static void main(String args[])
    {
        System.out.println("This is a checkin :-D");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        System.out.println("session closed");
    }
}
