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
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class TypeAdapter extends Type implements Adapter<IServiceType>
{
    private IServiceType ourType;

    public TypeAdapter()
    {
    }
    
    public TypeAdapter(IServiceType ourType)
    {
        this.ourType = ourType;
    }
    
    public TypeAdapter(Type theirType)
    {
        this.ourType = new ServiceType();
        ourType.setId(theirType.getId());
        ourType.setName(theirType.getName());
        ourType.setTaxRate(BigDecimal.valueOf(theirType.getTaxRate()));
    }

    @Override
    public void setId(int id)
    {
        this.ourType.setId(id);
    }

    @Override
    public int getId()
    {
        return ourType.getId();
    }

    @Override
    public void setName(String name)
    {
        this.ourType.setName(name);
    }

    @Override
    public String getName()
    {
        return ourType.getName();
    }

    @Override
    public void setTaxRate(double taxRate)
    {
        ourType.setTaxRate(new BigDecimal(taxRate));
    }

    @Override
    public double getTaxRate()
    {
        return this.ourType.getTaxRate().doubleValue();
    }

    @Override
    public IServiceType getOurType()
    {
        return this.ourType;
    }

    @Override
    public void setOurType(IServiceType type)
    {
        this.ourType = type;
    }
}
