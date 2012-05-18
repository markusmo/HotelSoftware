package at.fhv.roomanizer.domain.person;

public interface IParty {

	/**
	 * Returns the id of a party
	 * @return
	 */
	public int getId();
	
	/**
	 * Returns the Address of the Party
	 * @return Address of the Party
	 */
	public IAddress getIAddress();
}
