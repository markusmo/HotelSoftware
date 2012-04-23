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
    private int idService;
    private int idInvoice;

    public InvoiceItemPK()
    {
    }

    public int getIdService()
    {
        return idService;
    }

    public void setIdService(int idServices)
    {
        this.idService = idServices;
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
