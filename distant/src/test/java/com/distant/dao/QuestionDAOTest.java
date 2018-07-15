package com.distant.dao;

import com.distant.system.dao.DAOFactory;
import com.distant.system.dao.QuestionDao;
import com.distant.system.dao.conection.ConnectionPool;
import com.distant.system.dao.exception.DaoException;
import com.distant.system.entity.Question;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class QuestionDAOTest {

    private static final QuestionDao questionDao = DAOFactory.getFactory().getMainDAOManager().getQuestionDao();

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
    public void addQuestion() throws DaoException {
        Question question = new Question();
        question.setQuestion("question?");
        question.setAnswer1("answer1");
        question.setAnswer2("answer2");
        question.setAnswer3("answer3");
        question.setCorrectAnswer(2);
        question.setLanguageId(1);
        question.setSubjectId(1);

        questionDao.add(question);

        List<Question> questions = questionDao.getQuestionsById(1, 1);

        assertEquals(isQuestionExist(questions, question), false);

    }

    private boolean isQuestionExist(List<Question> questions, Question question) {

        for (Question quest : questions) {
            if (quest.equals(question)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void updateQuestion() throws DaoException {

        Question question1 = questionDao.find(5);

        Question question2 = new Question();
        question2.setQuestionId(question1.getQuestionId());
        question2.setQuestion("5+5");
        question2.setAnswer1("10");
        question2.setAnswer2("11");
        question2.setAnswer3("9");
        question2.setCorrectAnswer(1);
        question2.setLanguageId(question1.getLanguageId());
        question2.setSubjectId(question1.getSubjectId());

        questionDao.update(question2);
        Question question3 = questionDao.find(5);

        assertEquals(question2.getQuestion(), question3.getQuestion());

    }


    @Test
    public void deleteQuestion() throws DaoException {

        Question question1 = questionDao.find(5);
        List<Question> questions = questionDao.getQuestionsById(1, 1);
        questionDao.deleteQuestion(5);

        assertEquals(isQuestionExist(questions, question1), true);


    }


}
