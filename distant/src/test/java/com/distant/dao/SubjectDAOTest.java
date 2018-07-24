package com.distant.dao;

import com.distant.system.dao.DAOFactory;
import com.distant.system.dao.SubjectDao;
import com.distant.system.dao.connection.ConnectionPool;
import com.distant.system.dao.exception.DaoException;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class SubjectDAOTest {

    private static final SubjectDao subjectDao = DAOFactory.getFactory().getMainDAOManager().getSubjectDao();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        TestUtil.initializeConnectionPool();

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        ConnectionPool.getInstance().destroy();
    }

    @Before
    public void setUp() throws Exception {
        TestUtil.initializeDB();
    }

    @Test
    public void addSubject() throws DaoException {

        int numberOfSubjects = subjectDao.getSizeAllSubjects();
        subjectDao.addSubject("New subject");

        assertEquals((numberOfSubjects + 1), subjectDao.getSizeAllSubjects());

    }


    @Test
    public void updateSubject() throws DaoException {

        String subjectOld = subjectDao.getSubjectById(3);
        subjectDao.updateSubject(3, "programming2");

        assertEquals(subjectOld.equals(subjectDao.getSubjectById(3)), false);

    }


    @Test
    public void deleteSubject() throws DaoException {

        int numberOfSubjects = subjectDao.getSizeAllSubjects();
        subjectDao.deleteSubject(3);

        assertEquals((numberOfSubjects - 1), subjectDao.getSizeAllSubjects());


    }


}
