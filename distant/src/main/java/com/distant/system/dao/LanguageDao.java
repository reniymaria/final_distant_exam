package com.distant.system.dao;

import com.distant.system.dao.exception.DaoException;

public interface LanguageDao {

    String SQL_FINDID_BY_LANG = "SELECT id FROM languages WHERE language =?";
    String SQL_LANGUAGE_BY_ID ="SELECT language FROM languages WHERE id =?";

    int findId(String language) throws DaoException;

    String getLanguageById(int id) throws DaoException;
}
