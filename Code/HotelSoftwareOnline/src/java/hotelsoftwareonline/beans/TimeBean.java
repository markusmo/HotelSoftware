/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.beans;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Johannes
 */
@SessionScoped
@ManagedBean(name="time")
public class TimeBean
{
    public String getTime()
    {
        return (new Date()).toString();
    }
}
