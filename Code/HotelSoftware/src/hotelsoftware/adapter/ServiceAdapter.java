/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.service.IType;
import at.fhv.roomanizer.domain.service.Service;
import at.fhv.roomanizer.domain.service.Type;
import hotelsoftware.model.domain.service.IServiceType;
import hotelsoftware.model.domain.service.ServiceType;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class ServiceAdapter extends Service
{
    private hotelsoftware.model.domain.service.IService ourService;
    
    public ServiceAdapter(hotelsoftware.model.domain.service.IService ourservice)
    {
        this.ourService = ourservice;
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
        //TODO implement
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
    
}
