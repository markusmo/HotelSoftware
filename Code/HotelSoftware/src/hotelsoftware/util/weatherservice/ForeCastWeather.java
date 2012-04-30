/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.util.weatherservice;

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

    public ForeCastWeather()
    {
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
        if (LANG.equals("en"))
        {
            high = "" + fahrenheit2celsius(Integer.parseInt(high));
        }
        this.high = high;
    }

    public String getLow()
    {
        return low;
    }

    public void setLow(String low)
    {
        if (LANG.equals("en"))
        {
            low = "" + fahrenheit2celsius(Integer.parseInt(low));
        }
        this.low = low;
    }

    @Override
    public String toString()
    {
        return day_of_week + "\n" + super.getCondition() + "\nMin: " + low + "° Max: " + high + "°";
    }
}
