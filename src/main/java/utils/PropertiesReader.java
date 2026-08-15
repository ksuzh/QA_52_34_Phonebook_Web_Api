package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader {
    public static String getProperty(String filename, String key) {
        Properties properties = new Properties();
        try(FileInputStream file =
                    new FileInputStream("src/test/properties" + File.separator + filename)){
            properties.load(file);
            return properties.getProperty(key);
        }catch(Exception e){
            System.out.println("created exception");
            e.printStackTrace();
            return null;
        }

    }
}
