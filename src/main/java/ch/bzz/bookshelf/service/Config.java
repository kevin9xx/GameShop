package ch.bzz.bookshelf.service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;


@ApplicationPath("/resource")
public class Config extends Application {

    private static final String PROPERTIES_FILENAME = "game.properties";
    private static Properties properties = null;


    @Override
    public Set<Class<?>> getClasses() {
        HashSet providers = new HashSet<Class<?>>();
        providers.add(TestService.class);
        providers.add(GameService.class);
        providers.add(PublisherService.class);
        return providers;
    }


    public static String getProperty(String property) {
        if (Config.properties == null) {
            setProperties(new Properties());
            readProperties();
        }
        String value = Config.properties.getProperty(property);
        if (value == null) return "";
        return getResourcePath() + File.separator + value;
    }


    private static Path getResourcePath() {
        String pathname = Config.class.getClassLoader().getResource(PROPERTIES_FILENAME).getFile();
        Path path = new File(pathname).toPath().getParent();
        return path;
    }


    private static void readProperties() {

        InputStream inputStream = null;
        try {
            inputStream = Config.class.getClassLoader().getResourceAsStream(PROPERTIES_FILENAME);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {

            try {
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
    }


    private static void setProperties(Properties properties) {
        Config.properties = properties;
    }
}