/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation.data;

import java.math.BigDecimal;
import java.util.Date;

/**
 *Dieses Interface stellt alle Methoden bereit die die Klasse ReservationOption benötigt
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface ReservationOptionData
{

    Date getExpiration();

    BigDecimal getPrepayment();

    Boolean isFulfilled();
    
}
