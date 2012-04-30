package hotelsoftware.gui;

import hotelsoftware.controller.UseCaseController;
import java.util.LinkedList;

/**
 * 
 * MainGuiController Klasse, hält alle UseCasecontroller
 * @author Johannes
 *
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
    void back()
    {
        for (UseCaseController c : controller)
        {
            c.clear();
        }
    }
}
