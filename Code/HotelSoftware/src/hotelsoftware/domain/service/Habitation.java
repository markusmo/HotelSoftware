/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.service;

import hotelsoftware.database.model.Habitations;
import java.util.Date;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Habitation
{
    private Date start;
    private Date end;
    private Date created;
    private Habitations model;

    public Habitation(Habitations habitation)
    {
        this.model = habitation;
    }

    public Habitations getModel()
    {
        return model;
    }
    
}
