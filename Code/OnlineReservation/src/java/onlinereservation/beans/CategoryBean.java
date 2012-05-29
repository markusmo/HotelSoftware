/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinereservation.beans;

import java.io.Serializable;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class CategoryBean implements Serializable
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
