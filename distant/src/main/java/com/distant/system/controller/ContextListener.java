package com.distant.system.controller;

import com.distant.system.dao.conection.ConnectionPool;
import com.distant.system.dao.conection.ConnectionPoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * The class for instantiating and destroying the connection pool at the
 * deployment and undeployment of application respectively.
 */
public class ContextListener implements ServletContextListener {


    /** Properties file with data base and connection pool configurations */
    private static final String DB_PROPERTIES_FILE = "db";

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            ConnectionPool.getInstance().initialize(DB_PROPERTIES_FILE);
        } catch (ConnectionPoolException e) {
            //loger
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        try {
            ConnectionPool.getInstance().destroy();
        } catch (ConnectionPoolException e) {
           //logger
        }
    }

}
