/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinereservation.validator;

/**
 *
 * @author Tobias
 */
import hotelsoftware.model.domain.room.IRoom;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import hotelsoftware.model.domain.room.RoomCategory;
import java.util.Collection;
import java.util.Date;
import hotelsoftware.model.database.manager.RoomManager;
import hotelsoftware.model.domain.room.IRoomCategory;
import javax.faces.application.FacesMessage;
import onlinereservation.beans.ReservationBean;
import onlinereservation.beans.ReservationItemBean;

@FacesValidator("onlinereservation.validator")
public class RoomValidator implements Validator{
    
    Date start;
    Date end;
    ReservationBean bean;
    RoomManager manager;
    IRoomCategory category;
    
    /**
     * Überprüft ob genügend freie Räume in der gewünschten Kategorie verfügbar sind
     * @param context nicht benutzt
     * @param component nicht benutzt
     * @param value Die Reservation Bean, enthält die nötigen Informationen über die Reservierung
     * @throws ValidatorException 
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        bean = (ReservationBean)value;
        manager = RoomManager.getInstance();
        
        for (ReservationItemBean r : bean.getItems()){
            category = manager.getCategoryByName(r.getCategory().getName());
            if (r.getAmount() > category.getFreeRooms(bean.getStartDate(), bean.getEndDate()).size()){
                throw new ValidatorException(new FacesMessage("Not enough free rooms available in category " + category.getName()));
            }
        }
    }
    
}
