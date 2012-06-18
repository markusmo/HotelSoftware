package hotelsoftware.model.domain.service;

import java.math.BigDecimal;

/**
 * Diese Klasse bildet einen Service ab, ist Superklasse fuer alle Services,
 * mit denen das System arbeitet
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public abstract class Service implements IService
{
    private Integer idServices;
    protected BigDecimal price;
    protected IServiceType serviceType;
    
    public Service()
    {
    }

    /**
     * @return the id
     */
    @Override
    public Integer getIdServices()
    {
        return idServices;
    }

    /**
     * @param id the id to set
     */
    @Override
    public void setIdServices(Integer id)
    {
        if (this.idServices == null)
        {
            this.idServices = id;
        }
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
    @Override
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    /**
     * @return the serviceType
     */
    @Override
    public IServiceType getServiceType()
    {
        return serviceType;
    }

    /**
     * @param serviceType the serviceType to set
     */
    @Override
    public void setServiceType(IServiceType serviceType)
    {
        this.serviceType = serviceType;
    }
    
    @Override
    public double getPriceWithTax()
    {
        return this.getPrice().doubleValue() +  getOnlyTax();
    }

    /**
     * Gibt nur die Steuern aus
     * @return Die Steuern des Services
     */
    private double getOnlyTax()
    {
        double tax = (this.getServiceType().getTaxRate().doubleValue() / 100);
        return this.getPrice().doubleValue() * tax;
    }
}
