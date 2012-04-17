package hotelsoftware.checkin.console;

import hotelsoftware.database.HibernateUtil;
import hotelsoftware.model.database.users.DBUser;
import java.util.List;
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
        session.beginTransaction();
        List<DBUser> userlist = session.createQuery("from Users where id = 1").list();
        DBUser user1 = (DBUser)session.createQuery("from Users where id = 1").uniqueResult();
        
        session.close();
        for (DBUser users : userlist) {
            System.out.println(users.getUsername());
        }
        System.out.println("session closed");
        System.out.println(user1.getPassword());
    }
}
