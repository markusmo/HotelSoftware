/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

/**
 *
 * @author Kno
 */
public interface IParty{

    public IAddress getAddress();

    public Integer getIdParties();

    void setAddress(IAddress address);

    void setIdParties(Integer id);
    
}
