package com.distant.system.service;

import com.distant.system.service.exception.ServiceException;

public interface LanguageService {

    int findId(String language) throws ServiceException;

    String getLanguageById(int id) throws ServiceException;

}
