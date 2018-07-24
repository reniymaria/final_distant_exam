package com.distant.system.controller.util;

import com.distant.system.entity.Question;
import com.distant.system.service.impl.QuestionServiceImpl;
import com.distant.system.service.exception.ServiceException;
import com.distant.system.service.exception.ValidationException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseExcel {

    private static final int I = 0;
    private static QuestionServiceImpl questionService = new QuestionServiceImpl();

    private ParseExcel() {
        throw new AssertionError("Class contains static methods only. You should not instantiate it!");
    }


    public static boolean parseExcel(String file, int langId, int subjectId) throws IOException, InvalidFormatException {

        Workbook workbook = WorkbookFactory.create(new File(file));

        Sheet sheet = workbook.getSheetAt(I);


        List<Question> questions = new ArrayList<>();

        int starRow = sheet.getFirstRowNum();
        int endRow = sheet.getLastRowNum();

        for (int i = starRow + 1; i < endRow; i++) {
            Question question = new Question();

            Cell c1 = workbook.getSheetAt(I).getRow(i).getCell(0);
            Cell c2 = workbook.getSheetAt(I).getRow(i).getCell(1);
            Cell c3 = workbook.getSheetAt(I).getRow(i).getCell(2);
            Cell c4 = workbook.getSheetAt(I).getRow(i).getCell(3);
            Cell c5 = workbook.getSheetAt(I).getRow(i).getCell(4);

            question.setQuestion(c1.toString());
            question.setAnswer1(c2.toString());
            question.setAnswer2(c3.toString());
            question.setAnswer3(c4.toString());
            question.setCorrectAnswer((int) c5.getNumericCellValue());
            question.setLanguageId(langId);
            question.setSubjectId(subjectId);

            questions.add(question);
        }

        try {
            questionService.add(questions);
        } catch (ValidationException | ServiceException e) {
            return false;
        }
        return true;
    }
}


