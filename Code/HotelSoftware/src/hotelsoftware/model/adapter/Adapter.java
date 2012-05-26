/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.adapter;

/**
 *
 * @author Dunst
 */
public interface Adapter<T>
{
    T getOurType();
    void setOurType(T type);
}
