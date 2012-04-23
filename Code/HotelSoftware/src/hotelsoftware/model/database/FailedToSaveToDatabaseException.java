/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database;

/**
 *Diese Fehlermeldung entsteht wenn das speichern in die Datenbank fehlschlaegt
 * @author mohi
 */
public class FailedToSaveToDatabaseException extends DatabaseException
{

    /**
     * Creates a new instance of <code>FailedToSaveToDatabaseException</code> without detail message.
     */
    public FailedToSaveToDatabaseException()
    {
    }

    /**
     * Constructs an instance of <code>FailedToSaveToDatabaseException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public FailedToSaveToDatabaseException(String msg)
    {
        super(msg);
    }
}
