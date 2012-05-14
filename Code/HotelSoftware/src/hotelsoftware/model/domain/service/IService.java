/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.service;

import hotelsoftware.controller.data.service.ServiceData;
import java.math.BigDecimal;

/**
 *
 * @author Kno
 */
public interface IService extends ServiceData{

    /**
     * @return the id
     */
    Integer getIdServices();

    /**
     * @return the price
     */
    BigDecimal getPrice();

    String getServiceName();

    /**
     * @return the serviceType
     */
    IServiceType getServiceType();

    /**
     * @param id the id to set
     */
    void setIdServices(Integer id);

    /**
     * @param price the price to set
     */
    void setPrice(BigDecimal price);

    /**
     * @param serviceType the serviceType to set
     */
    void setServiceType(IServiceType serviceType);
    
}
