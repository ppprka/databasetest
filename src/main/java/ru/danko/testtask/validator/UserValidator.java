package ru.danko.testtask.validator;

import java.util.Map;

public class UserValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String NAME_REGEX = "^\\p{L}{2,25}$";
    private static final String SURNAME_REGEX = "^\\p{L}{2,25}$";
    private static final String EMPTY_VALUE = "";

    public static boolean isRegistrationParametersCorrect(Map<String, String> parameters) {
        boolean isCorrect = true;
        if (!isEmailValid(parameters.get(RequestParameter.EMAIL))) {
            isCorrect = false;
            parameters.put(RequestParameter.EMAIL, EMPTY_VALUE);
        }
        if (!isNameValid(parameters.get(RequestParameter.NAME))) {
            isCorrect = false;
            parameters.put(RequestParameter.NAME, EMPTY_VALUE);
        }
        if (!isNameValid(parameters.get(RequestParameter.SURNAME))) {
            isCorrect = false;
            parameters.put(RequestParameter.SURNAME, EMPTY_VALUE);
        }
        if (!isNameValid(parameters.get(RequestParameter.PATRONYMIC))) {
            isCorrect = false;
            parameters.put(RequestParameter.PATRONYMIC, EMPTY_VALUE);
        }
        if (!isPasswordValid(parameters.get(RequestParameter.PASSWORD)) ||
                !(parameters.get(RequestParameter.PASSWORD)
                        .equals(parameters.get(RequestParameter.PASSWORD_REPEAT)))) {
            isCorrect = false;
            parameters.put(RequestParameter.PASSWORD, EMPTY_VALUE);
            parameters.put(RequestParameter.PASSWORD_REPEAT, EMPTY_VALUE);
        }
        return isCorrect;
    }

    public static boolean isIdValid(String id) {
        return isStringCorrect(id, ID_REGEX);
    }

    public static boolean isNameValid(String name) {
        return isStringCorrect(name, NAME_REGEX) && !name.isBlank();
    }

    public static boolean isSurnameValid(String surname) {
        return isStringCorrect(surname, SURNAME_REGEX) && !surname.isBlank();
    }

    private static boolean isStringCorrect(String line, String regex) {
        boolean result = false;
        if (line != null) {
            result = line.matches(regex);
        }
        return result;
    }
}
