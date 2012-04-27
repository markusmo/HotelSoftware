package hotelsoftware.support;

/**
 * Wird geworfen, wenn eine Befugnis nicht gefunden wurde.
 * @author Dunst
 */
public class PermissionNotFoundException extends Exception
{
    private String permissionName;

    public String getPermissionName()
    {
        return permissionName;
    }
    
    public PermissionNotFoundException(String name)
    {
        this.permissionName = name;
    }
    
}
