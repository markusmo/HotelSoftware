/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

import hotelsoftware.model.domain.reservation.data.ReservationOptionData;
import hotelsoftware.model.database.reservation.DBReservationOption;
import java.math.BigDecimal;
import java.util.Date;

/**
 *Diese Klasse beinhaltet eine Reservierungsoption die für Reservierungen  benötigt werden
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class ReservationOption implements ReservationOptionData
{
    private Date expiration;
    private BigDecimal prepayment;
    private Boolean fulfilled;
    private Integer id;
    private Reservation reservation;
    private String comment;

    public ReservationOption()
    {
    }

    public Reservation getReservation()
    {
        return reservation;
    }

    public void setReservation(Reservation reservation)
    {
        this.reservation = reservation;
    }
    
    public static ReservationOption newOption()
    {
        return new ReservationOption();
    }

    @Override
    public Date getExpiration()
    {
        return expiration;
    }

    public void setExpiration(Date expiration)
    {
        this.expiration = expiration;
    }

    /**
     * Diese Methode wird benötigt um das Ablaufdatum zu verlängern
     *
     * @param days The amount of days to extend
     */
    public void extendExpiration(int days)
    {
        long millis = expiration.getTime() + days * 864000000;
        expiration = new Date(millis);
    }

    @Override
    public Boolean isFulfilled()
    {
        return fulfilled;
    }

    public void setFulfilled(Boolean fulfilled)
    {
        this.fulfilled = fulfilled;
    }

    @Override
    public BigDecimal getPrepayment()
    {
        return prepayment;
    }

    public void setPrepayment(BigDecimal prepayment)
    {
        this.prepayment = prepayment;
    }
    public Integer getId()
    {
        return id;
    }

    void setId(Integer id)
    {
        if (this.id == null)
        {
            this.id = id;
        }
    }
    
    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }
}
