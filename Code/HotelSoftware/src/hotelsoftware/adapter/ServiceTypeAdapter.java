/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.service.Type;
import hotelsoftware.model.domain.service.IServiceType;
import hotelsoftware.model.domain.service.ServiceType;
import java.math.BigDecimal;

/**
 *
 * @author Johannes
 */
class ServiceTypeAdapter extends Type implements Adapter<IServiceType>
{
    private ServiceType type;

    public ServiceTypeAdapter(Type type)
    {
        this.type = new ServiceType();
        this.type.setId(type.getId());
        this.type.setName(type.getName());
        this.type.setTaxRate(BigDecimal.valueOf(type.getTaxRate()));
    }

    public ServiceTypeAdapter(IServiceType type)
    {
        this.type = (ServiceType) type;
    }
    
    public ServiceTypeAdapter()
    {
    }

    @Override
    public IServiceType getOurType()
    {
        return type;
    }

    @Override
    public void setOurType(IServiceType type)
    {
        this.type = (ServiceType) type;
    }
}
