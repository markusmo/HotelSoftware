/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.service;

import hotelsoftware.database.model.DBExtraservices;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author Tobias
 */
public class ServiceFacade {
    
    private ServiceFacade() {
    }
    
    public static ServiceFacade getInstance() {
        return ServiceFacadeHolder.INSTANCE;
    }
    
    private static class ServiceFacadeHolder {

        private static final ServiceFacade INSTANCE = new ServiceFacade();
    }
    
    
}
