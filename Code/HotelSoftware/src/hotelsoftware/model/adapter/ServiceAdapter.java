/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.adapter;

import at.fhv.roomanizer.domain.service.IType;
import at.fhv.roomanizer.domain.service.Service;
import at.fhv.roomanizer.domain.service.Type;
import hotelsoftware.model.domain.service.ExtraService;
import hotelsoftware.model.domain.service.IExtraService;
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

    public ServiceAdapter(Service service)
    {
        this();
        this.ourService.setIdServices(service.getId());
        this.ourService.setServiceType(new TypeAdapter(service.getType()).getOurType());
        this.ourService.setPrice(BigDecimal.ZERO);
        this.ourService.setIdServices(service.getId());
        this.ourService.setServiceType(((TypeAdapter)service.getType()).getOurType());
        //muss wegen unneinigkeiten in der Domänenschicht manuell gesetzt werden -.-
        ((ExtraService)this.ourService).setName("PrePayment");
        if (service instanceof ExtraServiceAdapter)
        {
            //Muss so umständlich gesetzt werden da DB Modell nicht mehr übereinstimmt
            ((IExtraService)this.ourService).setReservable(((ExtraServiceAdapter)service).getOurType().getReservable());
        }
        else
        {
            ((IExtraService)this.ourService).setReservable(Boolean.FALSE);
        }
    }
    
    public ServiceAdapter()
    {
        this.ourService = new ExtraService();
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
        ourService.setServiceType((new TypeAdapter(type)).getOurType());
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
