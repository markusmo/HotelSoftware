/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.beans;

import hotelsoftware.model.domain.parties.ICompanyType;
import java.io.Serializable;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class CompanyTypeBean implements Serializable
{
    private ICompanyType type;

    public String getName()
    {
        return type.getName();
    }

    public void setName(String name)
    {
        this.type.setName(name);
    }

    public ICompanyType getType()
    {
        return type;
    }

    public void setType(ICompanyType type)
    {
        this.type = type;
    }
}
