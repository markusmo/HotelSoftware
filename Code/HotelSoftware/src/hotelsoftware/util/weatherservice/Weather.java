/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.util.weatherservice;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
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
 * Diese Klasse fragt Online mit der IP-Adresse die Position des Rechners ab
 * und haelt sich den Stadtnamen, mit diesem fragt es auf Google Weather
 * dann das aktuelle Wetter ab.
 *
 * @author Johannes
 */
public class Weather
{
    protected static String cityName;
    private String condition;
    private ImageIcon icon;
    private static Document doc;
    private static boolean init;
    private static Date now = new Date();
    protected final static String LANG = "en";

    static
    {
        findCity();
        setDoc();
        init = true;
    }

    public Weather()
    {
    }

    /**
     * Überprüft ob neue Wetterdaten geholt werden müssen. Dies ist alle 15 Minuten der Fall.
     *
     * @return
     */
    private static boolean hasToUpdate()
    {
        if (((new Date()).getTime() - now.getTime()) > 900000)
        {
            now = new Date();
            return true;
        }
        return false;
    }

    /**
     * Holt sich das Wetter und haelt sich das XML, das von Google generiert wird
     */
    private static void setDoc()
    {
        try
        {
            String googleWeatherUrl = "http://www.google.de/ig/api?weather=" + cityName + "&hl=" + LANG;

            StringBuilder xmlBuilder = new StringBuilder();

            try
            {
                String line = "";
                URL url = new URL(googleWeatherUrl);
                InputStream in = url.openStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, Charset.forName("Latin1")));
                while ((line = bufferedReader.readLine()) != null)
                {
                    xmlBuilder.append(line);
                }
            }
            catch (MalformedURLException e)
            {
            }
            catch (IOException e)
            {
            }
            catch (Throwable t)
            {
            }
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource source = new InputSource(new StringReader(xmlBuilder.toString()));
            doc = builder.parse(source);
            init = true;


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

    /**
     * Diese Methode gibt das aktuelle Wetter des jetzigen Tages aus
     *
     * @return
     * die jetzige Wettersituation
     */
    public static CurrentWeather getCurrent()
    {



        if ((!init) || hasToUpdate())
        {
            findCity();
            setDoc();
            init = true;
        }
        LinkedList<CurrentWeather> list = new LinkedList<CurrentWeather>();
        try
        {
            NodeList nodeLst = doc.getElementsByTagName("current_conditions");

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

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return list.get(0);
    }

    /**
     * Diese Methode gibt eine Liste von Wettervorhersagen fuer die naechesten Tage aus
     *
     * @return
     * eine Liste von Wettervorhersagen.
     */
    public static List<Weather> getForeCasts()
    {
        if ((!init) || hasToUpdate())
        {
            findCity();
            setDoc();
            init = true;
        }
        NodeList nodeLst = doc.getElementsByTagName("forecast_conditions");
        LinkedList<Weather> list = new LinkedList<Weather>();
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

    /**
     * Diese Methode fragt die externe IP ab und findet dann die Adresse zu dieser heraus.
     * Mit dieser Adresse wird dann die Stadt ermittelt und gesetzt.
     */
    private static void findCity()
    {
        BufferedReader ipin = null;
        BufferedReader cityin = null;
        String city = "";
        try
        {
            /*
             * URL whatismyip = new URL("http://automation.whatismyip.com/n09230945.asp");
             * ipin = new BufferedReader(new InputStreamReader(
             * whatismyip.openStream()));
             * String ip = ipin.readLine();
             */

            URL urlcity = new URL("http://freegeoip.net/xml/");//+ ip);
            cityin = new BufferedReader(new InputStreamReader(urlcity.openStream()));
            StringBuilder builder = new StringBuilder();
            String xml = "";
            while ((xml = cityin.readLine()) != null)
            {
                builder.append(xml);
            }
            DocumentBuilder docbuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource source = new InputSource(new StringReader(builder.toString()));
            Document document = docbuilder.parse(source);
            NodeList elementsByTagName = document.getElementsByTagName("City");
            Node item = elementsByTagName.item(0);
            city = item.getChildNodes().item(0).getNodeValue();


        }
        catch (SAXException ex)
        {
            Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ParserConfigurationException ex)
        {
            Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Throwable t)
        {
        }
        finally
        {
            try
            {
                // ipin.close();
                cityin.close();


            }
            catch (IOException ex)
            {
                Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cityName = city;
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

    /**
     * Diese Methode setzt das Icon fuer das Darstellen auf der Gui
     *
     * @param iconname der Name des Icons, von Google
     */
    public void setIcon(String iconname)
    {
        String iconUrl = iconname.split("/")[iconname.split("/").length - 1];
        String iconName = iconUrl.replace(".gif", "");
        String path = "resources/images/weathericons/" + iconName + ".png";

        URL url = Weather.class.getClassLoader().getResource(path);
        if (url == null)
        {
            url = Weather.class.getClassLoader().getResource("resources/images/weathericons/default.png");
        }
        this.icon = new ImageIcon(url);
    }

    protected int fahrenheit2celsius(int fahrenheit)
    {
        return (int) ((5 / 9.0) * (fahrenheit - 32.0));
    }
}
