package com.distant.system.controller.command.factory.impl.teacher;

import com.distant.system.controller.SessionRequestContent;
import com.distant.system.controller.command.ActionCommand;
import com.distant.system.controller.exception.NoSuchRequestParameterException;
import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.service.LanguageService;
import com.distant.system.service.ServiceFactory;
import com.distant.system.service.SubjectService;
import com.distant.system.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OpenAddQuestionCommand implements ActionCommand {

    private static final String TEACHER_ADD_QUESTION_PATH_PAGE = "path.page.teacher.add.question";
    private static final String SUBJECT_ID = "subjectId";
    private static final String LANG_ID = "langId";
    private static final String SUBJECT = "subject";
    private static final String LANG = "lang";
    private static final String PATH_PAGE_ERROR_503 = "path.page.error.503";

    private SubjectService subjectService = ServiceFactory.getInstance().getSubjectService();
    private LanguageService languageService = ServiceFactory.getInstance().getLanguageService();

    private static final Logger logger = LogManager.getLogger(OpenAddQuestionCommand.class);


    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        int subjectId;
        int langId;
        String subject;
        String lang;

        try {
            subjectId = Integer.parseInt(requestContent.getParameter(SUBJECT_ID));
            langId = Integer.parseInt(requestContent.getParameter(LANG_ID));


            subject = subjectService.getSubjectById(subjectId);
            lang = languageService.getLanguageById(langId);

            requestContent.setAttribute(SUBJECT_ID, subjectId);
            requestContent.setAttribute(LANG_ID, langId);
            requestContent.setAttribute(SUBJECT, subject);
            requestContent.setAttribute(LANG, lang);

            page = ConfigurationManager.getProperty(TEACHER_ADD_QUESTION_PATH_PAGE);

        } catch (NoSuchRequestParameterException e) {
            logger.warn("Parameter is not found", e);
            page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        } catch (ServiceException e) {
            logger.error("Service exception", e);
            page = ConfigurationManager.getProperty(PATH_PAGE_ERROR_503);
        }
        return page;
    }
}
