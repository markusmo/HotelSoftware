package hotelsoftware.checkin.console;

import hotelsoftware.util.HibernateUtil;
import hotelsoftware.model.database.users.DBUser;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mohi
 */
public class CheckinOnConsole
{
    public static void main(String args[])
    {
//        try
//        {
//            System.out.println("This is a checkin :-D");
//            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//            Transaction ts = session.beginTransaction();
//            ts.begin();
//
//            MessageDigest coder;
//            coder = MessageDigest.getInstance("MD5");
//            String password = new String(coder.digest("asdf".getBytes()));
//
//            DBUser user = new DBUser("user", password);
//            user.setActive(true);
//            session.save(user);
//            session.flush();
//            ts.commit();
//
//        }
//        catch (NoSuchAlgorithmException ex)
//        {
//            Logger.getLogger(CheckinOnConsole.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        List<DBUser> userlist = session.createCriteria(DBUser.class).list();
////        DBUser user1 = (DBUser)session.createQuery("from DBUser where id = 1").uniqueResult();
//        
//        for (DBUser users : userlist) {
//            System.out.println(users.getUsername());
//        }
//        System.out.println("session closed");
//        for (DBUser users : userlist) {
//            System.out.println(users.getUsername());
//        }
//        System.out.println("session closed");
//        //System.out.println(user1.getPassword());
    }
}
