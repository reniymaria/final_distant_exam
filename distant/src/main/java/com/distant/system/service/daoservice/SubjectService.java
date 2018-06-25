package com.distant.system.service.daoservice;

import com.distant.system.dao.DAOFactory;
import com.distant.system.dao.DAOManager;
import com.distant.system.dao.SubjectDao;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.factory.Factory;
import com.distant.system.factory.FactoryImpl;

public class SubjectService {

    private static final DAOManager daoManager = DAOFactory.getFactory().getMainDAOManager();
    private static final SubjectDao subjectDao = daoManager.getSubjectDao();

   public int getSubjectId(String subject) throws DaoException {
       return subjectDao.getSubjectId(subject);
    }
}
