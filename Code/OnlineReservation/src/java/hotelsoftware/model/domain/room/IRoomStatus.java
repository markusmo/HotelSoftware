/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

/**
 *
 * @author Kno
 */
public interface IRoomStatus{

    Integer getId();

    String getStatusName();

    void setId(int id);

    void setStatusName(String statusName);
    
}
