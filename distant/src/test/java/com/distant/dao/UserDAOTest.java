package com.distant.dao;

import com.distant.system.dao.DAOFactory;
import com.distant.system.dao.UserDao;
import com.distant.system.dao.conection.ConnectionPool;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.User;
import com.distant.system.service.util.HashUtil;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class UserDAOTest {

    private static final UserDao userDao = DAOFactory.getFactory().getMainDAOManager().getUserDAO();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        TestUtil.initializeConnectionPool();
        TestUtil.initializeDB();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        ConnectionPool.getInstance().destroy();
    }

    @DataProvider
    public static Object[][] provideLoginData() {
        return new Object[][]{{"petr", false}, {"teacher", true},
                {"student", true}, {"reniymaria", true},};
    }

    @Test
    @UseDataProvider("provideLoginData")
    public void testIsLoginExists(String login, boolean expected) throws DaoException {

        boolean isLoginExists = userDao.checkIfExist(login);

        assertEquals(expected, isLoginExists);
    }

    @Test
    public void testAddStudent() throws DaoException {
        User user = new User();
        user.setLogin("testuser");
        user.setPassword("1234");
        user.setName("Vlad");
        user.setSurname("Cepesh");
        user.setRole("student");
        userDao.addStudent(user);
        String userPosition = userDao.findPosition("testuser");

        assertEquals(user.getRole(), userPosition);
    }


    @DataProvider
    public static Object[][] provideUsersAuthorizationData() {
        return new Object[][]{{"teacher", "123", true}, {"student", "123", true},
                {"user", "password1234", false},};
    }

    @Test
    @UseDataProvider("provideUsersAuthorizationData")
    public void testGetUser(String login, String password, boolean expected) throws DaoException {
        String hashedPassword = HashUtil.md5Apache(password);

        boolean isUserFound = userDao.isAuthorized(login, hashedPassword);

        assertEquals(expected, isUserFound);
    }

    @Test
    public void testGetUserNameSurname() throws DaoException {
        String login = "student";
        String expectedUserName = "Vasya Zuk";

        String gottenUserName = userDao.getNameSurname(login);

        assertEquals(expectedUserName, gottenUserName);
    }
}
