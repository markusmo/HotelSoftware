/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

/**
 *
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
