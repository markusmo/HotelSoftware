package at.fhv.roomanizer.domain.service;

/**
 * Represents a type of a service, for instance habitation,
 * Food, Drinks, ....
 * 
 * @author Daniel Rotter <daniel.rotter@students.fhv.at>
 */
public class Type implements IType {
	/**
	 * The id of the service type
	 */
	private int _id;
	/**
	 * The name of the service type
	 */
	private String _name;
	/**
	 * The taxrate of the service type
	 */
	private double _taxRate;

	/**
	 * Sets the id of the service type
	 * @param id The id of the service type
	 */
	public void setId(int id) {
		_id = id;
	}

	/**
	 * Returns the id of the service type
	 * @return The id of the service type
	 */
	public int getId() {
		return _id;
	}
	
	/**
	 * Sets the name of the service type
	 * @param name The name of the service type
	 */
	public void setName(String name) {
		_name = name;
	}
	/**
	 * Returns the name of the service type
	 * @return The name of the service type
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Sets the taxrate of the service type
	 * @param taxRate The taxrate of the service type
	 */
	public void setTaxRate(double taxRate) {
		_taxRate = taxRate;
	}

	/**
	 * Returns the taxrate of the service type
	 * @return The taxrate of the service type
	 */
	public double getTaxRate() {
		return _taxRate;
	}
}
