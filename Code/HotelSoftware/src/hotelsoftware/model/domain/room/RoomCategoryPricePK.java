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
    
    private int idRoomCategories;
    
    private int idSeason;

    public RoomCategoryPricePK()
    {
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
    
}
