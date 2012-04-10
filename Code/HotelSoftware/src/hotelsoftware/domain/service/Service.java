/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.service;

import hotelsoftware.domain.invoice.InvoiceItem;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Service
{
    protected String name;
    protected BigDecimal price;            
    private Collection<InvoiceItem> invoiceItemCollection;
    private ServiceType serviceType;
}
