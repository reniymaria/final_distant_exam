package com.distant.system.service;

import com.distant.system.service.exception.ServiceException;


/**
 * The Interface LanguageService
 *
 * defines methods for working with language.
 */
public interface LanguageService {
    /**
     * @param language the name of language
     * @return id of language
     * @throws ServiceException the exception during getting data from DB
     */
    int findId(String language) throws ServiceException;

    /**
     * @param id the id of language
     * @return name of language
     * @throws ServiceException the exception during getting data from DB
     */
    String getLanguageById(int id) throws ServiceException;

}
