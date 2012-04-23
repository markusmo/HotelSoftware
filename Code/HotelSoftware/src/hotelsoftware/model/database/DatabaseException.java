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

    /**
     * Creates a new instance of <code>DatabaseException</code> without detail message.
     */
    public DatabaseException()
    {
    }

    /**
     * Constructs an instance of <code>DatabaseException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DatabaseException(String msg)
    {
        super(msg);
    }
}
