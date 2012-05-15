/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.data.service;

import java.math.BigDecimal;

/**
 *Diese Klasse enthält alle wichtigen Methoden die für die Klasse Service benötigt werden
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
