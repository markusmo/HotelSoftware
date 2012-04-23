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
    
    private Integer idSeason;

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

    public Integer getIdSeason()
    {
        return idSeason;
    }

    public void setIdSeason(int idSeason)
    {
        this.idSeason = idSeason;
    }
    
}
