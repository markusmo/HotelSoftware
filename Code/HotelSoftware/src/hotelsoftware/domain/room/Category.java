/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.room;

import hotelsoftware.domain.reservation.ReservationItem;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Category
{
    private String name;
    private BigDecimal price;
    private BigDecimal minPrice;
    private int bedAmount;
    private Collection<ReservationItem> reservationItemCollection;
}
