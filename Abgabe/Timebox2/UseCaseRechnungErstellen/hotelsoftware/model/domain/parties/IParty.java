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

    public IAddress getAddress();

    @Override
    public AddressData getAddressData();

    public Integer getIdParties();

    void setAddress(IAddress address);

    void setIdParties(Integer id);
    
}
