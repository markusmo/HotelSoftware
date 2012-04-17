/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.invoice;

/**
 *
 * @author mohi
 */
public class InvoiceitemsPK
{
    private int idServices;
    private int idInvoice;

    public InvoiceitemsPK()
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
