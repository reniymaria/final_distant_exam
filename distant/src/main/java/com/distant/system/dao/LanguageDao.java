package com.distant.system.dao;

import com.distant.system.dao.exception.DaoException;

/**
 * The Interface LanguageDao
 *
 * defines methods for working with language.
 */
public interface LanguageDao {

    String SQL_FINDID_BY_LANG = "SELECT id FROM languages WHERE language =?";
    String SQL_LANGUAGE_BY_ID = "SELECT language FROM languages WHERE id =?";

    /**
     * @param language the name of language
     * @return id of language
     * @throws DaoException the exception during getting data from DB
     */
    int findId(String language) throws DaoException;

    /**
     * @param id the id of language
     * @return name of language
     * @throws DaoException the exception during getting data from DB
     */
    String getLanguageById(int id) throws DaoException;
}
