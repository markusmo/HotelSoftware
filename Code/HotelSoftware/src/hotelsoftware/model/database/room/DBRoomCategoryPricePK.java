/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.room;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author mohi
 */
@Embeddable
public class DBRoomCategoryPricePK implements Serializable
{
    @Basic(optional = false)
    @Column(name = "idRoomCategories", nullable = false)
    private int idRoomCategories;
    @Basic(optional = false)
    @Column(name = "idSeasons", nullable = false)
    private int idSeasons;

    public DBRoomCategoryPricePK()
    {
    }

    public DBRoomCategoryPricePK(int idRoomCategories, int idSeasons)
    {
        this.idRoomCategories = idRoomCategories;
        this.idSeasons = idSeasons;
    }

    public int getIdRoomCategories()
    {
        return idRoomCategories;
    }

    public void setIdRoomCategories(int idRoomCategories)
    {
        this.idRoomCategories = idRoomCategories;
    }

    public int getIdSeasons()
    {
        return idSeasons;
    }

    public void setIdSeasons(int idSeasons)
    {
        this.idSeasons = idSeasons;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) idRoomCategories;
        hash += (int) idSeasons;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof DBRoomCategoryPricePK))
        {
            return false;
        }
        DBRoomCategoryPricePK other = (DBRoomCategoryPricePK) object;
        if(this.idRoomCategories != other.idRoomCategories)
        {
            return false;
        }
        if(this.idSeasons != other.idSeasons)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.RoomcategorypricesPK[ idRoomCategories=" + idRoomCategories + ", idSeasons=" + idSeasons + " ]";
    }
}
