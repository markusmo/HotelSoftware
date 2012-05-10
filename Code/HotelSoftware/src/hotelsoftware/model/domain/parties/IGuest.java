package hotelsoftware.model.domain.parties;

import hotelsoftware.model.domain.service.Habitation;

/**
 *Dieses Interface enthällt die Methoden der Klasse Guest, welche dort benötigt werden.
 * @author Kno
 */
public interface IGuest {

    void addHabitation(Habitation h);

    void removeHabitation(Habitation h);
    
}
