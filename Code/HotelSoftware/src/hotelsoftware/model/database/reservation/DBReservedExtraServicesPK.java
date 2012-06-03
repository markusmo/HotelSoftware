/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.reservation;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Dunst
 */
@Embeddable
public class DBReservedExtraServicesPK implements Serializable
{
    @Basic(optional = false)
    @Column(name = "extraServices_idServices")
    private int extraServicesidServices;
    @Basic(optional = false)
    @Column(name = "reservationItems_idReservations")
    private int reservationItemsidReservations;
    @Basic(optional = false)
    @Column(name = "reservationItems_idRoomCategories")
    private int reservationItemsidRoomCategories;

    public DBReservedExtraServicesPK()
    {
    }

    public DBReservedExtraServicesPK(int extraServicesidServices, int reservationItemsidReservations, int reservationItemsidRoomCategories)
    {
        this.extraServicesidServices = extraServicesidServices;
        this.reservationItemsidReservations = reservationItemsidReservations;
        this.reservationItemsidRoomCategories = reservationItemsidRoomCategories;
    }

    public int getExtraServicesidServices()
    {
        return extraServicesidServices;
    }

    public void setExtraServicesidServices(int extraServicesidServices)
    {
        this.extraServicesidServices = extraServicesidServices;
    }

    public int getReservationItemsidReservations()
    {
        return reservationItemsidReservations;
    }

    public void setReservationItemsidReservations(int reservationItemsidReservations)
    {
        this.reservationItemsidReservations = reservationItemsidReservations;
    }

    public int getReservationItemsidRoomCategories()
    {
        return reservationItemsidRoomCategories;
    }

    public void setReservationItemsidRoomCategories(int reservationItemsidRoomCategories)
    {
        this.reservationItemsidRoomCategories = reservationItemsidRoomCategories;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) extraServicesidServices;
        hash += (int) reservationItemsidReservations;
        hash += (int) reservationItemsidRoomCategories;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DBReservedExtraServicesPK))
        {
            return false;
        }
        DBReservedExtraServicesPK other = (DBReservedExtraServicesPK) object;
        if (this.extraServicesidServices != other.extraServicesidServices)
        {
            return false;
        }
        if (this.reservationItemsidReservations != other.reservationItemsidReservations)
        {
            return false;
        }
        if (this.reservationItemsidRoomCategories != other.reservationItemsidRoomCategories)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.model.database.reservation.ReservedextraservicesPK[ extraServicesidServices=" + extraServicesidServices + ", reservationItemsidReservations=" + reservationItemsidReservations + ", reservationItemsidRoomCategories=" + reservationItemsidRoomCategories + " ]";
    }
    
}
