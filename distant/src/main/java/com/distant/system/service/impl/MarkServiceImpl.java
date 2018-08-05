package com.distant.system.service.impl;

import com.distant.system.dao.DAOFactory;
import com.distant.system.dao.DAOManager;
import com.distant.system.dao.MarkDao;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.dto.ExamResult;
import com.distant.system.service.MarkService;
import com.distant.system.service.exception.ServiceException;
import com.distant.system.service.exception.ValidationException;

import java.sql.Timestamp;
import java.util.List;

public class MarkServiceImpl implements MarkService {

    private static final DAOManager daoManager = DAOFactory.getFactory().getMainDAOManager();
    private static final MarkDao markDao = daoManager.getMarkDAO();

    private static final String CON_LIST_EMPTY = "con.list.empty";
    private static final String CON_EXAM_RESULT_FAILED = "con.exam.result.failed";

    @Override
    public List<ExamResult> numberOfMarks(int offset, int records) throws ServiceException {
        try {
            return markDao.numberOfMarks(offset, records);
        } catch (DaoException e) {
            throw new ServiceException("Exception during getting number of marks", e);
        }
    }

    @Override
    public int allMarks() throws ServiceException, ValidationException {
        try {
            if (markDao.allMarks() == 0) {
                throw new ValidationException(CON_LIST_EMPTY);
            } else {
                return markDao.allMarks();
            }
        } catch (DaoException e) {
            throw new ServiceException("Exception during getting all marks", e);
        }
    }

    @Override
    public void deleteMark(int userId, int subjectId) throws ServiceException {
        try {
            markDao.deleteMark(userId, subjectId);
        } catch (DaoException e) {
            throw new ServiceException("Exception during deleting mark", e);
        }
    }

    @Override
    public List<ExamResult> numberOfStudentMarks(int studentId, int offset, int records) throws ServiceException {
        try {
            return markDao.numberOfStudentMarks(studentId, offset, records);
        } catch (DaoException e) {
            throw new ServiceException("Exception during getting number of marks", e);
        }

    }

    @Override
    public int allStudentMarks(int studentId) throws ServiceException, ValidationException {
        try {

            if (markDao.allStudentMarks(studentId) == 0) {
                throw new ValidationException(CON_LIST_EMPTY);
            } else {
                return markDao.allStudentMarks(studentId);
            }
        } catch (DaoException e) {
            throw new ServiceException("Exception during getting all student marks", e);
        }

    }

    @Override
    public void addMark(int mark, int studentId, int subjectId) throws ServiceException, ValidationException {
        try {
            if (mark == 0) {
                throw new ValidationException(CON_EXAM_RESULT_FAILED);
            } else {
                markDao.addMark(mark, studentId, subjectId);
            }
        } catch (DaoException e) {
            throw new ServiceException("Exception during adding mark", e);
        }
    }
}
