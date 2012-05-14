package hotelsoftware.model.domain.parties;

/**
 *Dieses Interface enthällt die Methoden der Klasse Company, welche dort benötigt werden.
 * @author Kno
 */
public interface ICompany extends ICustomer {

    void addContactPerson(Party p);

    void removeContactPerson(Party p);
    
}
