/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinereservation.beans;

import hotelsoftware.model.domain.room.IRoomCategory;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
@ManagedBean(name = "stepone")
@NoneScoped
public class StepOneBean
{
    private Date startDate;
    private Date endDate;
    private IRoomCategory category;
    
    public StepOneBean()
    {
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public IRoomCategory getCategory()
    {
        return category;
    }

    public void setCategory(IRoomCategory category)
    {
        this.category = category;
    }
}
