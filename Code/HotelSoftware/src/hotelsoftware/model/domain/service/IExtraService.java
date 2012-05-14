/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.service;

import hotelsoftware.controller.data.service.ExtraServiceData;
import hotelsoftware.controller.data.service.ServiceTypeData;

/**
 *
 * @author Kno
 */
public interface IExtraService extends IService, ExtraServiceData {

    String getName();

    String getServiceName();

    ServiceTypeData getServiceTypeData();

    void setName(String name);
    
}
