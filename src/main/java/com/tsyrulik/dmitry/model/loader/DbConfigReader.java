package com.tsyrulik.dmitry.model.loader;

import com.tsyrulik.dmitry.model.exception.LoaderFitnessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbConfigReader {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String CONFIG = "dbConfig.properties";

    public DbConfig readProperties()  {
        ClassLoader classLoader = DbConfigReader.class.getClassLoader();
        InputStream propStream = classLoader.getResourceAsStream(CONFIG);
        if (propStream == null) {
            LOGGER.error("File not found.");
            throw new LoaderFitnessException("File not found.");
        }

        Properties info;
        try {
            info = new Properties();
            info.load(propStream);
        } catch (IOException e) {
            LOGGER.error("File reading error.");
            throw new LoaderFitnessException("File reading error.");
        }

        return initInfo(info);
    }

    private DbConfig initInfo(Properties properties) {
        DbConfigCreator creator = new DbConfigCreator();
        return creator.create(properties);
    }
}