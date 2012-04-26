/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.util.test;

import javax.swing.ImageIcon;

/**
 *
 * @author Johannes
 */
public class ForeCastWeather extends Weather
{
    private String low;
    private String high;
    private String day_of_week;

    public ForeCastWeather(){}
    public ForeCastWeather(String city)
    {
        super(city);
    }

    public String getDay_of_week()
    {
        return day_of_week;
    }

    public void setDay_of_week(String day_of_week)
    {
        this.day_of_week = day_of_week;
    }

    public String getHigh()
    {
        return high;
    }

    public void setHigh(String high)
    {
        this.high = high;
    }

    public String getLow()
    {
        return low;
    }

    public void setLow(String low)
    {
        this.low = low;
    }
}
