package com.distant.system.factory;

import com.distant.system.dao.*;
import com.distant.system.dao.mysql.impl.InMemoryMarkDao;
import com.distant.system.dao.mysql.impl.InMemoryQuestionDao;
import com.distant.system.dao.mysql.impl.InMemorySubjectDao;
import com.distant.system.dao.mysql.impl.InMemoryUserDao;

public class FactoryImpl extends Factory {
    @Override
    public QuestionDao createQuestionDao() {
        return new InMemoryQuestionDao();
    }

    @Override
    public UserDao createStudentDao() {
        return new InMemoryUserDao();
    }

    @Override
    public SubjectDao createSubjectDao() {
        return new InMemorySubjectDao();
    }

    public MarkDao createMarkDao() {
        return new InMemoryMarkDao();
    }
}
