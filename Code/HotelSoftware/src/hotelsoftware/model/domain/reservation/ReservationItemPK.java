/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

/**
 *Diese Klasse dient als Primärschlüssel für die ReservierungsItems
 * @author Johannes
 */
public class ReservationItemPK
{
    private Integer idReservations;
    private Integer idRoomCategories;

    public ReservationItemPK()
    {
    }

    public Integer getIdReservations()
    {
        return idReservations;
    }

    public void setIdReservations(int idReservations)
    {
        this.idReservations = idReservations;
    }

    public Integer getIdRoomCategories()
    {
        return idRoomCategories;
    }

    public void setIdRoomCategories(int idRoomCategories)
    {
        this.idRoomCategories = idRoomCategories;
    }
    
    
}
