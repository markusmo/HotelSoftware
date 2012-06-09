/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.beans;

import hotelsoftware.model.database.manager.PartyManager;
import hotelsoftware.model.database.manager.RoomManager;
import hotelsoftware.model.database.manager.ServiceManager;
import hotelsoftware.model.domain.reservation.*;
import hotelsoftware.model.domain.room.IRoomCategory;
import hotelsoftware.support.ServiceNotFoundException;
import hotelsoftware.util.HelperFunctions;
import hotelsoftwareonline.controller.ReservationController;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
@ManagedBean(name = "reservation")
@SessionScoped
public class ReservationBean implements Serializable
{
    private String startDate = "";
    private String endDate = "";
    private String commentary = "";
    private ArrayList<ReservationItemBean> items;
    private ReservationController rc = new ReservationController();
    private String changeInvoiceAddress;
    private String overview;
    private String finish;

    public ReservationBean()
    {
        items = new ArrayList<ReservationItemBean>();
        ReservationItemBean item = new ReservationItemBean();
        items.add(item);
    }

    public String getCommentary()
    {
        return commentary;
    }

    public void setCommentary(String commentary)
    {
        this.commentary = commentary;
    }

    /**
     * Actionlistener für nächsten Schritt -> Rechnungsadresse ändern
     *
     * @param event
     */
    public void addressChangeListener(ActionEvent event)
    {
        this.changeInvoiceAddress = event.getComponent().getClientId();
    }

    /**
     * Zum nächsten Schritt -> Rechungsadresse ändern
     *
     * @return
     * <code>"changeInvoiceAddress"</code>
     */
    public String addressChange()
    {
        return "changeInvoiceAddress";
    }

    /**
     * Actionlistener für nächsten Schritt -> zum Überblick
     *
     * @param event
     */
    public void overviewListener(ActionEvent event)
    {
        this.overview = event.getComponent().getClientId();
    }

    /**
     * Zum nächsten Schritt -> zum Overview
     *
     * @return
     * <code>"toOverview"</code>
     */
    public String toOverview()
    {
        return "toOverview";
    }

    /**
     * Actionlistener für nächsten Schritt -> zum index.html
     *
     * @param event
     */
    public void finishListener(ActionEvent event)
    {
        this.finish = event.getComponent().getClientId();
    }

    /**
     * Zum nächsten Schritt -> fertig, zurück zu index.html
     *
     * @return
     */
    public String finishReservation()
    {
        IReservation reservation = new Reservation();
        reservation.setComment(commentary);
        reservation.setCreated(new Date());
        reservation.setEndDate(convertToDate(endDate));

        FacesContext context = FacesContext.getCurrentInstance();
        LoginBean bean = (LoginBean) context.getApplication().evaluateExpressionGet(context, "#{login}", LoginBean.class);

        reservation.setParty(PartyManager.getInstance().getCustomerById(bean.getCustomer().getId()));
        reservation.setReservationNumber(HelperFunctions.getNewContinousNumber(Reservation.class));

        LinkedList<IReservationItem> resItems = new LinkedList<IReservationItem>();
        for (ReservationItemBean item : this.getItems())
        {
            IReservationItem resItem = new ReservationItem();
            resItem.setAmount(item.getAmount());
            resItem.setReservation(reservation);
            resItem.setRoomCategory(RoomManager.getInstance().getCategoryByName(item.getCategory()));

            LinkedList<ReservedExtraServices> services = new LinkedList<ReservedExtraServices>();

            for (String service : item.getExtraServices())
            {
                ReservedExtraServices resService = new ReservedExtraServices();
                resService.setAmount(1); //TODO eventuell Dauer?
                resService.setReservationItem(resItem);
                try
                {
                    resService.setExtraService(ServiceManager.getInstance().getExtraServiceByName(service));
                }
                catch (ServiceNotFoundException ex)
                {
                    //Ignorieren, wurde zuerst aus der DB gelesen, muss also eigentlich vorhanden sein
                    Logger.getLogger(ReservationBean.class.getName()).log(Level.SEVERE, null, ex);
                }

                services.add(resService);
            }

            ReservedExtraServices boardCategory = new ReservedExtraServices();
            boardCategory.setAmount(1); //TODO eventuell Dauer?
            boardCategory.setReservationItem(resItem);
            try
            {
                boardCategory.setExtraService(ServiceManager.getInstance().getExtraServiceByName(item.getBoardCategory()));
            }
            catch (ServiceNotFoundException ex)
            {
                //Ignorieren, wurde zuerst aus der DB gelesen, muss also eigentlich vorhanden sein
                Logger.getLogger(ReservationBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            services.add(boardCategory);
            resItem.setReservedExtraServices(services);
            
            resItem.setReservationitemsPK(new ReservationItemPK());
            resItem.getReservationitemsPK().setIdRoomCategories(resItem.getRoomCategory().getId());
            
            resItems.add(resItem);
        }

        reservation.setReservationItems(resItems);
        reservation.setStartDate(convertToDate(startDate));

        ReservationController.saveReservation(reservation);

        return "finishedReservation";
    }

    public ArrayList<String> getAllFreeRoomCategories()
    {
        if (startDate == null || endDate == null || startDate.equals("") || endDate.equals(""))
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

    public ArrayList<String> getAllBoardCategories()
    {
        return ReservationController.getBoardCategories();
    }
    
    /**
     * Gibt den totalen Preis für eine Reservierung aus.
     * @return der Preis der Reservierung als String.
     */
    public String getTotalPrice()
    {
        DecimalFormat currencyFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.GERMANY);
        DecimalFormatSymbols symbols = currencyFormat.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        symbols.setDecimalSeparator(',');
        currencyFormat.setMinimumFractionDigits(2);
        currencyFormat.setDecimalFormatSymbols(symbols);
        
        double price = 0;
        
        for(ReservationItemBean item : this.items)
        {
            price += item.getPriceOfReservationItem();
        }
        
        return currencyFormat.format(price);
    }
    
    public ArrayList<String> getReservableExtraServices()
    {
        return ReservationController.getReservableExtraServices();
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
     * Adds a reservationitem to the current reservation For example: new double
     * room
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

    public void removeReservationItem(ReservationItemBean item)
    {
        if (this.items == null)
        {
            return;
        }
        items.remove(item);
    }

    /**
     * Adds a reservationitem to the current reservation For example: new double
     * room
     *
     * @param item the item to be added
     */
    public void addReservationItem()
    {
        if (this.items == null)
        {
            this.items = new ArrayList<ReservationItemBean>();
        }
        ReservationItemBean item = new ReservationItemBean();
        items.add(item);
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
