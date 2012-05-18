/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

import at.fhv.roomanizer.domain.service.ExtraService;
import java.math.BigDecimal;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class ExtraServiceAdapter extends ExtraService implements Adapter<hotelsoftware.model.domain.service.ExtraService>
{
    
    private hotelsoftware.model.domain.service.ExtraService ourExtraService;
    
    public ExtraServiceAdapter(){}
    
    public ExtraServiceAdapter(hotelsoftware.model.domain.service.ExtraService ourExtraService)
    {
        this.ourExtraService = ourExtraService;
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
