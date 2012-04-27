/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui;

import java.util.LinkedList;

/**
 *
 * @author Johannes
 */
public class GuiController
{
    private LinkedList<UseCaseController> controller = new LinkedList<UseCaseController>();

    private static class GuiControllerHolder
    {
        private static final GuiController INSTANCE = new GuiController();
    }

    public static GuiController getInstance()
    {
        return GuiControllerHolder.INSTANCE;
    }

    public void addUseCaseController(UseCaseController controller)
    {
        this.controller.add(controller);
    }
    public boolean remoceUseCaseController(UseCaseController c)
    {
        return this.controller.remove(c);
    }

    public boolean checkStateForSwitching()
    {
        for (UseCaseController c : controller)
        {
            if (!c.isInSwitchingState())
            {
                return false;

            }
        }
        return true;
    }
}
