/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.parties;

import hotelsoftware.database.model.DBCustomers;
import java.util.Collection;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Company extends Customer
{
    private String name;    
    private CompanyType type;
    
    public Company(DBCustomers costumer)
    {
        super(costumer);
    }
}
