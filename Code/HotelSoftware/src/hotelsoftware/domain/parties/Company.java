/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.parties;

import java.util.Collection;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Company extends Customer
{
    private String name;    
    private Collection<Party> partyCollection;
    private CompanyType type;
}
