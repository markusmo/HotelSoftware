/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.reservation;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Reservation
{
    private Date start;
    private Date end;
    private String comment;
    private Date created;
    private Collection<Option> optionCollection;
    
}


