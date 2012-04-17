/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.parties;

import hotelsoftware.database.model.DBCustomers;

/**
 * 
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Customer extends Party {
    
    protected Address invoiceAddress; 
    protected DBCustomers costumerModel;

    public Customer(DBCustomers costumers)
    {
        this.costumerModel = costumers;
    }
    
    public DBCustomers getCostumerModel()
    {
        return costumerModel;
    }
}
