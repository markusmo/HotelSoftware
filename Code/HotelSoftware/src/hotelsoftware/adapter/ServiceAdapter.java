/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.service.IType;
import at.fhv.roomanizer.domain.service.Service;
import at.fhv.roomanizer.domain.service.Type;
import java.math.BigDecimal;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class ServiceAdapter extends Service implements Adapter<hotelsoftware.model.domain.service.IService>
{
    private hotelsoftware.model.domain.service.IService ourService;

    public ServiceAdapter(hotelsoftware.model.domain.service.IService ourservice)
    {
        this.ourService = ourservice;
    }

    ServiceAdapter(Service service)
    {
        ourService.setIdServices(service.getId());
        ourService.setServiceType(new ServiceTypeAdapter(service.getType()).getOurType());
    }

    @Override
    public void setId(int id)
    {
        this.ourService.setIdServices(id);
    }

    @Override
    public int getId()
    {
        return this.ourService.getIdServices();
    }

    @Override
    public void setType(Type type)
    {
        ourService.setServiceType((new ServiceTypeAdapter(type)).getOurType());
    }

    @Override
    public Type getType()
    {
        return new TypeAdapter(ourService.getServiceType());
    }

    @Override
    public IType getIType()
    {
        return (IType) new TypeAdapter(ourService.getServiceType());
    }

    @Override
    public hotelsoftware.model.domain.service.IService getOurType()
    {
        return this.ourService;
    }

    @Override
    public void setOurType(hotelsoftware.model.domain.service.IService type)
    {
        this.ourService = type;
    }
}
