/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.invoice.subpanels;

import hotelsoftware.controller.createinvoice.CreateInvoiceController;
import hotelsoftware.controller.data.parties.CountryData;
import java.util.Collection;

/**
 *
 * @author Johannes
 */
class CreateInvoiceGuiControler
{
    private static class CreateInvoiceGuiControlerHolder
    {
        private static final CreateInvoiceGuiControler INSTANCE = new CreateInvoiceGuiControler();
    }

    public static CreateInvoiceGuiControler getInstance()
    {
        return CreateInvoiceGuiControler.CreateInvoiceGuiControlerHolder.INSTANCE;
    }
    private Collection<CountryData> countries;

    public Collection<CountryData> getAllCountries()
    {
        if (countries == null)
        {
            countries = CreateInvoiceController.getInstance().getAllCountries();
        }
        return countries;
    }
}
