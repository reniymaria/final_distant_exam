package com.distant.system.service.daoservice;

import com.distant.system.dao.DAOFactory;
import com.distant.system.dao.DAOManager;
import com.distant.system.dao.SubjectDao;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Subject;

import java.util.List;

public class SubjectService {

    private static final DAOManager daoManager = DAOFactory.getFactory().getMainDAOManager();
    private static final SubjectDao subjectDao = daoManager.getSubjectDao();

    public int getSubjectId(String subject) throws DaoException {
        return subjectDao.getSubjectId(subject);
    }

    public int getSizeStudentAvailableSubjects(int studentId) throws DaoException {
        return subjectDao.getSizeStudentAvailableSubjects(studentId);
    }

    public List<Subject> numberOfStudentSubjects(int studentId, int offset, int records) throws DaoException {
        return subjectDao.numberOfStudentSubjects(studentId, offset, records);
    }

    public List<Subject> numberOfAllSubjects(int offset, int records) throws DaoException {
        return subjectDao.numberOfAllSubjects(offset, records);
    }

    public int getSizeAllSubjects() throws DaoException {
        return subjectDao.getSizeAllSubjects();
    }

    public void deleteSubject(int subjectId) throws DaoException {
        subjectDao.deleteSubject(subjectId);
    }

    public void addSubject(String subject) throws DaoException {
        subjectDao.addSubject(subject);
    }

    public void updateSubject(int subjectId, String value) throws DaoException {
        subjectDao.updateSubject(subjectId, value);
    }

    public String getSubjectById(int id) throws DaoException {
        return subjectDao.getSubjectById(id);
    }
}
