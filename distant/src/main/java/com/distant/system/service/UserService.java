package com.distant.system.service;

import com.distant.system.entity.User;
import com.distant.system.service.exception.ServiceException;
import com.distant.system.service.exception.ValidationException;

public interface UserService {

    void addStudent(User user) throws ServiceException, ValidationException;

    User logIn(String login, String password) throws ValidationException, ServiceException;
}
