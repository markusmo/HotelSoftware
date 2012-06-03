/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.reservation;

import hotelsoftware.model.database.service.DBExtraService;
import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dunst
 */
@Entity
@Table(name = "reservedextraservices")
@XmlRootElement
public class DBReservedExtraServices implements Serializable
{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DBReservedExtraServicesPK reservedextraservicesPK;
    @Basic(optional = false)
    @Column(name = "amount")
    private int amount;
    @JoinColumns(
    {
        @JoinColumn(name = "reservationItems_idReservations", referencedColumnName = "idReservations", insertable = false, updatable = false),
        @JoinColumn(name = "reservationItems_idRoomCategories", referencedColumnName = "idRoomCategories", insertable = false, updatable = false)
    })
    @ManyToOne(optional = false)
    private DBReservationItem dBReservationItem;
    @JoinColumn(name = "extraServices_idServices", referencedColumnName = "idServices", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DBExtraService dBExtraService;

    public DBReservedExtraServices()
    {
    }

    public DBReservedExtraServices(DBReservedExtraServicesPK reservedextraservicesPK)
    {
        this.reservedextraservicesPK = reservedextraservicesPK;
    }

    public DBReservedExtraServices(DBReservedExtraServicesPK reservedextraservicesPK, int amount)
    {
        this.reservedextraservicesPK = reservedextraservicesPK;
        this.amount = amount;
    }

    public DBReservedExtraServices(int extraServicesidServices, int reservationItemsidReservations, int reservationItemsidRoomCategories)
    {
        this.reservedextraservicesPK = new DBReservedExtraServicesPK(extraServicesidServices, reservationItemsidReservations, reservationItemsidRoomCategories);
    }

    public DBReservedExtraServicesPK getReservedextraservicesPK()
    {
        return reservedextraservicesPK;
    }

    public void setReservedextraservicesPK(DBReservedExtraServicesPK reservedextraservicesPK)
    {
        this.reservedextraservicesPK = reservedextraservicesPK;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public DBReservationItem getDBReservationItem()
    {
        return dBReservationItem;
    }

    public void setDBReservationItem(DBReservationItem dBReservationItem)
    {
        this.dBReservationItem = dBReservationItem;
    }

    public DBExtraService getDBExtraService()
    {
        return dBExtraService;
    }

    public void setDBExtraService(DBExtraService dBExtraService)
    {
        this.dBExtraService = dBExtraService;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (reservedextraservicesPK != null ? reservedextraservicesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DBReservedExtraServices))
        {
            return false;
        }
        DBReservedExtraServices other = (DBReservedExtraServices) object;
        if ((this.reservedextraservicesPK == null && other.reservedextraservicesPK != null) || (this.reservedextraservicesPK != null && !this.reservedextraservicesPK.equals(other.reservedextraservicesPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.model.database.reservation.Reservedextraservices[ reservedextraservicesPK=" + reservedextraservicesPK + " ]";
    }
    
}
