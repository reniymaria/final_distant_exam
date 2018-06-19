package com.distant.system.factory;

import com.distant.system.dao.MarkDao;
import com.distant.system.dao.QuestionDao;
import com.distant.system.dao.SubjectDao;
import com.distant.system.dao.UserDao;

public abstract class Factory {
    public abstract QuestionDao createQuestionDao();

    public abstract UserDao createStudentDao();

    public abstract SubjectDao createSubjectDao();

    public  abstract MarkDao createMarkDao();
}
