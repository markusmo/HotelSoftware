/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.service;

import hotelsoftware.model.domain.users.User;
import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.reservation.ReservationItem;
import java.util.Date;

/**
 *
 * @author Tobias
 */
public class HabitationFacade
{
    private HabitationFacade()
    {
    }

    public static HabitationFacade getInstance()
    {
        return HabitationFacadeHolder.INSTANCE;
    }

    private static class HabitationFacadeHolder
    {
        private static final HabitationFacade INSTANCE = new HabitationFacade();
    }

    public static void create(Date start, Date end, Date created, User user)
    {
    }

    public static void createWithReservationData(ReservationItem reservationItem)
    {
    }
}
