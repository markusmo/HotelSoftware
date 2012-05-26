/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.controller.data.parties.GuestData;
import hotelsoftware.model.domain.reservation.IReservation;
import hotelsoftware.model.domain.service.IHabitation;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Kno
 */
public interface IGuest extends IParty, GuestData{

    /**
     * Fügt einen Aufenthalt zu einem Gast hinzu
     * @param h der Aufenthalt, der hinzugefügt werden soll
     */
    void addHabitation(IHabitation h);

    Collection<IHabitation> getCurrentHabitations();

    Collection<IHabitation> getHabitations();

    Collection<IReservation> getReservations();

    /**
     * Löscht einen spezifischen Aufenthalt
     * @param h der Aufenthalt, der gelöscht werden soll
     */
    void removeHabitation(IHabitation h);

    void setBirthday(Date birthday);

    void setCurrentHabitations(Collection<IHabitation> habitations);

    void setFname(String fname);

    void setGender(Character gender);

    void setLname(String lname);

    void setReservations(Collection<IReservation> reservations);
    
}
