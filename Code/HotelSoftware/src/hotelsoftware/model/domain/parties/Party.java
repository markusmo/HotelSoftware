/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

/**
 * Klasse die eine oder Mehrere Personen beschreibt
 * @author Lins Christian (christian.lins87@gmail.com)
 * @author Hubert
 */
public abstract class Party implements PartyData {
	
	protected Address address;
	protected Integer id;

	Party() {
	}

	protected Party(Address address) {
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		if (id == null)
		{
			this.id = id;
		}
	}

}
