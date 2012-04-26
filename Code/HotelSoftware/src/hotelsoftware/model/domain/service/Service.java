package hotelsoftware.model.domain.service;

import hotelsoftware.model.domain.service.data.ServiceData;
import java.math.BigDecimal;

/**
 * Diese Klasse bildet einen Service ab, ist Superklasse fuer alle Services,
 * mit denen das System arbeitet
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public abstract class Service implements ServiceData
{
    private Integer idServices;
    protected BigDecimal price;
    protected ServiceType serviceType;

    public Service()
    {
    }

    /**
     * @return the id
     */
    public Integer getIdServices()
    {
        return idServices;
    }

    /**
     * @param id the id to set
     */
    public void setIdServices(Integer id)
    {
        if (this.idServices == null)
        {
            this.idServices = id;
        }
    }

    protected Service(BigDecimal price, ServiceType serviceType)
    {
        this.price = price;
        this.serviceType = serviceType;
    }

    /**
     * @return the price
     */
    @Override
    public BigDecimal getPrice()
    {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    /**
     * @return the serviceType
     */
    public ServiceType getServiceType()
    {
        return serviceType;
    }

    /**
     * @param serviceType the serviceType to set
     */
    public void setServiceType(ServiceType serviceType)
    {
        this.serviceType = serviceType;
    }
}
