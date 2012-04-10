/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.service;

import hotelsoftware.domain.invoice.InvoiceItem;
import hotelsoftware.domain.parties.Guest;
import hotelsoftware.domain.room.Room;
import hotelsoftware.domain.users.User;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Habitation
{
    private Date start;
    private Date end;
    private Date created;
    private Collection<Guest> guestCollection;
    private Collection<InvoiceItem> invoiceItemCollection;
    private Collection<Room> rooms;
}
