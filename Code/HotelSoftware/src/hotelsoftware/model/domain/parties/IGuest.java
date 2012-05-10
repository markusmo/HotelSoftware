/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.model.domain.service.Habitation;

/**
 *
 * @author Kno
 */
public interface IGuest {

    void addHabitation(Habitation h);

    void removeHabitation(Habitation h);
    
}
