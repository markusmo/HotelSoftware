/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.service.HabitationData;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Room implements RoomData
{
    private int number; //FIXME maybe String (DB???)
    private Collection<RoomOption> options;
    private Category category;
    private Collection<Habitation> habitationCollection;
    private Collection<RoomStatus> status;

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public Collection<Habitation> getHabitationCollection()
    {
        return habitationCollection;
    }

    public void setHabitationCollection(Collection<Habitation> habitationCollection)
    {
        this.habitationCollection = habitationCollection;
    }

    @Override
    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public Collection<RoomOption> getOptions()
    {
        return options;
    }

    public void setOptions(Collection<RoomOption> options)
    {
        this.options = options;
    }

    public Collection<RoomStatus> getStatus()
    {
        return status;
    }

    public void setStatus(Collection<RoomStatus> status)
    {
        this.status = status;
    }

    public CategoryData getCategoryData()
    {
        return (CategoryData) getCategory();
    }

    public Collection<HabitationData> getHabitationCollectionData()
    {
        return new HelperFunctions<HabitationData, Habitation>().castCollectionUp(getHabitationCollection());
    }

    public Collection<RoomOptionData> getOptionsData()
    {
        return new HelperFunctions<RoomOptionData, RoomOption>().castCollectionUp(getOptions());
    }

    public Collection<RoomStatusData> getStatusData()
    {
        return new HelperFunctions<RoomStatusData, RoomStatus>().castCollectionUp(getStatus());
    }
    
    
}
