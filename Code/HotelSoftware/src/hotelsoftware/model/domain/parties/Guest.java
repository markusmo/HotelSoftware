/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.model.datainterfaces.GuestData;
import hotelsoftware.model.domain.service.Habitation;
import java.util.Collection;
import java.util.Date;

/**
 * 
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Guest extends Party implements GuestData
{
    private Date birthday;
    private Collection<Habitation> habitations;

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public void setHabitations(Collection<Habitation> habitations)
    {
        this.habitations = habitations;
    }
            
    public Date getBirthday()
    {
        return birthday;
    }

    public Collection<Habitation> getHabitations()
    {
        return habitations;
    }
}
