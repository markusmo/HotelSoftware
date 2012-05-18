package at.fhv.roomanizer.persistence.entity.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * This person is a superset of all persons appearing in our system.
 * 
 * @author Daniel Rotter <daniel.rotter@students.fhv.at>
 */
@Entity
@Table(name="parties")
@Inheritance(strategy = InheritanceType.JOINED)
public class PartyEntity {
	/**
	 * The id of the person
	 */
	@Id
	@Generated(GenerationTime.INSERT)
	@Column(name="id")
	private int _id;
	/**
	 * The address of the person
	 */
	
	@ManyToOne
	@JoinColumn(name="idAddresses")
	private AddressEntity _address;
	
	/**
	 * Sets the address of the person
	 * @param address The address of the person
	 */
	public void setAddress(AddressEntity address) {
		_address = address;
	}
	
	/**
	 * Returns the address of the person
	 * @return The address of the person
	 */
	public AddressEntity getAddress(){
		return _address;
	}

	/**
	 * Returns the id of the party
	 * @return id of the party
	 */
	public int getId() {
		return _id;
	}

	/**
	 * Sets the id of the party
	 * @param id of the party
	 */
	public void setId(int id) {
		this._id = id;
	}
}
