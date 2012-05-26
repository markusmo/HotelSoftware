/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.service;

import hotelsoftware.controller.data.service.ExtraServiceData;

/**
 *
 * @author Kno
 */
public interface IExtraService extends IService, ExtraServiceData
{
    void setName(String name);
}
