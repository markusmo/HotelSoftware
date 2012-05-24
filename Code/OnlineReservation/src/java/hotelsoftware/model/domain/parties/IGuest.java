/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.model.domain.reservation.IReservation;
import hotelsoftware.model.domain.service.IHabitation;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Kno
 */
public interface IGuest extends IParty{

    void addHabitation(IHabitation h);

    Date getBirthday();

    Collection<IHabitation> getCurrentHabitations();

    String getFname();

    Character getGender();

    Collection<IHabitation> getHabitations();

    String getLname();

    Collection<IReservation> getReservations();

    void removeHabitation(IHabitation h);

    void setBirthday(Date birthday);

    void setCurrentHabitations(Collection<IHabitation> habitations);

    void setFname(String fname);

    void setGender(Character gender);

    void setLname(String lname);

    void setReservations(Collection<IReservation> reservations);

    @Override
    String toString();
    
}
