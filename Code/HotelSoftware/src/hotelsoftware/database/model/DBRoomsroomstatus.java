/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.database.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mohi
 */
@Entity
@Table(name = "roomsroomstatus", catalog = "roomanizer", schema = "")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Roomsroomstatus.findAll", query = "SELECT r FROM Roomsroomstatus r"),
    @NamedQuery(name = "Roomsroomstatus.findByIdRooms", query = "SELECT r FROM Roomsroomstatus r WHERE r.roomsroomstatusPK.idRooms = :idRooms"),
    @NamedQuery(name = "Roomsroomstatus.findByIdRoomStatus", query = "SELECT r FROM Roomsroomstatus r WHERE r.roomsroomstatusPK.idRoomStatus = :idRoomStatus"),
    @NamedQuery(name = "Roomsroomstatus.findByStart", query = "SELECT r FROM Roomsroomstatus r WHERE r.start = :start"),
    @NamedQuery(name = "Roomsroomstatus.findByEnd", query = "SELECT r FROM Roomsroomstatus r WHERE r.end = :end")
})
public class DBRoomsroomstatus implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DBRoomsroomstatusPK roomsroomstatusPK;
    @Basic(optional = false)
    @Column(name = "start", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date start;
    @Basic(optional = false)
    @Column(name = "end", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date end;
    @JoinColumn(name = "idRoomStatus", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DBRoomstatus roomstatus;
    @JoinColumn(name = "idRooms", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DBRooms rooms;

    public DBRoomsroomstatus()
    {
    }

    public DBRoomsroomstatus(DBRoomsroomstatusPK roomsroomstatusPK)
    {
        this.roomsroomstatusPK = roomsroomstatusPK;
    }

    public DBRoomsroomstatus(DBRoomsroomstatusPK roomsroomstatusPK, Date start, Date end)
    {
        this.roomsroomstatusPK = roomsroomstatusPK;
        this.start = start;
        this.end = end;
    }

    public DBRoomsroomstatus(int idRooms, int idRoomStatus)
    {
        this.roomsroomstatusPK = new DBRoomsroomstatusPK(idRooms, idRoomStatus);
    }

    public DBRoomsroomstatusPK getRoomsroomstatusPK()
    {
        return roomsroomstatusPK;
    }

    public void setRoomsroomstatusPK(DBRoomsroomstatusPK roomsroomstatusPK)
    {
        this.roomsroomstatusPK = roomsroomstatusPK;
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

    public DBRoomstatus getRoomstatus()
    {
        return roomstatus;
    }

    public void setRoomstatus(DBRoomstatus roomstatus)
    {
        this.roomstatus = roomstatus;
    }

    public DBRooms getRooms()
    {
        return rooms;
    }

    public void setRooms(DBRooms rooms)
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
        if(!(object instanceof DBRoomsroomstatus))
        {
            return false;
        }
        DBRoomsroomstatus other = (DBRoomsroomstatus) object;
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
    
}
