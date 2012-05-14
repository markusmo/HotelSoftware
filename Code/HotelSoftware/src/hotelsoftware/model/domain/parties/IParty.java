/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.controller.data.parties.AddressData;
import hotelsoftware.controller.data.parties.PartyData;

/**
 *
 * @author Kno
 */
public interface IParty extends PartyData{

    IAddress getAddress();

    AddressData getAddressData();

    Integer getIdParties();

    void setAddress(IAddress address);

    void setIdParties(Integer id);
    
}
