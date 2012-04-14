/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.parties;

import hotelsoftware.database.model.Customers;

/**
 * 
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Customer extends Party {
    
    protected Address invoiceAddress; 
    protected Customers costumerModel;

    public Customer(Customers costumers)
    {
        this.costumerModel = costumers;
    }
    
    public Customers getCostumerModel()
    {
        return costumerModel;
    }
}
