package hotelsoftware.model.database.room;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Diese Klasse bildet den aktuellen Status eines Zimmers historisiert ab.
 * @author mohi
 */
@Entity
@Table(name = "roomsroomstatus", catalog = "`roomanizer-dev`", schema = "")
@XmlRootElement
public class DBRoomsRoomStatus implements Serializable
{
    @Basic(optional = false)
    @Column(name = "startDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date start;
    
    @Basic(optional = true)
    @Column(name = "endDate", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date end;
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    protected Integer id;
    
    @JoinColumn(name = "idRoomStatus", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DBRoomStatus roomstatus;
    
    @JoinColumn(name = "idRooms", referencedColumnName = "id")
    @ManyToOne(fetch= FetchType.EAGER)
    private DBRoom room;

    public DBRoomsRoomStatus()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setRoomsroomstatusPK(Integer id)
    {
        this.id = id;
    }

    public DBRoomStatus getRoomstatus()
    {
        return roomstatus;
    }

    public void setRoomstatus(DBRoomStatus roomstatus)
    {
        this.roomstatus = roomstatus;
    }

    public DBRoom getRoom()
    {
        return room;
    }

    public void setRoom(DBRoom rooms)
    {
        this.room = rooms;
    }
  
    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Roomsroomstatus[ id=" + id + " ]";
    }

    public Date getStart()
    {
        return start;
    }

    public void setStart(Date start)
    {
        this.start = start;
    }

    public Date getEnd()
    {
        return end;
    }

    public void setEnd(Date end)
    {
        this.end = end;
    }
    
}
