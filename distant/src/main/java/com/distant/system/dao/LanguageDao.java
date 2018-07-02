package com.distant.system.dao;

import com.distant.system.dao.exception.DaoException;

public interface LanguageDao {

    String SQL_FINDID_BY_LANG = "SELECT id FROM languages WHERE language =?";

    int findId(String language) throws DaoException;
}
