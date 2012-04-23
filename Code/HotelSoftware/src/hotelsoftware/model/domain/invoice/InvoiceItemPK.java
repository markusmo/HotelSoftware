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
    private Integer idService;
    private Integer idInvoice;

    public InvoiceItemPK()
    {
    }

    public Integer getIdService()
    {
        return idService;
    }

    public void setIdService(int idServices)
    {
        this.idService = idServices;
    }

    public Integer getIdInvoice()
    {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice)
    {
        this.idInvoice = idInvoice;
    }
}
