/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.controller.data.parties.PartyData;
import hotelsoftware.controller.data.reservation.ReservationData;
import hotelsoftware.controller.data.reservation.ReservationItemData;
import hotelsoftware.controller.data.reservation.ReservationOptionData;
import hotelsoftware.model.domain.parties.Guest;
import hotelsoftware.model.domain.parties.IGuest;
import hotelsoftware.model.domain.parties.IParty;
import hotelsoftware.model.domain.parties.Party;
import hotelsoftware.model.domain.users.IUser;
import hotelsoftware.model.domain.users.User;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Kno
 */
public interface IReservation extends ReservationData{

    String getComment();

    Date getCreated();

    Date getEndDate();

    Integer getGuestAmount();

    Collection<IGuest> getGuests();

    Integer getId();

    IParty getParty();

    String getReserationNumber();

    Collection<IReservationItem> getReservationItems();

    String getReservationNumber();

    Collection<IReservationOption> getReservationOptions();

    Date getStartDate();

    IUser getUser();

    void setComment(String comment);

    void setCreated(Date created);

    void setEndDate(Date end);

    void setGuests(Collection<IGuest> guests);

    void setId(Integer id);

    void setParty(IParty party);

    void setReserationNumber(String reservationNumber);

    void setReservationItems(Collection<IReservationItem> reservationItems);

    void setReservationNumber(String reservationNumber);

    void setReservationOptions(Set<IReservationOption> reservationOptions);

    void setStartDate(Date start);

    void setUser(IUser user);
    
}
