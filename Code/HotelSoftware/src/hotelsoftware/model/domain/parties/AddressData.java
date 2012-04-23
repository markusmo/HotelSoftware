/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

/**
 *Dieses Interface enthällt die get Methoden für die Klasse Adresse, welche dort dann implementiert werden.
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface AddressData
{

    String getCity();

    String getIdCountry();

    String getEmail();

    String getFax();

    String getPhone();

    String getStreet();

    String getZip();
    
}
