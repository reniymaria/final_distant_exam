package com.distant.system.service.daoservice;

import com.distant.system.dao.DAOFactory;
import com.distant.system.dao.DAOManager;
import com.distant.system.dao.LanguageDao;
import com.distant.system.dao.exception.DaoException;

public class LanguageService {

    private static final DAOManager daoManager = DAOFactory.getFactory().getMainDAOManager();
    private static final LanguageDao languageDao = daoManager.getLanguageDao();

    public int findId(String language) throws DaoException {
        return languageDao.findId(language);

    }

}
