/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.util.test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Johannes
 */
public class Weather
{
    private String condition;
    private ImageIcon icon;
    private Document doc;

    public Weather()
    {
        this("dornbirn");
    }

    public Weather(String city)
    {
        try
        {
            String googleWeatherUrl = "http://www.google.de/ig/api?weather=" + city + "&hl=de";

            String xmlString = "";
            String line = "";

            LinkedList<Weather> list = new LinkedList<Weather>();
            try
            {
                URL url = new URL(googleWeatherUrl);
                InputStream in = url.openStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, Charset.forName("Latin1")));
                while ((line = bufferedReader.readLine()) != null)
                {
                    xmlString += line;
                }
            }
            catch (MalformedURLException e)
            {
            }
            catch (IOException e)
            {
            }
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource source = new InputSource(new StringReader(xmlString));
            this.doc = builder.parse(source);
            NodeList nodeLst = doc.getElementsByTagName("forecast_conditions");
        }
        catch (SAXException ex)
        {
            Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ParserConfigurationException ex)
        {
            Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }

    public List<Weather> getCurrent()
    {

        NodeList nodeLst = doc.getElementsByTagName("current_conditions");
        LinkedList<Weather> list = new LinkedList<Weather>();
        for (int s = 0; s < nodeLst.getLength(); s++)
        {
            CurrentWeather w = new CurrentWeather();
            Node fstNode = nodeLst.item(s);

            if (fstNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element fstElmnt = (Element) fstNode;
                w.setCondition(fstElmnt.getElementsByTagName("condition").item(0).getAttributes().getNamedItem("data").getNodeValue());
                w.setIcon(fstElmnt.getElementsByTagName("icon").item(0).getAttributes().getNamedItem("data").getNodeValue());
                w.setHumidity(fstElmnt.getElementsByTagName("humidity").item(0).getAttributes().getNamedItem("data").getNodeValue());
                w.setTemp(fstElmnt.getElementsByTagName("temp_c").item(0).getAttributes().getNamedItem("data").getNodeValue());
                w.setWind_condition(fstElmnt.getElementsByTagName("wind_condition").item(0).getAttributes().getNamedItem("data").getNodeValue());
                list.add(w);
            }

        }
        return list;
    }

    public List<Weather> getForeCasts()
    {
        NodeList nodeLst = doc.getElementsByTagName("forecast_conditions");
        LinkedList<Weather> list = new LinkedList<Weather>();;
        for (int s = 0; s < nodeLst.getLength(); s++)
        {
            ForeCastWeather w = new ForeCastWeather();
            Node fstNode = nodeLst.item(s);

            if (fstNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element fstElmnt = (Element) fstNode;
                w.setCondition(fstElmnt.getElementsByTagName("condition").item(0).getAttributes().getNamedItem("data").getNodeValue());
                w.setIcon(fstElmnt.getElementsByTagName("icon").item(0).getAttributes().getNamedItem("data").getNodeValue());
                w.setHigh(fstElmnt.getElementsByTagName("high").item(0).getAttributes().getNamedItem("data").getNodeValue());
                w.setLow(fstElmnt.getElementsByTagName("low").item(0).getAttributes().getNamedItem("data").getNodeValue());
                w.setDay_of_week(fstElmnt.getElementsByTagName("day_of_week").item(0).getAttributes().getNamedItem("data").getNodeValue());
                list.add(w);
            }

        }
        return list;
    }

    public String getCondition()
    {
        return condition;
    }

    public void setCondition(String condition)
    {
        this.condition = condition;
    }

    public ImageIcon getIcon()
    {
        return icon;
    }

    public void setIcon(ImageIcon icon)
    {
        this.icon = icon;
    }

    public void setIcon(String iconname)
    {
        String iconUrl = iconname.split("/")[iconname.split("/").length - 1];
        String iconName = iconUrl.replace(".gif", "");
        String path = "resources/images/weathericons/" + iconName + ".png";
        this.icon = new ImageIcon(Weather.class.getClassLoader().getResource(path));
        //this.icon = new ImageIcon(Weather.class.getClassLoader().getResource("resources/images/weathericons/weather_sunny-40.gif"));
    }
}