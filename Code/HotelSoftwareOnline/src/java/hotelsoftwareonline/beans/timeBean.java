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
@ManagedBean
public class timeBean
{
    public String getTime()
    {
        return (new Date()).toString();
    }
}
