/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.parties;


import hotelsoftware.domain.invoice.Invoice;
import java.util.Collection;

/**
 * 
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Customer extends Party {
    
    protected Address invoiceAddress;    
    protected Collection<Invoice> invoiceCollection;
    
}
