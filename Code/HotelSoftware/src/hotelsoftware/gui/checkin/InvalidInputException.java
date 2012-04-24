package hotelsoftware.gui.checkin;

/**
 * Dieser Fehler weist auf eine falsche Eingab hin.
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
