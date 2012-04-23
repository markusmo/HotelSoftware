/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.invoice;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author mohi
 */
@Embeddable
public class DBInvoiceItemPK implements Serializable
{
    @Basic(optional = false)
    @Column(name = "idServices", nullable = false)
    private Integer idService;
    @Basic(optional = false)
    @Column(name = "idInvoice", nullable = false)
    private Integer idInvoice;

    public DBInvoiceItemPK()
    {
    }

    public DBInvoiceItemPK(int idServices, int idInvoice)
    {
        this.idService = idServices;
        this.idInvoice = idInvoice;
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

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) idService;
        hash += (int) idInvoice;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof DBInvoiceItemPK))
        {
            return false;
        }
        DBInvoiceItemPK other = (DBInvoiceItemPK) object;
        if(this.idService != other.idService)
        {
            return false;
        }
        if(this.idInvoice != other.idInvoice)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "hotelsoftware.database.model.InvoiceitemsPK[ idServices=" + idService + ", idInvoice=" + idInvoice + " ]";
    }
    
}
