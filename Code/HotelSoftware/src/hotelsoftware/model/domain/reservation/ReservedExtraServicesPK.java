/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

/**
 *
 * @author Dunst
 */
public class ReservedExtraServicesPK
{
    private int extraServicesidServices;
    private int reservationItemsidReservations;
    private int reservationItemsidRoomCategories;

    public ReservedExtraServicesPK()
    {
    }

    public int getExtraServicesidServices()
    {
        return extraServicesidServices;
    }

    public void setExtraServicesidServices(int extraServicesidServices)
    {
        this.extraServicesidServices = extraServicesidServices;
    }

    public int getReservationItemsidReservations()
    {
        return reservationItemsidReservations;
    }

    public void setReservationItemsidReservations(int reservationItemsidReservations)
    {
        this.reservationItemsidReservations = reservationItemsidReservations;
    }

    public int getReservationItemsidRoomCategories()
    {
        return reservationItemsidRoomCategories;
    }

    public void setReservationItemsidRoomCategories(int reservationItemsidRoomCategories)
    {
        this.reservationItemsidRoomCategories = reservationItemsidRoomCategories;
    }
}
