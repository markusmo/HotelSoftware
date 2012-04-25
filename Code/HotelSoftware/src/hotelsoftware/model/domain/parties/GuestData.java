package hotelsoftware.model.domain.parties;

import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.service.data.HabitationData;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * Dieses Interface enthällt die Methoden, die von der Gastklasse implementiert werden müssen und somit für den Gast wichtig sind.
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
