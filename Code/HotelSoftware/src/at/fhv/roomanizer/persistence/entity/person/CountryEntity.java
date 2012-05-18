package at.fhv.roomanizer.persistence.entity.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name="countries")
public class CountryEntity {
	
	/**
	 * Id of the country
	 */
	@Id
	@Generated(GenerationTime.INSERT)
	@Column(name="id")
	private int _id;
	
	/**
	 * Name of the Country
	 */
	@Column(name="name")
	private String _name;
	
	/**
	 * Short name of the Country
	 */
	@Column(name="nameShort")
	private String _shortName;
	
	/**
	 * Returns the id of the country
	 * @return id of the country
	 */
	public int getId() {
		return _id;
	}
	
	/**
	 * Sets the id of the country
	 * @param id of the country
	 */
	public void setId(int id) {
		this._id = id;
	}
	
	/**
	 * Returns the name of the country
	 * @return name of the country
	 */
	public String getName() {
		return _name;
	}
	
	/**
	 * Sets the name of the country
	 * @param name of the country
	 */
	public void setName(String name) {
		this._name = name;
	}
	
	/**
	 * Returns the short name of the country
	 * @return short name of the country
	 */
	public String getShortName() {
		return _shortName;
	}
	
	/**
	 * Sets the short name of the country
	 * @param short name of the country
	 */
	public void setShortName(String shortName) {
		this._shortName = shortName;
	}
		
	//TODO Country-Object missing in Domain-Layer, afterwards Conversion-functions

}
