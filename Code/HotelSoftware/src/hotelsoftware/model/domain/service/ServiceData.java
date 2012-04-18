/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.service;

import java.math.BigDecimal;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface ServiceData
{

    /**
     * @return the price
     */
    BigDecimal getPrice();

    /**
     * @return the serviceType
     */
    ServiceTypeData getServiceTypeData();
    
}
