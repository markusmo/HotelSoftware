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
     * Neue Instanz von <code>FaildToDeleteFromDatabaseException</code> 
     */
    public FaildToDeleteFromDatabaseException()
    {
    }

    /**
     * Neue Instanz von <code>FaildToDeleteFromDatabaseException</code> mit Fehlermeldung
     * @param msg
     * Die Fehlermeldung, die weitergereicht werden soll.
     */
    public FaildToDeleteFromDatabaseException(String msg)
    {
        super(msg);
    }
}
