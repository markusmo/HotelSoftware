/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.service;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.service.DBExtraService;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author Tobias
 */
public class ServiceFacade
{
    private ServiceFacade()
    {
    }

    public static ServiceFacade getInstance()
    {
        return ServiceFacadeHolder.INSTANCE;
    }

    private static class ServiceFacadeHolder
    {
        private static final ServiceFacade INSTANCE = new ServiceFacade();
    }

    public Set<ExtraService> getAllExtraServices()
    {
        return DynamicMapper.mapCollection(DBExtraService.getExtraServices());
    }

    public ExtraService getExtraServiceByName(String name) throws javax.management.ServiceNotFoundException
    {

        DBExtraService p = DBExtraService.getExtraServiceByName(name);

        if (p == null)
        {

            throw new javax.management.ServiceNotFoundException();

        }
        return (ExtraService) DynamicMapper.map(p);
    }
}
