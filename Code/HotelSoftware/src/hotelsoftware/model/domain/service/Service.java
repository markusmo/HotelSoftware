/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.service;

import hotelsoftware.model.domain.service.data.ServiceData;
import java.math.BigDecimal;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public abstract class Service implements ServiceData
{
    private Integer id;
    protected BigDecimal price;    
    protected ServiceType serviceType;
    
    Service()
    {
    }
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        if (id == null){
            this.id = id;
        } 
    }
    
    protected Service(BigDecimal price, ServiceType serviceType){
        this.price = price;
        this.serviceType = serviceType;
    }
    

    /**
     * @return the price
     */
    @Override
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
