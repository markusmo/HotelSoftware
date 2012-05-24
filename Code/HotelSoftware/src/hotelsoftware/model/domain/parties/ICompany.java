/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.controller.data.parties.CompanyData;
import java.util.Collection;

/**
 *
 * @author Kno
 */
public interface ICompany extends ICustomer, CompanyData{

    void addContactPerson(IParty p);

    ICompanyType getCompanyType();

    Collection<IParty> getContactPersons();

    void removeContactPerson(IParty p);

    void setCompanyType(ICompanyType type);

    void setContactPersons(Collection<IParty> contactPersons);

    void setName(String companyname);
    
}
