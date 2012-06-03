package hotelsoftware.model.database.users;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Diese Klasse bildet die Befugnisse fuer das System auf die Datenbank ab.
 * @author mohi
 */
@Entity
@Table(name = "permissions", catalog = "`roomanizer`", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "name"
    })
})
@XmlRootElement
public class DBPermission implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    public DBPermission()
    {
    }

    public DBPermission(Integer id)
    {
        this.id = id;
    }

    public DBPermission(Integer id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public DBPermission(String name) {
        this.name = name;
    }
    
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Permissions[ id=" + id + " ]";
    }
    
//    /**
//     * Gibt alle Befugnisse aus
//     * @return
//     * Ein Set mit allen Befugnissen, die verfuegbar sind
//     * @throws HibernateException 
//     */
//    public static Collection<DBPermission> getPermissions() throws HibernateException
//    {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction ts = session.beginTransaction();
//        ts.begin();
//        Criteria criteria = session.createCriteria(DBPermission.class);
//        List<DBPermission> retList = criteria.list();
//
//        return new LinkedHashSet<DBPermission>(retList);
//    }
    
//    /**
//     * Sucht eine spezielle Befugnis in der Datenbank
//     * @param permission
//     * Die Befugnis, nach der gesucht wird
//     * @return
//     * Die Befugnis, nach der gesucht wird
//     * @throws HibernateException 
//     * Wirft einen Fehler, wenn etwas bei der Transaktion fehleschlaegt
//     */
//    public static DBPermission getPermissionByName(String permission) throws HibernateException
//    {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction ts = session.beginTransaction();
//        ts.begin();
//        Criteria criteria = session.createCriteria(DBPermission.class);
//        List<DBPermission> retList = criteria.list();
//
//        for (DBPermission permissions : retList)
//        {
//            if (permissions.getName().equals(permission))
//            {
//                return permissions;
//            }
//        }
//        return null;
//    }   
}
