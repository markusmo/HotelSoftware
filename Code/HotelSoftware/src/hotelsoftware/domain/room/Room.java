/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.room;

import hotelsoftware.domain.service.Habitation;
import java.util.Collection;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Room
{
    private int number;
    private Collection<RoomOptions> options;
    private Category category;
    private Collection<Habitation> habitationCollection;
    private Collection<RoomStatus> status;
}
