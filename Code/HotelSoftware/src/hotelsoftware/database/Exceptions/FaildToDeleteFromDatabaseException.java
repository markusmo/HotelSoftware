/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.database.Exceptions;

/**
 *
 * @author mohi
 */
public class FaildToDeleteFromDatabaseException extends Exception
{

    /**
     * Creates a new instance of <code>FaildToDeleteFromDatabaseException</code> without detail message.
     */
    public FaildToDeleteFromDatabaseException()
    {
    }

    /**
     * Constructs an instance of <code>FaildToDeleteFromDatabaseException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public FaildToDeleteFromDatabaseException(String msg)
    {
        super(msg);
    }
}
