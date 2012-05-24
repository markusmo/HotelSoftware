/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.beans;

import java.util.Date;
import java.util.LinkedList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
@ManagedBean(name = "reservation")
@NoneScoped
public class ReservationBean
{
    private Date startDate;
    private Date endDate;
    private String comment;
    private Date created;
    private IGuest contactperson;
    private LinkedList<IGuest> guests;
    private LinkedList<IReservationItem> items;
    public ReservationBean()
    {
    }
}
