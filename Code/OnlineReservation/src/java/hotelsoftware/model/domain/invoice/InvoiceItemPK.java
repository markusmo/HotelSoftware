package hotelsoftware.model.domain.invoice;

/**
 * Zum eindeutigen Identifizieren von Rechungspositionen
 * @author mohi
 */
public class InvoiceItemPK implements IInvoiceItemPK
{
    private Integer idService;
    private Integer idInvoice;

    public InvoiceItemPK()
    {
    }

    @Override
    public Integer getIdService()
    {
        return idService;
    }

    @Override
    public void setIdService(int idServices)
    {
        this.idService = idServices;
    }

    @Override
    public Integer getIdInvoice()
    {
        return idInvoice;
    }

    @Override
    public void setIdInvoice(int idInvoice)
    {
        this.idInvoice = idInvoice;
    }
}
