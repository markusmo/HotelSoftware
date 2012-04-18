/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.service.HabitationData;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public interface GuestData
{

    Date getBirthday();

    Collection<HabitationData> getCurrentHabitationsData();

    String getFname();

    Character getGender();

    Collection<HabitationData> getHabitationsData();

    String getLname();
    
}
