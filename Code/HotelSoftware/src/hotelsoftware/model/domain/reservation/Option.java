/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.reservation;

import hotelsoftware.model.database.reservation.DBReservationoptions;
import hotelsoftware.model.database.reservation.DBReservations;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Option implements OptionData
{
    private Date expiration;
    private BigDecimal prepayment;
    private boolean fulfilled;
    private Integer id;

    private Option()
    {
    }

    public static Option newOption()
    {
        return new Option();
    }

    private Option(DBReservationoptions r)
    {
        this.expiration = r.getExpiration();
        this.fulfilled = r.getFulfilled();
        this.prepayment = r.getPrepayment();
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
     * Use this Method if you want to extend the Expirationdate
     *
     * @param days The amount of days to extend
     */
    public void extendExpiration(int days)
    {
        long millis = expiration.getTime() + days * 864000000;
        expiration = new Date(millis);
    }

    @Override
    public boolean isFulfilled()
    {
        return fulfilled;
    }

    public void setFulfilled(boolean fulfilled)
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
        if (id == null)
        {
            this.id = id;
        }
    }
}
