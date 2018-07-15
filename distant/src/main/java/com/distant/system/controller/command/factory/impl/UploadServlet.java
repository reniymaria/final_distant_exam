package com.distant.system.controller.command.factory.impl;

import com.distant.system.controller.util.ConfigurationManager;
import com.distant.system.controller.util.ParseExcel;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

    private static final String ACTION_COMPLETED = "path.page.action.completed";
    private static final String SUBJECT_ID_ATTR = "subjectId";
    private static final String LANG_ID_ATTR = "langId";
    private static final String I18N_CONTENT = "i18n.content";
    private static final String LANGUAGE = "language";
    private static final String CON_SUCCESS_UPLOAD = "con.success.upload";
    private static final String UPLOAD_SUCCESS = "uploadSuccess";
    private static final String PATTERN_DATE = "dd-M-yyyy-HH-mm-ss";

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LogManager.getLogger(UploadServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Locale locale;

        HttpSession session = request.getSession();
        locale = new Locale(session.getAttribute(LANGUAGE).toString());

        int subjectId = (int) session.getAttribute(SUBJECT_ID_ATTR);
        int langId = (int) session.getAttribute(LANG_ID_ATTR);

        ResourceBundle bundle = ResourceBundle.getBundle(I18N_CONTENT, locale);
        String pathName = null;

        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        Date dateNow = new Date();
                        SimpleDateFormat ft = new SimpleDateFormat(PATTERN_DATE);
                        String name = new File(item.getName()).getName();
                        pathName = "D:\\upload" + File.separator + ft.format(dateNow)+"_"+ name;
                        item.write(new File(pathName));
                    }
                }

                ParseExcel.parseExcel(pathName, langId, subjectId);
                request.setAttribute(UPLOAD_SUCCESS, bundle.getString(CON_SUCCESS_UPLOAD));

                request.setAttribute(LANG_ID_ATTR, langId);
                request.setAttribute(SUBJECT_ID_ATTR, subjectId);
            } catch (Exception ex) {
               LOGGER.error("File is not uploaded");
            }
        }

        request.getRequestDispatcher(ConfigurationManager.getProperty(ACTION_COMPLETED)).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        HttpSession session = request.getSession();

        int subjectId = (int) session.getAttribute(SUBJECT_ID_ATTR);
        int langId = (int) session.getAttribute(LANG_ID_ATTR);

        request.setAttribute(LANG_ID_ATTR, langId);
        request.setAttribute(SUBJECT_ID_ATTR, subjectId);

        request.getRequestDispatcher(ConfigurationManager.getProperty(ACTION_COMPLETED)).forward(request, response);

    }
}
