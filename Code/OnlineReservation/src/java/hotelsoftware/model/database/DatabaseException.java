/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database;

/**
 *Diese Fehlermeldung tritt auf, wenn ein es ein Problem mit der Datenbank gibt
 * @author mohi
 */
public class DatabaseException extends Exception
{
    public DatabaseException()
    {
    }
    public DatabaseException(String msg)
    {
        super(msg);
    }
}
