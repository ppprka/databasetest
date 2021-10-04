package ru.danko.testtask.validator;

import ru.danko.testtask.controller.RequestParameter;

import java.util.Map;

public class StudentGroupValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String NUMBER_REGEX = "^\\p{L}{2,5}$";
    private static final String EMPTY_VALUE = "";

    public static boolean isRegistrationParametersCorrect(Map<String, String> parameters) {
        boolean isCorrect = true;
        if (!isIdValid(parameters.get(RequestParameter.GROUP_ID))) {
            isCorrect = false;
            parameters.put(RequestParameter.GROUP_ID, EMPTY_VALUE);
        }
        if (!isNumberValid(parameters.get(RequestParameter.NUMBER))) {
            isCorrect = false;
            parameters.put(RequestParameter.NUMBER, EMPTY_VALUE);
        }
        return isCorrect;
    }

    public static boolean isIdValid(String id) {
        return isStringCorrect(id, ID_REGEX);
    }

    public static boolean isNumberValid(String number) {
        return isStringCorrect(number, NUMBER_REGEX) && !number.isBlank();
    }

    private static boolean isStringCorrect(String line, String regex) {
        boolean result = false;
        if (line != null) {
            result = line.matches(regex);
        }
        return result;
    }
}
