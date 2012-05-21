package hotelsoftware.model.database.room;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Diese Klasse bildet den Primaerschluessel auf die Zimmerkategoriepreise ab.
 * @author mohi
 */
@Embeddable
public class DBRoomCategoryPricePK implements Serializable
{
    @Basic(optional = false)
    @Column(name = "idRoomCategories", nullable = false)
    private Integer idRoomCategories;
    @Basic(optional = false)
    @Column(name = "idSeasons", nullable = false)
    private Integer idSeasons;

    public DBRoomCategoryPricePK()
    {
    }

    public DBRoomCategoryPricePK(int idRoomCategories, int idSeasons)
    {
        this.idRoomCategories = idRoomCategories;
        this.idSeasons = idSeasons;
    }

    public Integer getIdRoomCategories()
    {
        return idRoomCategories;
    }

    public void setIdRoomCategories(int idRoomCategories)
    {
        this.idRoomCategories = idRoomCategories;
    }

    public Integer getIdSeasons()
    {
        return idSeasons;
    }

    public void setIdSeasons(int idSeasons)
    {
        this.idSeasons = idSeasons;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final DBRoomCategoryPricePK other = (DBRoomCategoryPricePK) obj;
        if (this.idRoomCategories != other.idRoomCategories && (this.idRoomCategories == null || !this.idRoomCategories.equals(other.idRoomCategories)))
        {
            return false;
        }
        if (this.idSeasons != other.idSeasons && (this.idSeasons == null || !this.idSeasons.equals(other.idSeasons)))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 53 * hash + (this.idRoomCategories != null ? this.idRoomCategories.hashCode() : 0);
        hash = 53 * hash + (this.idSeasons != null ? this.idSeasons.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.RoomcategorypricesPK[ idRoomCategories=" + idRoomCategories + ", idSeasons=" + idSeasons + " ]";
    }
}
