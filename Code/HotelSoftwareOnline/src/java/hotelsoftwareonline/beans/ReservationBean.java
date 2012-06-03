/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.beans;

import java.io.Serializable;
import hotelsoftware.model.domain.room.IRoomCategory;
import hotelsoftwareonline.controller.ReservationController;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
@ManagedBean(name = "reservation")
@SessionScoped
public class ReservationBean implements Serializable
{
    private String startDate;
    private String endDate;
    private String commentary;

    public String getCommentary()
    {
        return commentary;
    }

    public void setCommentary(String commentary)
    {
        this.commentary = commentary;
    }
    
    public void next() {
        //TODO next screen/step
    }
    
    private ArrayList<ReservationItemBean> items;
    private ReservationController rc = new ReservationController();

    public ReservationBean()
    {
        items = new ArrayList<ReservationItemBean>();
        items.add(new ReservationItemBean());
    }

    //TODO implement get all free Categorys
    public ArrayList<String> getAllFreeRoomCategories()
    {
        if (startDate.equals("") || endDate.equals(""))
        {
            return null;
        }
        ArrayList<String> list = new ArrayList<String>();
        for (IRoomCategory cat : ReservationController.getFreeCategories(convertToDate(startDate), convertToDate(endDate)))
        {
            list.add(cat.getName());
        }
        return list;
    }

    //TODO implement
    public ArrayList<String> getAllBoardCategorys()
    {
        return null;
    }

    //TODO implement
    public ArrayList<String> getAllExtraServices()
    {
        return null;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        System.out.println(endDate.toString());
        this.endDate = endDate;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        System.out.println(startDate.toString());
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
     *
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

    /**
     * Speichert
     *
     * @return
     */
    public String safeReservation()
    {

        return "reservationsuccess";
    }

    private Date convertToDate(String date)
    {
        String[] dates = date.split("/");
        Date d = new Date();
        Calendar c = Calendar.getInstance();
        c.set(Integer.parseInt(dates[2]), Integer.parseInt(dates[0]), Integer.parseInt(dates[1]));
        d.setTime(c.getTimeInMillis());
        return d;
    }
}
