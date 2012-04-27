/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.util.test;

/**
 *
 * @author Johannes
 */
public class CurrentWeather extends Weather
{
    private String temp;
    private String humidity;
    private String wind_condition;

    public CurrentWeather()
    {
    }

    public CurrentWeather(String city)
    {
        super(city);
    }

    public String getHumidity()
    {
        return humidity;
    }

    public void setHumidity(String humidity)
    {
        this.humidity = humidity;
    }

    public String getTemp()
    {
        return temp;
    }

    public void setTemp(String temp)
    {
        this.temp = temp;
    }

    public String getWind_condition()
    {
        return wind_condition;
    }

    public void setWind_condition(String wind_condition)
    {
        this.wind_condition = wind_condition;
    }

    @Override
    public String toString()
    {
        return "\nAktuell: " + super.getCondition() + "\n" +  humidity + "\n" + wind_condition;
    }
}
