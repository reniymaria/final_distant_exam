package com.distant.system.dao.mysql;

import com.distant.system.dao.DAOFactory;
import com.distant.system.dao.DAOManager;


/**
 * A factory for creating transactional and non transactional MySqlDAO
 * factories.
 */
public class MySqlDAOFactory extends DAOFactory {

    private static final DAOManager daoManager = new MainDAOManager();

    @Override
    public DAOManager getMainDAOManager() {
        return daoManager;
    }


}