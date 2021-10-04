package ru.danko.testtask.validator;

import ru.danko.testtask.controller.RequestParameter;

import java.util.Map;

public class StudentValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String NAME_REGEX = "^\\p{L}{2,25}$";
    private static final String EMPTY_VALUE = "";

    public static boolean isRegistrationParametersCorrect(Map<String, String> parameters) {
        boolean isCorrect = true;
        if (!isNameValid(parameters.get(RequestParameter.NAME))) {
            isCorrect = false;
            parameters.put(RequestParameter.NAME, EMPTY_VALUE);
        }
        if (!isSurnameValid(parameters.get(RequestParameter.SURNAME))) {
            isCorrect = false;
            parameters.put(RequestParameter.SURNAME, EMPTY_VALUE);
        }
        return isCorrect;
    }

    public static boolean isNameValid(String name) {
        return isStringCorrect(name, NAME_REGEX) && !name.isBlank();
    }

    public static boolean isSurnameValid(String surname) {
        return isStringCorrect(surname, NAME_REGEX) && !surname.isBlank();
    }

    private static boolean isStringCorrect(String line, String regex) {
        boolean result = false;
        if (line != null) {
            result = line.matches(regex);
        }
        return result;
    }
}
