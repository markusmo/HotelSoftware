/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinereservation.validator;

/**
 *
 * @author Tobias
 */
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

@FacesValidator("onlinereservation.validator")
public class RoomValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
