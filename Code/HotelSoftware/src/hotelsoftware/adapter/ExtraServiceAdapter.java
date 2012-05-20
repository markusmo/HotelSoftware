/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.service.ExtraService;
import at.fhv.roomanizer.domain.service.IType;
import at.fhv.roomanizer.domain.service.Type;
import java.math.BigDecimal;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class ExtraServiceAdapter extends ExtraService implements Adapter<hotelsoftware.model.domain.service.ExtraService>
{
    private hotelsoftware.model.domain.service.ExtraService ourExtraService;

    public ExtraServiceAdapter()
    {
        
    }

    public ExtraServiceAdapter(hotelsoftware.model.domain.service.IExtraService ourExtraService)
    {
        this.ourExtraService = (hotelsoftware.model.domain.service.ExtraService) ourExtraService;
    }
    
    public ExtraServiceAdapter(ExtraService theirExtraService)
    {
        this.ourExtraService = new hotelsoftware.model.domain.service.ExtraService();
        this.ourExtraService.setPrice(BigDecimal.valueOf(theirExtraService.getPrice()));
        this.ourExtraService.setIdServices(theirExtraService.getId());
        this.ourExtraService.setServiceType(((TypeAdapter)theirExtraService.getType()).getOurType());
        //TODO muss wegen unneinigkeiten in der Domänenschicht manuell gesetzt werden -.-
        this.ourExtraService.setName("PrePayment");
    }

    @Override
    public double getPrice()
    {
        return this.ourExtraService.getPrice().doubleValue();
    }

    @Override
    public void setPrice(double price)
    {
        this.ourExtraService.setPrice(new BigDecimal(price));
    }

    @Override
    public IType getIType()
    {
        return getType();
    }

    @Override
    public int getId()
    {
        return this.ourExtraService.getIdServices();
    }

    @Override
    public Type getType()
    {
        return new TypeAdapter(this.ourExtraService.getServiceType());
    }

    @Override
    public void setId(int id)
    {
        this.ourExtraService.setIdServices(id);
    }

    @Override
    public void setType(Type type)
    {
        this.ourExtraService.setServiceType(((TypeAdapter) type).getOurType());
    }

    @Override
    public hotelsoftware.model.domain.service.ExtraService getOurType()
    {
        return ourExtraService;
    }

    @Override
    public void setOurType(hotelsoftware.model.domain.service.ExtraService type)
    {
        this.ourExtraService = type;
    }
}
