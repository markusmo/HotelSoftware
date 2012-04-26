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
public class Weather
{
    private String condition;
    private String low;
    private String high;
    private String day_of_week;
    private String iconUrl;
    private ImageIcon icon;

    public Weather()
    {
    }

    public String getCondition()
    {
        return condition;
    }

    public void setCondition(String condition)
    {
        this.condition = condition;
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

    public String getIcon()
    {
        return iconUrl;
    }

    public void setIcon(String iconname)
    {
        this.iconUrl = iconname.split("/")[iconname.split("/").length - 1];
        String iconName = iconUrl.replace(".gif", "");
        String path = "resources/images/weathericons/" + iconName + ".png";
        this.icon = new ImageIcon(Weather.class.getClassLoader().getResource(path));
        //this.icon = new ImageIcon(Weather.class.getClassLoader().getResource("resources/images/weathericons/weather_sunny-40.gif"));
    }

    public String getLow()
    {
        return low;
    }

    public void setLow(String low)
    {
        this.low = low;
    }

    @Override
    public String toString()
    {
        return "Weather{" + "condition=" + condition + ", low=" + low + ", high=" + high + ", day_of_week=" + day_of_week + ", icon=" + iconUrl + '}';
    }
}
