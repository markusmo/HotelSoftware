/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.service;

import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class ExtraService extends Service
{
    private String name;

    private ExtraService(String name, BigDecimal price, ServiceType type)
    {
        super(price, type);
        this.name = name;
    }

    private ExtraService()
    {
    }

    public static ExtraService createExtraService(String name, BigDecimal price, ServiceType type)
    {
        return new ExtraService(name, price, type);
    }

    public static Collection<ExtraService> getAllExtraServices()
    {
        return null;
    }
}
