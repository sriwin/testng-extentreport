package com.sriwin.automation.config;

import com.sriwin.automation.exceptions.CoreException;
import com.sriwin.automation.utils.ExceptionUtil;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
    private static Properties properties = null;
    private static PropertyManager ourInstance = new PropertyManager();
    private static final Logger LOGGER = Logger.getLogger(PropertyManager.class);

    private PropertyManager() {
        properties = new Properties();
    }

    public static PropertyManager getInstance() {
        return ourInstance;
    }

    public String getPropertyValue(String key) {
        String value = properties.getProperty(key.trim());
        return ((value == null) ? null : value.trim());
    }


    public String getStringValue(String key) {
        String value = properties.getProperty(key.trim());
        return value == null ? null : value.trim();
    }

    public boolean getBooleanValue(String key) {
        String value = getStringValue(key.trim()).trim();
        if (value != null && value.trim().length() > 0) {
            if (value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("true")) {
                return true;
            } else if (value.equalsIgnoreCase("no") || value.equalsIgnoreCase("false")) {
                return false;
            }
        }
        return false;
    }

    public int getIntValue(String key) {
        String value = properties.getProperty(key.trim()).trim();
        return Integer.parseInt(value);
    }


    public void addProperty(String key, String value) {
        properties.put(key, value);
    }

    public void removeProperty(String key) {
        properties.remove(key);
    }


    public void loadProperties(String filePath) throws CoreException {
        try {
            if (properties == null) {
                properties = new Properties();
            }
            InputStream file = new FileInputStream(new File(filePath));
            properties.load(file);
        } catch (Exception e) {
            throw ExceptionUtil.handleCoreException(e);
        }
    }

    public void loadProperties(Properties properties) throws CoreException {
        try {
            properties.putAll(properties);
        } catch (Exception e) {
            throw ExceptionUtil.handleCoreException(e);
        }
    }
}