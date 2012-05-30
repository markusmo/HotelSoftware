/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinereservation.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    //TODO implement get all free Categorys
    public List<String> getAllFreeRoomCategorys() {
        return null;
    }
    
    //TODO implement
    public List<String> getAllBoardCategorys() {
        return null;
    }
    
    //TODO implement
    public List<String> getAllExtraServices() {
        return null;
    }
    
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
}
