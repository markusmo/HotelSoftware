/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.adapter;

/**
 *
 * @author Tobias
 */
public class StatusAdapter extends at.fhv.roomanizer.domain.room.Status implements hotelsoftware.adapter.Adapter{

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public Object getOurType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setOurType(Object type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
