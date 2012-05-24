/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.controller.login;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class PermissionNotFoundException extends Exception
{
    /**
     * Creates a new instance of
     * <code>PermissionNotFoundException</code> without detail message.
     */
    public PermissionNotFoundException()
    {
    }

    /**
     * Constructs an instance of
     * <code>PermissionNotFoundException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public PermissionNotFoundException(String msg)
    {
        super(msg);
    }
}
