/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.createinvoice;

import hotelsoftware.controller.data.parties.CountryData;
import hotelsoftware.model.domain.parties.Country;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;

/**
 *
 * @author Dunst
 */
public class SelectCustomerState extends CreateInvoiceState
{
    public SelectCustomerState(CreateInvoiceController context)
    {
        super(context);
    }
    
    @Override
    public Collection<CountryData> getAllCountries()
    {
        return HelperFunctions.castCollectionUp(Country.getAllCountries(), CountryData.class, Country.class); 
    }
}
