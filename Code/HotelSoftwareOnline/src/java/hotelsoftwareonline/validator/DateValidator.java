/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.validator;

import hotelsoftwareonline.beans.ReservationBean;
import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Tobias
 */
@FacesValidator("onlinereservation.datevalidator")
public class DateValidator implements Validator{

    Date start;
    Date end;
    
    @Override
    public void validate(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
        String startdate = (String) value;
        // Obtain the component and submitted value of the confirm password component.
        UIInput confirmComponent = (UIInput) component.getAttributes().get(
                "enddate");
        String enddate = (String) confirmComponent.getSubmittedValue();
        
        end = convertToDate(enddate);
        start = convertToDate(startdate);
        
        if(start.before(Calendar.getInstance().getTime())){
            throw new ValidatorException(new FacesMessage("Start date in the past"));
        }
        
        if (end == null){
            throw new ValidatorException(new FacesMessage("No end date chosen"));
        }
        
        if (start.after(end)){
            throw new ValidatorException(new FacesMessage("Start date comes after end date"));
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
