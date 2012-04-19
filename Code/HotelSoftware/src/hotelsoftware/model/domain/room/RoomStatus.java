/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

/**
 *
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class RoomStatus implements RoomStatusData
{
    private String statusName;
    private int id;

    @Override
    public String getStatusName()
    {
        return statusName;
    }

    public void setStatusName(String statusName)
    {
        this.statusName = statusName;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    
    

   
    
    
}
