/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.service;

import hotelsoftware.controller.data.service.ServiceTypeData;
import java.math.BigDecimal;

/**
 *
 * @author Kno
 */
public interface IServiceType extends ServiceTypeData{

    /**
     * @return the id
     */
    Integer getId();

    BigDecimal getTaxRate();

    /**
     * @param id the id to set
     */
    void setId(Integer id);

    /**
     * @param type the type to set
     */
    void setName(String type);

    void setTaxRate(BigDecimal taxRate);
    
}
