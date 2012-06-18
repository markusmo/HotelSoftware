/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.beans;

import hotelsoftware.model.database.manager.RoomManager;
import hotelsoftware.model.database.manager.ServiceManager;
import hotelsoftware.model.domain.reservation.*;
import hotelsoftware.model.domain.room.IRoomCategory;
import hotelsoftware.model.domain.room.RoomCategory;
import hotelsoftware.support.NoPriceDefinedException;
import hotelsoftware.support.ServiceNotFoundException;
import hotelsoftwareonline.controller.ReservationController;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class ReservationItemBean implements Serializable
{
    private String category;
    private int amount = 1;
    private ArrayList<String> extraServices;
    private String boardCategory;

    public ArrayList<String> getExtraServices()
    {
        return extraServices;
    }

    public void setExtraServices(ArrayList<String> extraServices)
    {
        this.extraServices = extraServices;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public String getBoardCategory()
    {
        return boardCategory;
    }

    public void setBoardCategory(String boardCategory)
    {
        this.boardCategory = boardCategory;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    /**
     * Gibt den Preis für einen Extraservice an, dessen Name übergeben wird
     *
     * @param service der Name des Extraservice
     * @return der Preis als String.
     */
    public String getPriceForExtraService(String service)
    {
        try
        {
            DecimalFormat currencyFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.GERMANY);
            DecimalFormatSymbols symbols = currencyFormat.getDecimalFormatSymbols();
            symbols.setGroupingSeparator(' ');
            symbols.setDecimalSeparator(',');
            currencyFormat.setMinimumFractionDigits(2);
            currencyFormat.setDecimalFormatSymbols(symbols);

            ReservationController controller = new ReservationController();

            double price = controller.getTotalPriceForExtraService(service);

            return currencyFormat.format(price);
        }
        catch (ServiceNotFoundException ex)
        {
            Logger.getLogger(ReservationItemBean.class.getName()).log(Level.SEVERE, null, ex);
            return "price not found";
        }
    }

    /**
     * Gibt den Preis für das ganze Reservierungsposition aus
     *
     * @return Preis = Verpflegungsart + Kategorie + Extraservices
     */
    public double getPriceOfReservationItem()
    {
        try
        {
            ReservationController controller = new ReservationController();
            double price = 0;

            price += controller.getTotalPriceForExtraService(this.extraServices);
            price += controller.getTotalPriceForExtraService(this.boardCategory);
            price += controller.getPriceForCategory(this.category);

            return price;
        }
        catch (NoPriceDefinedException ex)
        {
            Logger.getLogger(ReservationItemBean.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        catch (ServiceNotFoundException ex)
        {
            Logger.getLogger(ReservationItemBean.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    /**
     * gibt den Preis für eine Zimmerkategorie aus
     *
     * @return Preis als String.
     */
    public String getPriceForCategory()
    {
        try
        {
            DecimalFormat currencyFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.GERMANY);
            DecimalFormatSymbols symbols = currencyFormat.getDecimalFormatSymbols();
            symbols.setGroupingSeparator(' ');
            symbols.setDecimalSeparator(',');
            currencyFormat.setMinimumFractionDigits(2);
            currencyFormat.setDecimalFormatSymbols(symbols);

            ReservationController controller = new ReservationController();
            double price = controller.getPriceForCategory(this.category);

            return currencyFormat.format(price);
        }
        catch (NoPriceDefinedException ex)
        {
            Logger.getLogger(ReservationItemBean.class.getName()).log(Level.SEVERE, null, ex);
            return "price not found";
        }
    }

    /**
     * gibt den Preis für alle die Extraservices aus
     *
     * @return Preis der Extraservices als String
     */
    public String getPriceForExtraServices()
    {
        try
        {
            DecimalFormat currencyFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.GERMANY);
            DecimalFormatSymbols symbols = currencyFormat.getDecimalFormatSymbols();
            symbols.setGroupingSeparator(' ');
            symbols.setDecimalSeparator(',');
            currencyFormat.setMinimumFractionDigits(2);
            currencyFormat.setDecimalFormatSymbols(symbols);

            ReservationController controller = new ReservationController();

            double price = controller.getTotalPriceForExtraService(this.extraServices);

            return currencyFormat.format(price);
        }
        catch (ServiceNotFoundException ex)
        {
            Logger.getLogger(ReservationItemBean.class.getName()).log(Level.SEVERE, null, ex);
            return "price not found";
        }
    }

    /**
     * gibt den Preis für die Verpflegungsart aus
     *
     * @return Preis für die Verpflegungsart
     */
    public String getPriceForBoardCategory()
    {
        try
        {
            DecimalFormat currencyFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.GERMANY);
            DecimalFormatSymbols symbols = currencyFormat.getDecimalFormatSymbols();
            symbols.setGroupingSeparator(' ');
            symbols.setDecimalSeparator(',');
            currencyFormat.setMinimumFractionDigits(2);
            currencyFormat.setDecimalFormatSymbols(symbols);

            ReservationController controller = new ReservationController();

            double price = controller.getTotalPriceForExtraService(this.boardCategory);

            return currencyFormat.format(price);
        }
        catch (ServiceNotFoundException ex)
        {
            Logger.getLogger(ReservationItemBean.class.getName()).log(Level.SEVERE, null, ex);
            return "price not found";
        }
    }

    /**
     * Gibt ein Domänen Objekt aus
     *
     * @param reservation die Referenz auf die Reservierung auf die sich die Reservierunsposition bezieht
     * @return eine Reservierungsposition mit allen Feldern gefüllt
     */
    public IReservationItem getReservationItem(IReservation reservation)
    {
        IReservationItem reservationItem = new ReservationItem();

        reservationItem.setAmount(this.amount);
        reservationItem.setReservation(reservation);
        reservationItem.setReservationitemsPK(new ReservationItemPK());
        IRoomCategory roomcategory = RoomManager.getInstance().getCategoryByName(category);
        reservationItem.setRoomCategory(roomcategory);
        reservationItem.getReservationitemsPK().setIdRoomCategories(reservationItem.getRoomCategory().getId());

        LinkedList<ReservedExtraServices> services = new LinkedList<ReservedExtraServices>();

        for (String service : this.getExtraServices())
        {
            ReservedExtraServices resService = new ReservedExtraServices();
            resService.setAmount(1); //TODO eventuell Dauer?
            resService.setReservationItem(reservationItem);
            try
            {
                resService.setExtraService(ServiceManager.getInstance().getExtraServiceByName(
                        service));
            }
            catch (ServiceNotFoundException ex)
            {
                //Ignorieren, wurde zuerst aus der DB gelesen, muss also eigentlich vorhanden sein
                Logger.getLogger(ReservationBean.class.getName()).log(
                        Level.SEVERE, null, ex);
            }

            services.add(resService);
        }
        reservationItem.setReservedExtraServices(services);

        return reservationItem;
    }
}
