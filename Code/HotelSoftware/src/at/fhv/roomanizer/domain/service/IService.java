package at.fhv.roomanizer.domain.service;

public interface IService {
	/**
	 * Returns the id of the service
	 * @return The id of the service
	 */
	public int getId();
	/**
	 * Returns the type of the service
	 * @return The type of the service
	 */
	public IType getIType();
}
