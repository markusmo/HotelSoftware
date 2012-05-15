/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

import hotelsoftware.controller.data.reservation.ReservationOptionData;
import hotelsoftware.model.database.reservation.DBReservationOption;
import java.math.BigDecimal;
import java.util.Date;

/**
 *Diese Klasse beinhaltet eine Reservierungsoption die für Reservierungen  benötigt werden
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class ReservationOption implements IReservationOption
{
    private Date expiration;
    private BigDecimal prepayment;
    private Boolean fulfilled;
    private Integer id;
    private IReservation reservation;
    private String comment;

    public ReservationOption()
    {
    }

    @Override
    public IReservation getReservation()
    {
        return reservation;
    }

    @Override
    public void setReservation(IReservation reservation)
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

    @Override
    public void setExpiration(Date expiration)
    {
        this.expiration = expiration;
    }

    /**
     * Diese Methode wird benötigt um das Ablaufdatum zu verlängern
     *
     * @param days The amount of days to extend
     */
    @Override
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

    @Override
    public void setFulfilled(Boolean fulfilled)
    {
        this.fulfilled = fulfilled;
    }

    @Override
    public BigDecimal getPrepayment()
    {
        return prepayment;
    }

    @Override
    public void setPrepayment(BigDecimal prepayment)
    {
        this.prepayment = prepayment;
    }
    @Override
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
    
    @Override
    public String getComment()
    {
        return comment;
    }

    @Override
    public void setComment(String comment)
    {
        this.comment = comment;
    }
}
