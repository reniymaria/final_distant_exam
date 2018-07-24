package com.distant.system.service.impl;

import com.distant.system.dao.DAOFactory;
import com.distant.system.dao.DAOManager;
import com.distant.system.dao.LanguageDao;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.service.LanguageService;
import com.distant.system.service.exception.ServiceException;

public class LanguageServiceImpl implements LanguageService {

    private static final DAOManager daoManager = DAOFactory.getFactory().getMainDAOManager();
    private static final LanguageDao languageDao = daoManager.getLanguageDao();

    @Override
    public int findId(String language) throws ServiceException {
        try {
            return languageDao.findId(language);
        } catch (DaoException e) {
            throw new ServiceException("Exception during finding language", e);
        }
    }

    @Override
    public String getLanguageById(int id) throws ServiceException  {
        try {
            return languageDao.getLanguageById(id);
        } catch (DaoException e) {
            throw new ServiceException("Exception during getting language", e);
        }

    }

}
