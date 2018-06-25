package com.distant.system.service.daoservice;

import com.distant.system.dao.DAOFactory;
import com.distant.system.dao.DAOManager;
import com.distant.system.dao.MarkDao;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.dto.ExamResult;

import java.util.List;

public class MarkService {

    private static final DAOManager daoManager = DAOFactory.getFactory().getMainDAOManager();
    private static final MarkDao markDao = daoManager.getMarkDAO();

    public List<ExamResult> getExamMarks() throws DaoException {
        return markDao.getExamMarks();
    }

    public List<ExamResult> numberOfMarks(int offset, int records) throws DaoException {
        return markDao.numberOfMarks(offset, records);
    }

    public int allMarks() throws DaoException {
        return markDao.allMarks();
    }

    public void deleteMark(int userId, int subjectId) throws DaoException {
        markDao.deleteMark(userId, subjectId);
    }
}
