package com.distant.system.service;

import com.distant.system.service.impl.*;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();
    private static final UserService userService = new UserServiceImpl();
    private static final LanguageService languageService = new LanguageServiceImpl();
    private static final MarkService markService = new MarkServiceImpl();
    private static final QuestionService questionService = new QuestionServiceImpl();
    private static final SubjectService subjectService = new SubjectServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public LanguageService getLanguageService() {
        return languageService;
    }

    public MarkService getMarkService() {
        return markService;
    }

    public QuestionService getQuestionService() {
        return questionService;
    }

    public SubjectService getSubjectService() {
        return subjectService;
    }
}
