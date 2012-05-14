package hotelsoftware.model.domain.room;

/**
 * Diese Klasse identifiziert einen Zimmerkategoriepreis eindeutig.
 * @author Johannes
 */
public class RoomCategoryPricePK implements IRoomCategoryPricePK
{
    //primary keys for categorie and season
    private Integer idRoomCategories;    
    private Integer idSeasons;

    public RoomCategoryPricePK()
    {
    }

    @Override
    public Integer getIdRoomCategories()
    {
        return idRoomCategories;
    }

    @Override
    public void setIdRoomCategories(int idRoomCategories)
    {
        this.idRoomCategories = idRoomCategories;
    }

    @Override
    public Integer getIdSeasons()
    {
        return idSeasons;
    }

    @Override
    public void setIdSeasons(int idSeason)
    {
        this.idSeasons = idSeason;
    }
    
}
