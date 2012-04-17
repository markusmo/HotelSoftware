/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.service;

import hotelsoftware.database.model.DBServices;
import java.math.BigDecimal;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public abstract class Service
{
    protected BigDecimal price;    
    protected ServiceType serviceType;
    
    private Service()
    {
    }
    
    private Service(String name, BigDecimal price, ServiceType serviceType){
        this.price = price;
        this.serviceType = serviceType;
    }
    

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the serviceType
     */
    public ServiceType getServiceType() {
        return serviceType;
    }

    /**
     * @param serviceType the serviceType to set
     */
    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }
}
