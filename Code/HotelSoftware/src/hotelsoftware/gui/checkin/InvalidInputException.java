/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.checkin;

/**
 *
 * @author Dunst
 */
class InvalidInputException extends Exception
{
    private String input;
    
    public InvalidInputException(String input)
    {
        this.input = input;
    }
    
    public String getInput()
    {
        return input;
    }
}
