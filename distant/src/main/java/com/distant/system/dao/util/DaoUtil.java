package com.distant.system.dao.util;

import com.distant.system.entity.Question;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DaoUtil {

    private DaoUtil() {
        throw new AssertionError("Class contains static methods only. You should not instantiate it!");
    }


    public static  <T> List<T> numberOfStrings(List<T> allItems, int offset, int records) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < allItems.size(); i++) {
            if (offset == 0) {
                if (i < records) {
                    result.add(allItems.get(i));

                }
            } else if (offset > 0) {
                if (i > (offset * records) && i < (offset * records + records + 1)) {
                    result.add(allItems.get(i));
                }

            }
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
