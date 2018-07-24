package com.distant.system.controller;

import com.distant.system.dao.connection.ConnectionPool;
import com.distant.system.dao.connection.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * The class for instantiating and destroying the connection pool at the
 * deployment and undeployment of application respectively.
 */
public class ContextListener implements ServletContextListener {


    /** Properties file with data base and connection pool configurations */
    private static final String DB_PROPERTIES_FILE = "db";
    private static final Logger LOGGER = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            ConnectionPool.getInstance().initialize(DB_PROPERTIES_FILE);
        } catch (ConnectionPoolException e) {
            LOGGER.error("Connection pool exception during init");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        try {
            ConnectionPool.getInstance().destroy();
        } catch (ConnectionPoolException e) {
            LOGGER.error("Connection pool exception during destroy");
        }
    }

}
