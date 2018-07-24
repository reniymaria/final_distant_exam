package com.distant.system.service;

import com.distant.system.entity.Subject;
import com.distant.system.service.exception.ServiceException;
import com.distant.system.service.exception.ValidationException;

import java.util.List;

public interface SubjectService {

    int getSizeStudentAvailableSubjects(int studentId) throws ServiceException;

    List<Subject> numberOfStudentSubjects(int studentId, int offset, int records) throws ServiceException;

    List<Subject> numberOfAllSubjects(int offset, int records) throws ServiceException;

    int getSizeAllSubjects() throws ServiceException;

    void deleteSubject(int subjectId) throws ServiceException;

    void addSubject(String subject) throws ServiceException, ValidationException;

    void updateSubject(int subjectId, String value) throws ServiceException, ValidationException;

    String getSubjectById(int id) throws ServiceException;
}
