/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.room;

import hotelsoftware.util.HibernateUtil;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "roomoptions", catalog = "roomanizer", schema = "", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "name"
    })
})
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Roomoptions.findAll", query = "SELECT r FROM Roomoptions r"),
    @NamedQuery(name = "Roomoptions.findById", query = "SELECT r FROM Roomoptions r WHERE r.id = :id"),
    @NamedQuery(name = "Roomoptions.findByName", query = "SELECT r FROM Roomoptions r WHERE r.name = :name")
})
public class DBRoomOption implements Serializable
{
    private static final long serialVersionUID = 1L;

    public static void safeNewRoomOption(String name)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void getRoomoptions()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    public DBRoomOption()
    {
    }

    public DBRoomOption(Integer id)
    {
        this.id = id;
    }

    public DBRoomOption(Integer id, String name)
    {
        this.id = id;
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
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof DBRoomOption))
        {
            return false;
        }
        DBRoomOption other = (DBRoomOption) object;
        if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Roomoptions[ id=" + id + " ]";
    }
    
    public static Collection<DBRoomOption> getRoomOptions()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction ts = session.beginTransaction();
        ts.begin();
        
        Collection<DBRoomOption> options = session.createCriteria(DBRoomOption.class).list();
        
        session.close();
        
        return options;
    }
}
