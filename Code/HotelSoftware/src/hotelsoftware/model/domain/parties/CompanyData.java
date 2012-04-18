/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import java.util.Collection;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface CompanyData
{

    String getCompanyname();

    Collection<PartyData> getContactPersonsData();

    String getName();

    CompanyTypeData getTypeData();
    
}
