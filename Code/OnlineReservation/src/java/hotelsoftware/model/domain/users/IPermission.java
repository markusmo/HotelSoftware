/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.users;

/**
 *
 * @author mohi
 */
public interface IPermission
{

    Integer getId();

    String getName();

    int hashCode();

    void setId(Integer id);

    void setName(String permission);
    
}
