/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.invoice;

/**
 *
 * @author mohi
 */
public class InvoiceItemPK
{
    private int idServices;
    private int idInvoice;

    public InvoiceItemPK()
    {
    }

    public int getIdServices()
    {
        return idServices;
    }

    public void setIdServices(int idServices)
    {
        this.idServices = idServices;
    }

    public int getIdInvoice()
    {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice)
    {
        this.idInvoice = idInvoice;
    }
}
