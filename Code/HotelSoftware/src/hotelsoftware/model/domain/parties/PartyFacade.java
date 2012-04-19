/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.parties.DBCompany;
import hotelsoftware.model.database.parties.DBCompanyType;
import hotelsoftware.model.database.parties.DBGuest;
import hotelsoftware.model.database.parties.DBPrivateCustomer;
import java.util.Collection;

/**
 * 
 * @author Hubert
 */
public class PartyFacade {
	private PartyFacade() {
	}

	public static PartyFacade getInstance() {
		return PartyFacadeHolder.INSTANCE;
	}

	private static class PartyFacadeHolder {
		private static final PartyFacade INSTANCE = new PartyFacade();
	}

	public Collection<CompanyType> getAllTypes() {
		return DynamicMapper.mapCollection(DBCompanyType.getAllTypes());
	}

	public Company getCompanyByName(String name)
			throws CompanyNotFoundException {
		DBCompany c = DBCompany.getCompanyByName(name);

		if (c == null) {
			throw new CompanyNotFoundException();
		}

		return (Company) DynamicMapper.map(c);
	}

	public PrivateCustomer getPrivateCustomerByName(String firstName,
			String lastName) throws PrivateCustomerNotFoundException,
			GuestNotFoundException {
		DBPrivateCustomer c = DBPrivateCustomer.getPrivateCustomerByName(firstName, lastName);

		if (c == null) {
			throw new GuestNotFoundException();
		}

		return (PrivateCustomer) DynamicMapper.map(c);
	}

	@SuppressWarnings("unchecked")
	public Collection<Guest> getGuestByName(String firstName, String lastName)
			throws CompanyNotFoundException, GuestNotFoundException {
		Collection<DBGuest> c = DBGuest.getGuestsByName(firstName, lastName);

		if (c == null) {
			throw new GuestNotFoundException();
		}

		return (Collection<Guest>) DynamicMapper.map(c);
	}
}
