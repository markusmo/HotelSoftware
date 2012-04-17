/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.model.database.parties.DBCustomer;

/**
 * 
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Customer extends Party {
    
    protected Address invoiceAddress; 
    protected DBCustomer costumerModel;

    public Customer(DBCustomer costumers)
    {
        this.costumerModel = costumers;
    }
    
    public DBCustomer getCostumerModel()
    {
        return costumerModel;
    }
}
