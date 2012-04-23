package hotelsoftware.model.domain.parties;

import hotelsoftware.model.domain.invoice.InvoiceData;

import java.util.Collection;
/**
 * 
 * @author Hubert
 *
 */
public class PrivateCustomer extends Customer {
    
	private String fname;
	private String lname;
	private Character gender;

	PrivateCustomer() {
	}

	public static PrivateCustomer create(String fname, String lname,
			Character gender, Address invoiceAddress, Address address) {
		return new PrivateCustomer(fname, lname, gender, invoiceAddress,
				address);
	}

	private PrivateCustomer(String fname, String lname, Character gender,
			Address invoiceAddress, Address address) {
		super(address, invoiceAddress);
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
	}

	@Override
	public String getName() {
		return fname + " " + lname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}
/**
 * sucht in der datenbank nach einem PrivateCustomer
 * @param firstname
 * @param lastname
 * @return
 * @throws CompanyNotFoundException
 * @throws PrivateCustomerNotFoundException
 * @throws GuestNotFoundException
 */
	public static PrivateCustomer getPrivateCustomerByName(String firstname, String lastname)
			throws CompanyNotFoundException, PrivateCustomerNotFoundException, GuestNotFoundException {
		return PartyFacade.getInstance().getPrivateCustomerByName(firstname,lastname);
	}

	@Override
	public AddressData getInvoiceAddressData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<InvoiceData> getInvoicesData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddressData getAddressData() {
		// TODO Auto-generated method stub
		return null;
	}

}
