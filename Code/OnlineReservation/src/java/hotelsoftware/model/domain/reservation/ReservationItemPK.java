/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

/**
 *Diese Klasse dient als Primärschlüssel für die ReservierungsItems
 * @author Johannes
 */
public class ReservationItemPK implements IReservationItemPK
{
    private Integer idReservations;
    private Integer idRoomCategories;

    public ReservationItemPK()
    {
    }

    @Override
    public Integer getIdReservations()
    {
        return idReservations;
    }

    @Override
    public void setIdReservations(int idReservations)
    {
        this.idReservations = idReservations;
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
    
    
}
