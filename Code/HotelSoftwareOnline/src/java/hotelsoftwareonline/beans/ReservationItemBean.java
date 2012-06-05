/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftwareonline.beans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class ReservationItemBean implements Serializable
{
    private String category;
    private int amount;
    private ArrayList<String> extraServices;
    private String boardCategory;
    
    public ArrayList<String> getExtraServices()
    {
        return extraServices;
    }

    public void setExtraServices(ArrayList<String> extraServices)
    {
        this.extraServices = extraServices;
    }
    
    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }
    
    public String getBoardCategory()
    {
        return boardCategory;
    }

    public void setBoardCategory(String boardCategory)
    {
        this.boardCategory = boardCategory;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }
}
