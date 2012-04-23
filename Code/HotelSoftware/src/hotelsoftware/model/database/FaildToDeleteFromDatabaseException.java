/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database;

/**
 * Diese Fehlermeldung tritt auf, wenn ein Fehler bei der Löschung eines Datensatzes in der Datenbank auftritt.
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
