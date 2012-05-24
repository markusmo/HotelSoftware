/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.controller.data.parties.CompanyTypeData;

/**
 *
 * @author Kno
 */
public interface ICompanyType extends CompanyTypeData{

    Integer getId();

    void setId(Integer id);

    void setName(String typ);
    
}
