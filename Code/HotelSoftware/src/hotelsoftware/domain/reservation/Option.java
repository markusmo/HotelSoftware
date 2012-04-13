/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.reservation;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Option
{
    private Date expiration;
    private BigDecimal prepayment;
    private boolean fulfilled;

    private Option()
    {
    }

    public static Option newOption()
    {
        return new Option();
    }

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

    public boolean isFulfilled()
    {
        return fulfilled;
    }

    public void setFulfilled(boolean fulfilled)
    {
        this.fulfilled = fulfilled;
    }

    public BigDecimal getPrepayment()
    {
        return prepayment;
    }

    public void setPrepayment(BigDecimal prepayment)
    {
        this.prepayment = prepayment;
    }
}
