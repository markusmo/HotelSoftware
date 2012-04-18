/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

/**
 *
 * @author Johannes
 */
public class ReservationItemPK
{
    private int idReservations;
    private int idRoomCategories;

    public ReservationItemPK()
    {
    }
    
    

    public int getIdReservations()
    {
        return idReservations;
    }

    public void setIdReservations(int idReservations)
    {
        this.idReservations = idReservations;
    }

    public int getIdRoomCategories()
    {
        return idRoomCategories;
    }

    public void setIdRoomCategories(int idRoomCategories)
    {
        this.idRoomCategories = idRoomCategories;
    }
    
    
}
