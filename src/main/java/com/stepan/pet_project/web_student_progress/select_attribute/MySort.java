package com.stepan.pet_project.web_student_progress.select_attribute;

import org.springframework.data.domain.Sort;

import java.util.Locale;

/**
 * @author Cupriyanovich Stepan
 * @version 1.0
 */

public class MySort {
    public static Sort getSortByPassedArgument(String textForSort) {
        Sort sort;

        StringBuilder result;
        String firstWord;

        String[] arrWords = textForSort.split(" ");

        String ascOrDesc = arrWords[arrWords.length-1];
        firstWord = arrWords[0].toLowerCase(Locale.ROOT);

        result = new StringBuilder(firstWord);

        for (int i = 1; i < arrWords.length-1; i++) {
            String firstSymbol = Character.toString(arrWords[i].charAt(0));
            result.append(arrWords[i].replaceFirst(firstSymbol, firstSymbol.toUpperCase()));
        }

        if (ascOrDesc.equals("asc")) {
            sort = Sort.by(Sort.Direction.ASC, result.toString());
        } else if (ascOrDesc.equals("desc")) {
            sort = Sort.by(Sort.Direction.DESC, result.toString());
        } else{
            return null;
        }

        return sort;
    }
}
