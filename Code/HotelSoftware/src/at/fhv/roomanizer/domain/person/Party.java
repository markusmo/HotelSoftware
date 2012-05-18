package at.fhv.roomanizer.domain.person;

/**
 * This person is a superset of all persons appearing in our system.
 * 
 * @author Daniel Rotter <daniel.rotter@students.fhv.at>
 */
public class Party implements IParty {
	/**
	 * The id of the person
	 */
	private int _id;
	/**
	 * The address of the person
	 */
	private Address _address;
	
	/**
	 * Sets the address of the person
	 * @param address The address of the person
	 */
	public void setAddress(Address address) {
		_address = address;
	}
	
	/**
	 * Returns the address of the person
	 * @return The address of the person
	 */
	public Address getAddress(){
		return _address;
	}

	/**
	 * @see IParty#getIAddress()
	 */
	@Override
	public IAddress getIAddress() {
		return (IAddress) _address;
	}

	/**
	 * @see IParty#getId
	 */
	public int getId() {
		return _id;
	}
/**
 * Sets the id of a party
 * @param id
 */
	public void setId(int id) {
		this._id = id;
	}
}
