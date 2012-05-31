/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.beans;

import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
@ManagedBean(name = "reservation")
@SessionScoped
public class ReservationBean
{
    private Date startDate;
    private Date endDate;
    private ArrayList<ReservationItemBean> items;
    
    public ReservationBean()
    {
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public ArrayList<ReservationItemBean> getItems()
    {
        return items;
    }

    public void setItems(ArrayList<ReservationItemBean> items)
    {
        this.items = items;
    }

    /**
     * Adds a reservationitem to the current reservation
     * For example: new double room
     * @param item the item to be added
     */
    public void addReservationItem(ReservationItemBean item)
    {
        if (this.items == null)
        {
            this.items = new ArrayList<ReservationItemBean>();
        }
        
        this.items.add(item);
    }
    
    //TODO implement get all free Categorys
    public ArrayList<String> getAllFreeRoomCategorys() {
        return null;
    }
    
    //TODO implement
    public ArrayList<String> getAllBoardCategorys() {
        return null;
    }
    
    //TODO implement
    public ArrayList<String> getAllExtraServices() {
        return null;
    }
    
    /**
     * Speichert 
     * @return 
     */
    public String safeReservation()
    {
        
        return "reservationsuccess";
    }
}
