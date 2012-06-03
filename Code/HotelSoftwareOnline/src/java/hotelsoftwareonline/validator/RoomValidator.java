/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.validator;

/**
 *
 * @author Tobias
 */
import hotelsoftware.model.database.manager.RoomManager;
import hotelsoftware.model.domain.room.IRoomCategory;
import hotelsoftwareonline.beans.ReservationBean;
import hotelsoftwareonline.beans.ReservationItemBean;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

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

        if (value == null){
            throw new ValidatorException(new FacesMessage("No rooms were chosen"));
        }
        
        bean = (ReservationBean)value;
        manager = RoomManager.getInstance();
        map = new HashMap();
        
        start = convertToDate(bean.getStartDate());
        end = convertToDate(bean.getEndDate());
        
        for (IRoomCategory tempCategory : manager.getAllCategories()){
            map.put(tempCategory.getId(), 0);
        }
        
        for (ReservationItemBean r : bean.getItems()){
            
            category = manager.getCategoryByName(r.getCategory().getName());
            amountOfCurrentCategory = (Integer) map.get(category.getId());
            
            if ((r.getAmount() + amountOfCurrentCategory) > category.getFreeRooms(start, end).size()){
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
    
    private Date convertToDate(String date)
    {
        String[] dates = date.split("/");
        Date d = new Date();
        Calendar c = Calendar.getInstance();
        c.set(Integer.parseInt(dates[2]), Integer.parseInt(dates[0]), Integer.parseInt(dates[1]));
        d.setTime(c.getTimeInMillis());
        return d;
    }
    
}
