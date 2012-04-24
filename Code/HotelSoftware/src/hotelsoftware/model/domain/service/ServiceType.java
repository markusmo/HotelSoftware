/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.service;

import hotelsoftware.model.domain.service.data.ServiceTypeData;
import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.service.DBServiceType;
import java.util.Collection;
import java.util.Set;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class ServiceType implements ServiceTypeData
{
    private String type;
    private Integer id;

    /**
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id)
    {
        if (id == null)
        {
            this.id = id;
        }
    }

    public static ServiceType createServiceType(String name)
    {
        return new ServiceType(name);
    }

    public static Set<ServiceType> getAllServiceTypes()
    {
        Set<DBServiceType> serviceType = DBServiceType.getAllServiceTypes();
        return (Set<ServiceType>) DynamicMapper.map(serviceType);
    }

    public ServiceType()
    {
    }

    private ServiceType(String type)
    {
        this.type = type;
    }

    /**
     * @return the type
     */
    @Override
    public String getType()
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type)
    {
        this.type = type;
    }

    public static ServiceType create(String name)
    {
        return new ServiceType(name);
    }
}
