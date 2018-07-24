package com.distant.system.service;

import com.distant.system.entity.dto.ExamResult;
import com.distant.system.service.exception.ServiceException;
import com.distant.system.service.exception.ValidationException;

import java.util.List;

public interface MarkService {

    List<ExamResult> numberOfMarks(int offset, int records) throws ServiceException;

    int allMarks() throws ServiceException, ValidationException;

    void deleteMark(int userId, int subjectId) throws ServiceException;

    List<ExamResult> numberOfStudentMarks(int studentId, int offset, int records) throws ServiceException;

    int allStudentMarks(int studentId) throws ServiceException, ValidationException;

    void addMark(int mark, int studentId, int subjectId) throws ServiceException, ValidationException;

}
