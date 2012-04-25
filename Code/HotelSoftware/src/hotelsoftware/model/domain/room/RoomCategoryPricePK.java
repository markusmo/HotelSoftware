package hotelsoftware.model.domain.room;

/**
 * Diese Klasse identifiziert einen Zimmerkategoriepreis eindeutig.
 * @author Johannes
 */
public class RoomCategoryPricePK
{
    //primary keys for categorie and season
    private Integer idRoomCategories;    
    private Integer idSeasons;

    public RoomCategoryPricePK()
    {
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

    public void setIdSeasons(int idSeason)
    {
        this.idSeasons = idSeason;
    }
    
}
