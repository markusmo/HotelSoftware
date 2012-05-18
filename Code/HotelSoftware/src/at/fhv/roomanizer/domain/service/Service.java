package at.fhv.roomanizer.domain.service;

/**
 * Represents a service in the hotel. A service can be anything, which is sold to the
 * customer.
 *
 * @author Daniel Rotter <daniel.rotter@students.fhv.at>
 */
public class Service implements IService
{
    /**
     * The id of the service
     */
    private int _id;
    /**
     * The type of the service
     */
    private Type _type;

    /**
     * Sets the id of the service
     *
     * @param id The id of the service
     */
    public void setId(int id)
    {
        _id = id;
    }

    /**
     * Returns the id of the service
     *
     * @return The id of the service
     */
    @Override
    public int getId()
    {
        return _id;
    }

    /**
     * Sets the type of the service
     *
     * @param type The type of the service
     */
    public void setType(Type type)
    {
        _type = type;
    }

    /**
     * Returns the type of the service
     *
     * @return The type of the service
     */
    public Type getType()
    {
        return _type;
    }

    /**
     * Returns the interface of type of the service
     *
     * @return the IType of the Service
     */
    @Override
    public IType getIType()
    {
        return (IType) _type;
    }
}
