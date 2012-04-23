/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface OptionData
{

    Date getExpiration();

    BigDecimal getPrepayment();

    boolean isFulfilled();
    
}
