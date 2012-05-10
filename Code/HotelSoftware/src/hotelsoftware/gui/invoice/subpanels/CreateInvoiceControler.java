/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.invoice.subpanels;

import hotelsoftware.controller.data.parties.CountryData;
import hotelsoftware.model.domain.parties.Country;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;

/**
 *
 * @author Johannes
 */
class CreateInvoiceControler
{
    private static Collection<CountryData> countries;

    public static Collection<CountryData> getAllCountries()
    {
        if (countries == null)
        {
            countries = new HelperFunctions<CountryData, Country>().castCollectionUp(Country.getAllCountries());
        }
        return countries;
    }
}
