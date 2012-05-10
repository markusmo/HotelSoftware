/*{
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.database.service;

import java.util.Collection;
import java.util.LinkedList;
import hotelsoftware.model.domain.service.Habitation;
import java.util.List;
/**
 *
 * @author Kno
 */
public class TestClassLOESCHENbitte {
    
    
    public static void main(String[] args)
    {
     List<Habitation> h = (List)Habitation.searchHabitations(null,null,2);   
     if(h==null)
         System.out.println("NOT FOUND");
     else
     for(Habitation x : h)
     {
         System.out.println("a: "+ x.getServiceName());
     }
    
    }
}
