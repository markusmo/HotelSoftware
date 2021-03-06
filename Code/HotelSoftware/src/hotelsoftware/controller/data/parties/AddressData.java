package hotelsoftware.controller.data.parties;

import hotelsoftware.model.domain.parties.Country;

/**
 *Dieses Interface enthält die get Methoden für die Klasse Adresse, welche dort dann implementiert werden.
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface AddressData
{

    String getCity();

    Country getIdCountry();

    String getEmail();

    String getFax();

    String getPhone();

    String getStreet();

    String getZip();
    
}
