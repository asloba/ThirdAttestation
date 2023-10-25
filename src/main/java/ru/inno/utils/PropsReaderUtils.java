package ru.inno.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


    public class PropsReaderUtils {
        static String getPropertyHandler(Properties property, String propertyKey, String defaultValue) {
            return System.getProperty(propertyKey) != null
                    ? System.getProperty(propertyKey)
                    : property.getProperty(propertyKey, defaultValue);
        }

        public static InputStream readFile(String fileName) throws FileNotFoundException {
            InputStream stream = TestProperties.class.getClassLoader().getResourceAsStream(fileName);
            if (stream == null) {
                throw new FileNotFoundException(fileName + " file not found in the classpath");
            }
            return stream;
        }

        static Properties readProperties(String fileName) {
            Properties properties = null;
            try (InputStream fis = readFile(fileName)) {
                properties = new Properties();
                properties.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (properties == null)
                try {
                    throw new FileNotFoundException(fileName + " file not found in the classpath");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            return properties;
        }

    }
