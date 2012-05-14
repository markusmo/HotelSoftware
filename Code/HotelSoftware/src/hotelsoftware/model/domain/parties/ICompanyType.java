/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.controller.data.parties.CompanyTypeData;

/**
 *
 * @author Kno
 */
public interface ICompanyType extends CompanyTypeData{

    /**
     * Instanziert einen neuen Firmen-Typ (z.B. GMBH, Reisebuero, etc.)
     * @param typ
     * Der Typ der Firma
     * @return
     * Eine neue Instanz des Firmentyps
     */
    CompanyType create(String typ);

    Integer getId();

    String getName();

    void setId(Integer id);

    void setName(String typ);
    
}
