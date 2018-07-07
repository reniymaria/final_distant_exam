package com.distant.system.dao.util;

import com.distant.system.entity.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DaoUtil {

    private DaoUtil() {
        throw new AssertionError("Class contains static methods only. You should not instantiate it!");
    }


    public static <T> List<T> numberOfStrings(List<T> allItems, int offset, int records) {
        List<T> result = new ArrayList<>(records);
        for (int i = 0; i < records; i++) {
            int index = offset * records + i;
            if (index >= allItems.size()) {
                break;
            }
            result.add(allItems.get(index));
        }
        return result;
    }

    public static List<Question> getRandomFive(List<Question> questions) {
        List<Question> fiveQuestions = new ArrayList<>();
        Collections.shuffle(questions);
        for (Question question : questions) {
            fiveQuestions.add(question);
            if (fiveQuestions.size() < 5) {
                continue;
            } else break;
        }
        return fiveQuestions;
    }

}
