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

@FacesValidator("onlinereservation.validator")
public class RoomValidator implements Validator{
    
    Date start;
    Date end;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        RoomCategory luxury = new RoomCategory();
        Collection<IRoom> collection = luxury.getFreeRooms(start, end);
    }
    
}
