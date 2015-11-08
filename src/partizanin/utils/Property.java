package partizanin.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with Intellij IDEA.
 * Project path: VKprojectFx.
 * Date: 07.11.2015.
 * Time: 17:37.
 * To change this template use File|Setting|Editor|File and Code Templates.
 */
public class Property {
    public static void main(String[] args) {
        Properties prop = new Properties();
        String fileName = "app.properties";
        InputStream is = null;
        try {
            is = new FileInputStream(fileName);
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(prop.getProperty("app.name"));
        System.out.println(prop.getProperty("app.version"));

        System.out.println(prop.getProperty("app.vendor", "Java"));

        prop.setProperty("app.name", "JavaFx");
        prop.setProperty("app.version", "11111111111");
        prop.setProperty("app.vendor", "Tapolsky");

        System.out.println(prop.getProperty("app.name"));
        System.out.println(prop.getProperty("app.version"));

        System.out.println(prop.getProperty("app.vendor", "Java"));
    }
}
