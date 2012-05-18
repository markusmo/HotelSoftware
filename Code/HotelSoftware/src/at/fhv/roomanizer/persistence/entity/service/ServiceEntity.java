package at.fhv.roomanizer.persistence.entity.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Represents a service in the hotel. A service can be anything, which is sold to the
 * customer.
 * 
 * @author Daniel Rotter <daniel.rotter@students.fhv.at>
 */

@Entity
@Table(name="services")
@Inheritance(strategy = InheritanceType.JOINED)
public class ServiceEntity {
	/**
	 * The id of the service
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int _id;
	/**
	 * The type of the service
	 */
	
	@OneToOne
	@JoinColumn(name="idServiceTypes")
	private TypeEntity _type;

	/**
	 * Sets the id of the service
	 * @param id The id of the service
	 */
	public void setId(int id) {
		_id = id;
	}

	/**
	 * Returns the id of the service
	 * @return The id of the service
	 */
	public int getId() {
		return _id;
	}
	
	/**
	 * Sets the type of the service
	 * @param type The type of the service
	 */
	public void setType(TypeEntity type) {
		_type = type;
	}

	/**
	 * Returns the type of the service
	 * @return The type of the service
	 */
	public TypeEntity getType() {
		return _type;
	}
}
