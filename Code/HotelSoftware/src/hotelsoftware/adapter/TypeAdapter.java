/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.service.Type;
import hotelsoftware.model.domain.service.IServiceType;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class TypeAdapter extends Type
{

    private IServiceType ourType;
    
    public TypeAdapter(IServiceType ourType)
    {
        this.ourType = ourType;
    }
    
    @Override
    public int getId()
    {
        return this.ourType.getId();
    }

    @Override
    public String getName()
    {
        return this.ourType.getName();
    }

    @Override
    public double getTaxRate()
    {
        return this.getTaxRate();
    }
    
}
