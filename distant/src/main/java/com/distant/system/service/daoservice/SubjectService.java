package com.distant.system.service.daoservice;

import com.distant.system.dao.SubjectDao;
import com.distant.system.factory.Factory;
import com.distant.system.factory.FactoryImpl;

public class SubjectService {

    private Factory factory = new FactoryImpl();
    private SubjectDao subjectDao = factory.createSubjectDao();

   public int getSubjectId(String subject) {
       return subjectDao.getSubjectId(subject);
    }
}
