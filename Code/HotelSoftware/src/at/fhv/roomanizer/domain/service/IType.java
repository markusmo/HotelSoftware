package at.fhv.roomanizer.domain.service;

public interface IType {
	/**
	 * Returns the id of the service type
	 * @return The id of the service type
	 */
	public int getId();
	/**
	 * Returns the name of the service type
	 * @return The name of the service type
	 */
	public String getName();
	/**
	 * Returns the taxrate of the service type
	 * @return The taxrate of the service type
	 */
	public double getTaxRate();
}
