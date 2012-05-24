package hotelsoftware.model.database.service;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Diese Klasse bildet einen Extraservice auf die Datenbank ab.
 * @author mohi
 */
@Entity
@Table(name = "extraservices", catalog = "`roomanizer-dev`", schema = "")
@PrimaryKeyJoinColumn(name = "idServices", referencedColumnName = "idServices")
@XmlRootElement
public class DBExtraService extends DBService implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    
    @Basic(optional = false)
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    public DBExtraService()
    {
    }

    public DBExtraService(Integer id, String name, BigDecimal price)
    {
        this.name = name;
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

//    /**
//     * Gibt alle Verpflegungsarten aus
//     * @return 
//     * Alle Verpflegunsarten, die verfuegbar sind
//     */
//    public static Collection<DBExtraService> getAllHabitationServices()
//    {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction ts = session.beginTransaction();
//        ts.begin();
//        
//        SQLQuery query = session.createSQLQuery("SELECT * from extraservices es INNER JOIN services s ON es.idServices = s.idServices "
//                + "INNER JOIN servicetypes st ON s.idServiceTypes = st.id WHERE st.name = 'Habitation'");
//        
//        query.addEntity(DBExtraService.class);
//        Collection<DBExtraService> retList = query.list();
//        
//        return retList;
//    }

//    /**
//     * Liefert einen spezifischen Extraservice aus
//     * @param name
//     * Der Service nach dem gesucht wird.
//     * @return
//     * Den Extraservice mit dem angegebenen Namen
//     * @throws HibernateException 
//     * Wirft einen Fehler, wenn etwas bei der Transaktion fehllschlaegt
//     */
//    public static DBExtraService getExtraServiceByName(String name) throws HibernateException
//    {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction ts = session.beginTransaction();
//        ts.begin();
//        DBExtraService extraService = (DBExtraService) session.createCriteria(DBExtraService.class).add(Restrictions.eq("name", name)).uniqueResult();
//        return extraService;
//    }

//    /**
//     * Gibt alle Extraservices aus
//     * @return
//     * Alle Extraservices, die verfuegbar sind
//     * @throws HibernateException 
//     * Wirft einen Fehler, wenn etwas mit der Transaktion fehlschlaegt
//     */
//    public static Set<DBExtraService> getAllExtraServices() throws HibernateException
//    {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction ts = session.beginTransaction();
//        ts.begin();
//        List<DBExtraService> extraServices = (List<DBExtraService>) session.createCriteria(DBExtraService.class).list();
//        return new LinkedHashSet<DBExtraService>(extraServices);
//    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Extraservices[ id=" + getIdServices() + " ]";
    }
}
