/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.adapter;

import at.fhv.roomanizer.domain.room.ISeason;
import at.fhv.roomanizer.domain.room.Season;
import java.util.Date;

/**
 *
 * @author Johannes
 */
public class SeasonAdapter extends Season implements Adapter<hotelsoftware.model.domain.room.Season>
{
    private hotelsoftware.model.domain.room.Season season;

    public SeasonAdapter(ISeason seasons)
    {
        this.season = new hotelsoftware.model.domain.room.Season();
        this.season.setEnd(seasons.getEnd());
        this.season.setStart(seasons.getStart());
        this.season.setId(seasons.getId());
        this.season.setName(seasons.getName());
    }

    public SeasonAdapter(hotelsoftware.model.domain.room.ISeason seasons)
    {
        this.season = (hotelsoftware.model.domain.room.Season) seasons;
    }

    public SeasonAdapter()
    {
    }

    @Override
    public Date getEnd()
    {
        return season.getEnd();
    }

    @Override
    public int getId()
    {
        return season.getId();
    }

    @Override
    public String getName()
    {
        return season.getName();
    }

    @Override
    public Date getStart()
    {
        return season.getStart();
    }

    @Override
    public void setEnd(Date end)
    {
        season.setEnd(end);
    }

    @Override
    public void setId(int id)
    {
        season.setId(id);
    }

    @Override
    public void setName(String name)
    {
        season.setName(name);
    }

    @Override
    public void setStart(Date start)
    {
        season.setStart(start);
    }

    @Override
    public hotelsoftware.model.domain.room.Season getOurType()
    {
        return season;
    }

    @Override
    public void setOurType(hotelsoftware.model.domain.room.Season type)
    {
        season = type;
    }
}