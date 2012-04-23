/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import java.util.Collection;

/**
 *Dieses Interface enthällt Methoden die in der Companyklasse implementiert werden müässen, 
 *da sie dort dringend benötigt werden.
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface CompanyData
{

    String getCompanyname();

    Collection<PartyData> getContactPersonsData();

    String getName();

    CompanyTypeData getTypeData();
    
}
