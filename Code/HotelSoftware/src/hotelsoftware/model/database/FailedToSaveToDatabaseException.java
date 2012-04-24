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
     * Neue Instanz von <code>FailedToSaveToDatabaseException</code>
     */
    public FailedToSaveToDatabaseException()
    {
    }

    /**
     * Neue Instanz von <code>FailedToSaveToDatabaseException</code> mit einer Fehlermeldung
     * @param msg
     * Die Fehlermeldung, die weitergereicht werden soll.
     */
    public FailedToSaveToDatabaseException(String msg)
    {
        super(msg);
    }
}
