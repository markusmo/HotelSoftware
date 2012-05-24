/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.service;

import java.math.BigDecimal;

/**
 *
 * @author Kno
 */
public interface IServiceType{

    /**
     * @return the id
     */
    Integer getId();

    /**
     * @return the type
     */
    String getName();

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
