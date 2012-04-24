/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

/**
 *
 * @author Johannes
 */
public class RoomCategoryPricePK
{
    
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
