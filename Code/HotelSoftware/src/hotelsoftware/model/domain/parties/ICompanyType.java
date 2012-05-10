package hotelsoftware.model.domain.parties;

/**
 *Dieses Interface enthällt die Methoden der Klasse CompanyType, welche dort benötigt werden.
 * @author Kno
 */
public interface ICompanyType {

    /**
     * Instanziert einen neuen Firmen-Typ (z.B. GMBH, Reisebuero, etc.)
     * @param typ
     * Der Typ der Firma
     * @return
     * Eine neue Instanz des Firmentyps
     */
    CompanyType create(String typ);
    
}
