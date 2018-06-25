package com.distant.system.dao;


import com.distant.system.dao.exception.UnsupportedStoradgeTypeException;
import com.distant.system.dao.mysql.MySqlDAOFactory;

/**
 * An abstract factory for creating transactional and not transactional DAO
 * factories.
 */
public abstract class DAOFactory {

    /**
     * Returns the factory. By default it's MySql factory
     */
    public static DAOFactory getFactory() {
        return getFactory(StoradgeTypes.MySql);
    }

    /**
     * Gets the factory by {@link StoradgeTypes}.
     *
     * @param type
     *            the type of factory
     * @return the factory defined by the {@code type} or throws
     *         {@codeUnsupportedStoradgeTypeException} if factory for this
     *         {@code type} is not defined
     */
    public static DAOFactory getFactory(StoradgeTypes type) {
        switch (type) {
            case MySql:
                return new MySqlDAOFactory();
            default:
                throw new UnsupportedStoradgeTypeException("Storage type wasn't chosen correctly");
        }
    }

    public abstract DAOManager getMainDAOManager();


}
