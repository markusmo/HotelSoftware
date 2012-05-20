/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

import hotelsoftware.controller.data.reservation.ReservationOptionData;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Kno
 */
public interface IReservationOption extends ReservationOptionData {

    /**
     * Diese Methode wird benötigt um das Ablaufdatum zu verlängern
     *
     * @param days The amount of days to extend
     */
    void extendExpiration(int days);

    String getComment();

    Date getExpiration();

    Integer getId();

    BigDecimal getPrepayment();

    IReservation getReservation();

    Boolean isFulfilled();

    void setId(Integer id);
    
    void setComment(String comment);

    void setExpiration(Date expiration);

    void setFulfilled(Boolean fulfilled);

    void setPrepayment(BigDecimal prepayment);

    void setReservation(IReservation reservation);
    
}
