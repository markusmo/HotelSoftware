/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.room;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "roomsroomstatus", catalog = "roomanizer", schema = "")
@XmlRootElement
/*@NamedQueries(
{
    @NamedQuery(name = "Roomsroomstatus.findAll", query = "SELECT r FROM Roomsroomstatus r"),
    @NamedQuery(name = "Roomsroomstatus.findByIdRooms", query = "SELECT r FROM Roomsroomstatus r WHERE r.roomsroomstatusPK.idRooms = :idRooms"),
    @NamedQuery(name = "Roomsroomstatus.findByIdRoomStatus", query = "SELECT r FROM Roomsroomstatus r WHERE r.roomsroomstatusPK.idRoomStatus = :idRoomStatus"),
    @NamedQuery(name = "Roomsroomstatus.findByStart", query = "SELECT r FROM Roomsroomstatus r WHERE r.start = :start"),
    @NamedQuery(name = "Roomsroomstatus.findByEnd", query = "SELECT r FROM Roomsroomstatus r WHERE r.end = :end")
})*/
public class DBRoomsRoomStatus implements Serializable
{
    @Basic(optional = false)
    @Column(name = "start", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date start;
    @Basic(optional = false)
    @Column(name = "end", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date end;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DBRoomsRoomStatusPK roomsroomstatusPK;
    @JoinColumn(name = "idRoomStatus", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DBRoomStatus roomstatus;
    @JoinColumn(name = "idRooms", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(fetch= FetchType.LAZY)
    private DBRoom rooms;

    public DBRoomsRoomStatus()
    {
    }

    public DBRoomsRoomStatus(DBRoomsRoomStatusPK roomsroomstatusPK)
    {
        this.roomsroomstatusPK = roomsroomstatusPK;
    }

    public DBRoomsRoomStatus(DBRoomsRoomStatusPK roomsroomstatusPK, Date start, Date end)
    {
        this.roomsroomstatusPK = roomsroomstatusPK;
        this.start = start;
        this.end = end;
    }

    public DBRoomsRoomStatus(int idRooms, int idRoomStatus)
    {
        this.roomsroomstatusPK = new DBRoomsRoomStatusPK(idRooms, idRoomStatus);
    }

    public DBRoomsRoomStatusPK getRoomsroomstatusPK()
    {
        return roomsroomstatusPK;
    }

    public void setRoomsroomstatusPK(DBRoomsRoomStatusPK roomsroomstatusPK)
    {
        this.roomsroomstatusPK = roomsroomstatusPK;
    }

    public DBRoomStatus getRoomstatus()
    {
        return roomstatus;
    }

    public void setRoomstatus(DBRoomStatus roomstatus)
    {
        this.roomstatus = roomstatus;
    }

    public DBRoom getRooms()
    {
        return rooms;
    }

    public void setRooms(DBRoom rooms)
    {
        this.rooms = rooms;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (roomsroomstatusPK != null ? roomsroomstatusPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof DBRoomsRoomStatus))
        {
            return false;
        }
        DBRoomsRoomStatus other = (DBRoomsRoomStatus) object;
        if((this.roomsroomstatusPK == null && other.roomsroomstatusPK != null) || (this.roomsroomstatusPK != null && !this.roomsroomstatusPK.equals(other.roomsroomstatusPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.Roomsroomstatus[ roomsroomstatusPK=" + roomsroomstatusPK + " ]";
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
