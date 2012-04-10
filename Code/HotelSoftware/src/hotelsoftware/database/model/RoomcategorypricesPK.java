/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.database.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author mohi
 */
@Embeddable
public class RoomcategorypricesPK implements Serializable
{
    @Basic(optional = false)
    @Column(name = "idRoomCategories", nullable = false)
    private int idRoomCategories;
    @Basic(optional = false)
    @Column(name = "idSeason", nullable = false)
    private int idSeason;

    public RoomcategorypricesPK()
    {
    }

    public RoomcategorypricesPK(int idRoomCategories, int idSeason)
    {
        this.idRoomCategories = idRoomCategories;
        this.idSeason = idSeason;
    }

    public int getIdRoomCategories()
    {
        return idRoomCategories;
    }

    public void setIdRoomCategories(int idRoomCategories)
    {
        this.idRoomCategories = idRoomCategories;
    }

    public int getIdSeason()
    {
        return idSeason;
    }

    public void setIdSeason(int idSeason)
    {
        this.idSeason = idSeason;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) idRoomCategories;
        hash += (int) idSeason;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof RoomcategorypricesPK))
        {
            return false;
        }
        RoomcategorypricesPK other = (RoomcategorypricesPK) object;
        if(this.idRoomCategories != other.idRoomCategories)
        {
            return false;
        }
        if(this.idSeason != other.idSeason)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.RoomcategorypricesPK[ idRoomCategories=" + idRoomCategories + ", idSeason=" + idSeason + " ]";
    }
    
}
