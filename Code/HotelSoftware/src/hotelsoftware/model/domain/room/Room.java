/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import hotelsoftware.model.domain.service.Habitation;
import java.util.Collection;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Room
{
    private int number;
    private Collection<RoomOption> options;
    private Category category;
    private Collection<Habitation> habitationCollection;
    private Collection<RoomStatus> status;
}
