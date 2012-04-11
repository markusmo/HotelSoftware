package hotelsoftware.checkin.console;

import hotelsoftware.database.HibernateUtil;
import hotelsoftware.database.model.Users;
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
        List<Users> userlist = session.createQuery("from Users where id = 1").list();
        Users user1 = (Users)session.createQuery("from Users where id = 1").uniqueResult();
        
        session.close();
        for (Users users : userlist) {
            System.out.println(users.getUsername());
        }
        System.out.println("session closed");
        System.out.println(user1.getPassword());
    }
}
