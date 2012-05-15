/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.controller.data.invoice.InvoiceData;
import hotelsoftware.controller.data.parties.AddressData;
import hotelsoftware.controller.data.parties.CompanyData;
import hotelsoftware.controller.data.parties.CompanyTypeData;
import hotelsoftware.controller.data.parties.PartyData;
import java.util.Collection;

/**
 *
 * @author Kno
 */
public interface ICompany extends ICustomer, CompanyData{

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
