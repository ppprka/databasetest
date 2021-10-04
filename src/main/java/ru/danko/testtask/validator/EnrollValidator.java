package ru.danko.testtask.validator;

import ru.danko.testtask.controller.RequestParameter;

import java.util.Map;

public class EnrollValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String EMPTY_VALUE = "";
    public static boolean isRegistrationParametersCorrect(Map<String, String> parameters) {
        boolean isCorrect = true;
        if (isIdValid(parameters.get(RequestParameter.ID_STUDENT))) {
            isCorrect = false;
            parameters.put(RequestParameter.ID_STUDENT, EMPTY_VALUE);
        }
        if (isIdValid(parameters.get(RequestParameter.ID_GROUP))) {
            isCorrect = false;
            parameters.put(RequestParameter.ID_GROUP, EMPTY_VALUE);
        }

        return isCorrect;
    }

    public static boolean isIdValid(String id) {
        return isStringCorrect(id, ID_REGEX);
    }

    private static boolean isStringCorrect(String line, String regex) {
        boolean result = false;
        if (line != null) {
            result = line.matches(regex);
        }
        return result;
    }
}
