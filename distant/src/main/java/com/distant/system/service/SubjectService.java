package com.distant.system.service;

import com.distant.system.dao.DAOFactory;
import com.distant.system.dao.DAOManager;
import com.distant.system.dao.SubjectDao;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Subject;
import com.distant.system.service.exception.ServiceException;
import com.distant.system.service.exception.ValidationException;
import com.distant.system.service.util.Validation;

import java.util.List;

public class SubjectService {

    private static final DAOManager daoManager = DAOFactory.getFactory().getMainDAOManager();
    private static final SubjectDao subjectDao = daoManager.getSubjectDao();

    private static final String CON_FIELD_EMPTY = "con.field.empty";


    public int getSizeStudentAvailableSubjects(int studentId) throws ServiceException {
        try {
            return subjectDao.getSizeStudentAvailableSubjects(studentId);
        } catch (DaoException e) {
            throw new ServiceException("Exception during number of subjects", e);
        }
    }

    public List<Subject> numberOfStudentSubjects(int studentId, int offset, int records) throws ServiceException {
        try {
            return subjectDao.numberOfStudentSubjects(studentId, offset, records);
        } catch (DaoException e) {
            throw new ServiceException("Exception during number of subjects", e);
        }
    }

    public List<Subject> numberOfAllSubjects(int offset, int records) throws ServiceException {
        try {
            return subjectDao.numberOfAllSubjects(offset, records);
        } catch (DaoException e) {
            throw new ServiceException("Exception during getting list of subjects", e);
        }
    }

    public int getSizeAllSubjects() throws ServiceException {
        try {
            return subjectDao.getSizeAllSubjects();
        } catch (DaoException e) {
            throw new ServiceException("Exception during getting all subjects", e);
        }
    }

    public void deleteSubject(int subjectId) throws ServiceException {
        try {
            subjectDao.deleteSubject(subjectId);
        } catch (DaoException e) {
            throw new ServiceException("Exception during delete subject", e);
        }
    }

    public void addSubject(String subject) throws ServiceException, ValidationException {
        try {
            if (Validation.isEmpty(subject)) {
                throw new ValidationException(CON_FIELD_EMPTY);
            } else {
                subjectDao.addSubject(subject);
            }
        } catch (DaoException e) {
            throw new ServiceException("Exception during adding subject", e);
        }
    }

    public void updateSubject(int subjectId, String value) throws ServiceException, ValidationException {
        try {
            if (Validation.isEmpty(value)) {
                throw new ValidationException(CON_FIELD_EMPTY);
            } else {
                subjectDao.updateSubject(subjectId, value);
            }
        } catch (DaoException e) {
            throw new ServiceException("Exception during updating of subject", e);
        }
    }

    public String getSubjectById(int id) throws ServiceException {
        try {
            return subjectDao.getSubjectById(id);
        } catch (DaoException e) {
            throw new ServiceException("Exception during getting subject by id", e);
        }
    }
}
