/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.service;

import java.math.BigDecimal;


/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class ExtraService extends Service
{
    String name;
    private ExtraService(String name, BigDecimal price, ServiceType type)
    {
        this.name = name;
        this.price = price;
        this.serviceType = type;
    }
    
    private ExtraService()
    {
    }
    
    public static ExtraService createExtraService(String name, BigDecimal price, ServiceType type){
        return new ExtraService(name, price, type);
    }
    
    public static getAllServices(){
        
    }
}
