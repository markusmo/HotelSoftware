package hotelsoftware.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

/**
 * Setzt System Eigenschaften aus dem
 * <code>resources/roomanizer.cfg</code> file. Name der Eigenschaft ist mit
 * <code>;</code> von dem Wert getrennt.
 *
 * @author mohi
 */
public class RoomanizerProperties
{

    /**
     * Liest aus dem <code>resources/roomanizer.cfg</code> alle Eigenschaften,
     * die in diesem File geschrieben stehen.
     * @throws IOException
     */
    public static void setPropertiesFromConfig() throws IOException
    {
        URL config = RoomanizerProperties.class.getClassLoader().getResource(
                "resources/roomanizer.cfg");
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                config.openStream()));
        String line = new String();
        while ((line = reader.readLine()) != null)
        {
            String[] split = line.split(";");
            addProperty(split[0], split[1]);
        }
        reader.close();
    }
    
    /**
     * Fügt eine neue Eigentschaft hinzu
     * @param name
     * Name der Eigentschaft, z.B.: <code>roomanizer.numerofclients</code>
     * @param value 
     * Wert der Eigentschaft, z.B.: <code>10</code>
     */
    public static void addProperty(String name, String value)
    {
        Properties properties = System.getProperties();
        properties.setProperty(name, value);
    }
}
