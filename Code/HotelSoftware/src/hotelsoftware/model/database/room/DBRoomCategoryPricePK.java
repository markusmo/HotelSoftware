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
    public String toString()
    {
        return "hotelsoftware.database.model.RoomcategorypricesPK[ idRoomCategories=" + idRoomCategories + ", idSeasons=" + idSeasons + " ]";
    }
}
