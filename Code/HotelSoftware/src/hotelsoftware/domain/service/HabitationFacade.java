/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.domain.service;

/**
 *
 * @author Tobias
 */
public class HabitationFacade {
    
    private HabitationFacade() {
    }
    
    public static HabitationFacade getInstance() {
        return HabitationFacadeHolder.INSTANCE;
    }
    
    private static class HabitationFacadeHolder {

        private static final HabitationFacade INSTANCE = new HabitationFacade();
    }
}
