/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.service;

import hotelsoftware.model.domain.service.data.ExtraServiceData;
import hotelsoftware.model.domain.service.data.ServiceTypeData;
import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.service.DBExtraService;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class ExtraService extends Service implements ExtraServiceData
{
    private String name;

    @Override
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ExtraService()
    {
    }

    private ExtraService(String name, BigDecimal price, ServiceType type)
    {
        super(price, type);
        this.name = name;
    }

    public static ExtraService createExtraService(String name, BigDecimal price, ServiceType type)
    {
        return new ExtraService(name, price, type);
    }

    public static Set<ExtraService> getAllExtraServices()
    {
        Set<ExtraService> extraServices = (Set<ExtraService>) DynamicMapper.map(DBExtraService.getAllExtraServices());
        return extraServices;
    }

    public static ExtraService getExtraServiceByName(String name) throws ServiceNotFoundException
    {
        ExtraService extraService = (ExtraService) DynamicMapper.map(DBExtraService.getExtraServiceByName(name));

        if (extraService == null)
        {
            throw new ServiceNotFoundException();
        }

        return extraService;
    }

    public ServiceTypeData getServiceTypeData()
    {
        return (ServiceTypeData) getServiceType();
    }
}
