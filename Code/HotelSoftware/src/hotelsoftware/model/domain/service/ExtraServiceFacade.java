/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.service;

import hotelsoftware.model.database.service.DBExtraService;
import hotelsoftware.model.DynamicMapper;
import java.util.Collection;
import javax.management.ServiceNotFoundException;

/**
 *
 * @author Tobias
 */
public class ExtraServiceFacade
{
    private ExtraServiceFacade()
    {
    }

    public static ExtraServiceFacade getInstance()
    {
        return ExtraServiceFacadeHolder.INSTANCE;
    }

    private static class ExtraServiceFacadeHolder
    {
        private static final ExtraServiceFacade INSTANCE = new ExtraServiceFacade();
    }

    public Collection<ExtraService> getAllExtraServices()
    {
        return DynamicMapper.mapCollection(DBExtraService.getExtraServices());
    }

    public ExtraService getExtraServiceByName(String name) throws ServiceNotFoundException
    {

        DBExtraService p = DBExtraService.getServiceByName(name);

        if (p == null)
        {

            throw new ServiceNotFoundException();

        }
        return (ExtraService) DynamicMapper.map(p);
    }
}
