/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.model.domain.reservation.Reservation;
import java.util.Collection;

/**
 * 
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public abstract class Party
{
    
    protected String fname;    
    protected String lname;
    protected Address address;
    protected Collection<Reservation> reservationCollection;
    protected Collection<Company> companyCollection;
    
    
}
