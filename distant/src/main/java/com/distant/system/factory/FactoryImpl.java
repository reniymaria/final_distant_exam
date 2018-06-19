package com.distant.system.factory;

import com.distant.system.dao.*;

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
