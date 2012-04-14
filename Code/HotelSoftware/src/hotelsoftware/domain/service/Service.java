/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.service;

import hotelsoftware.database.model.Services;
import java.math.BigDecimal;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Service
{
    protected String name;
    protected BigDecimal price;    
    private ServiceType serviceType;
    
    public Service(Services service)
    {
    }
}
