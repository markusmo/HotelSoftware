/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.validator;

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
import java.util.HashMap;
import javax.faces.application.FacesMessage;
import hotelsoftwareonline.beans.ReservationBean;
import hotelsoftwareonline.beans.ReservationItemBean;

@FacesValidator("onlinereservation.validator")
public class RoomValidator implements Validator{
    
    private Date start;
    private Date end;
    private ReservationBean bean;
    private RoomManager manager;
    private IRoomCategory category;
    private static HashMap map;
    int amountOfCurrentCategory;
    
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
        map = new HashMap();
        
        for (IRoomCategory tempCategory : manager.getAllCategories()){
            map.put(tempCategory.getId(), 0);
        }
        
        for (ReservationItemBean r : bean.getItems()){
            
            category = manager.getCategoryByName(r.getCategory().getName());
            amountOfCurrentCategory = (Integer) map.get(category.getId());
            
            if ((r.getAmount() + amountOfCurrentCategory) > category.getFreeRooms(bean.getStartDate(), bean.getEndDate()).size()){
                throw new ValidatorException(new FacesMessage("Not enough free rooms available in category " + category.getName()));
            }
            
            map.put(category.getId(), r.getAmount() + amountOfCurrentCategory);
        }
    }
    
    public static void resetCurrentReservations(){
        if (map != null){
            map.clear();
        }
    }
    
}
