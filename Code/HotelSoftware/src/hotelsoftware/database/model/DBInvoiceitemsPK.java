/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.database.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author mohi
 */
@Embeddable
public class DBInvoiceitemsPK implements Serializable
{
    @Basic(optional = false)
    @Column(name = "idServices", nullable = false)
    private int idServices;
    @Basic(optional = false)
    @Column(name = "idInvoice", nullable = false)
    private int idInvoice;

    public DBInvoiceitemsPK()
    {
    }

    public DBInvoiceitemsPK(int idServices, int idInvoice)
    {
        this.idServices = idServices;
        this.idInvoice = idInvoice;
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

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) idServices;
        hash += (int) idInvoice;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if(!(object instanceof DBInvoiceitemsPK))
        {
            return false;
        }
        DBInvoiceitemsPK other = (DBInvoiceitemsPK) object;
        if(this.idServices != other.idServices)
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
        return "hotelsoftware.database.model.InvoiceitemsPK[ idServices=" + idServices + ", idInvoice=" + idInvoice + " ]";
    }
    
}
