package hotelsoftware.controller.data.parties;

import java.util.Collection;

/**
 *Dieses Interface enthält Methoden die in der Companyklasse implementiert werden müässen, 
 *da sie dort dringend benötigt werden.
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface CompanyData extends CustomerData
{

    String getCompanyname();

    Collection<PartyData> getContactPersonsData();

    CompanyTypeData getTypeData();
    
}
