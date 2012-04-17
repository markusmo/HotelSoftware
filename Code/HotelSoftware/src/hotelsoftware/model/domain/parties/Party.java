/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.model.domain.reservation.Reservation;
import java.util.Collection;

/**
 * 
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public abstract class Party {
	
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
