/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import java.util.Collection;

/**
 *
 * @author Kno
 */
public interface ICompany extends ICustomer{

    void addContactPerson(IParty p);

    ICompanyType getCompanyType();

    String getCompanyname();

    Collection<IParty> getContactPersons();

    String getName();

    void removeContactPerson(IParty p);

    void setCompanyType(ICompanyType type);

    void setContactPersons(Collection<IParty> contactPersons);

    void setName(String companyname);
    
}
