/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.service;

import hotelsoftware.database.model.Extraservices;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author Tobias
 */
public class ExtraServiceFacade {
    
    private ExtraServiceFacade() {
    }
    
    public static ExtraServiceFacade getInstance() {
        return ExtraServiceFacadeHolder.INSTANCE;
    }
    
    private static class ExtraServiceFacadeHolder {

        private static final ExtraServiceFacade INSTANCE = new ExtraServiceFacade();
    }
    
    public Collection<ExtraService> getAllExtraServices(){
        Collection<ExtraService> extraServices = new LinkedList<ExtraService>();
        
        for (Extraservices p : Extraservices.getExtraServices())
        {
            extraServices.add(new ExtraService(p.getName()));
        }
        return extraServices;
    }
    
    public ExtraService getExtraServiceByName(String name){
        return (ExtraService)Extraservices.getExtraServices();
    }
}
