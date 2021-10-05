package ru.danko.testtask.validator;

import ru.danko.testtask.controller.RequestParameter;

import java.util.Map;

public class RecordBookValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String YEAR_REGEX = "^[1-2]\\d{0,9}$";
    private static final String EMPTY_VALUE = "";

    public static boolean isRegistrationParametersCorrect(Map<String, String> parameters) {
        boolean isCorrect = true;
        if (!isIdValid(parameters.get(RequestParameter.ID_GROUP))) {
            isCorrect = false;
            parameters.put(RequestParameter.ID_GROUP, EMPTY_VALUE);
        }
        if (!isGraduationYearValid(parameters.get(RequestParameter.GRADUATION_YEAR))) {
            isCorrect = false;
            parameters.put(RequestParameter.GRADUATION_YEAR, EMPTY_VALUE);
        }
        return isCorrect;
    }

    public static boolean isIdValid(String id) {
        return isStringCorrect(id, ID_REGEX);
    }

    public static boolean isGraduationYearValid(String graduationYear) {
        return isStringCorrect(graduationYear, YEAR_REGEX) && !graduationYear.isBlank();
    }

    private static boolean isStringCorrect(String line, String regex) {
        boolean result = false;
        if (line != null) {
            result = line.matches(regex);
        }
        return result;
    }
}
