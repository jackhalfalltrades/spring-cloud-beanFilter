package com.maat.bestbuy.integration.configuration;

import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicPropertyFactory;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PropertyManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyManager.class);

    private static synchronized void setProperty(String propertyName, Object propertyValues) {
        ConfigurationManager.getConfigInstance().setProperty(propertyName, propertyValues);
    }

    public static String getStringProperty(String propertyName, String defaultValue) {
        return DynamicPropertyFactory.getInstance().getStringProperty(propertyName, defaultValue).get();
    }

    public static Integer getIntProperty(String propertyName, int defaultValue) {
        return DynamicPropertyFactory.getInstance().getIntProperty(propertyName, defaultValue).get();
    }

    public static List<String> getStringListProperty(String propertyName, String delimeter) {
        String props = getProperty(propertyName);
        if(props != null)
            return Arrays.stream(props.split(delimeter))
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
        return new ArrayList<>();
    }

    public static String getProperty(String propertyName) {
        return getStringProperty(propertyName,null);
    }

}
