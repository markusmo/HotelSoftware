/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

import hotelsoftware.model.domain.service.IExtraService;

/**
 *
 * @author Dunst
 */
public class ReservedExtraServices
{
    private ReservedExtraServicesPK reservedExtraServicesPK;
    private IReservationItem reservationItem;
    private IExtraService extraService;
    private int amount;
    
    public IReservationItem getReservationItem()
    {
        return reservationItem;
    }

    public void setReservationItem(IReservationItem dBReservationItem)
    {
        this.reservationItem = dBReservationItem;
    }

    public IExtraService getExtraService()
    {
        return extraService;
    }

    public void setExtraService(IExtraService dBExtraService)
    {
        this.extraService = dBExtraService;
    }
    
    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public ReservedExtraServicesPK getReservedExtraServicesPK()
    {
        return reservedExtraServicesPK;
    }

    public void setReservedExtraServicesPK(ReservedExtraServicesPK reservedExtraServicesPK)
    {
        this.reservedExtraServicesPK = reservedExtraServicesPK;
    }
}
