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
    protected DBReservedExtraServicesPK reservedExtraServicesPK;
    @Basic(optional = false)
    @Column(name = "amount")
    private int amount;
    @JoinColumns(
    {
        @JoinColumn(name = "idReservations", referencedColumnName = "idReservations", insertable = false, updatable = false),
        @JoinColumn(name = "idRoomCategories", referencedColumnName = "idRoomCategories", insertable = false, updatable = false)
    })
    @ManyToOne(optional = false)
    private DBReservationItem reservationItem;
    @JoinColumn(name = "idServices", referencedColumnName = "idServices", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DBExtraService extraService;

    public DBReservedExtraServices()
    {
    }

    public DBReservedExtraServicesPK getReservedExtraServicesPK()
    {
        return reservedExtraServicesPK;
    }

    public void setReservedExtraServicesPK(DBReservedExtraServicesPK reservedextraservicesPK)
    {
        this.reservedExtraServicesPK = reservedextraservicesPK;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public DBReservationItem getReservationItem()
    {
        return reservationItem;
    }

    public void setReservationItem(DBReservationItem dBReservationItem)
    {
        this.reservationItem = dBReservationItem;
    }

    public DBExtraService getExtraService()
    {
        return extraService;
    }

    public void setExtraService(DBExtraService dBExtraService)
    {
        this.extraService = dBExtraService;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (reservedExtraServicesPK != null ? reservedExtraServicesPK.hashCode() : 0);
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
        if ((this.reservedExtraServicesPK == null && other.reservedExtraServicesPK != null) || (this.reservedExtraServicesPK != null && !this.reservedExtraServicesPK.equals(other.reservedExtraServicesPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.model.database.reservation.Reservedextraservices[ reservedextraservicesPK=" + reservedExtraServicesPK + " ]";
    }
    
}
