/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.beans;

import java.io.Serializable;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class CompanyTypeBean implements Serializable
{
    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
