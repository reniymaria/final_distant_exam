package com.distant.system.dao;

public interface MarkDao {

    String SQL_ADD_MARK = "UPDATE marks SET mark=? WHERE studentId = ? and subjectId=? ";

    void addMark(int mark, int studentId, int subjectId);


}
