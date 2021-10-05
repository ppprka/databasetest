package ru.danko.testtask.validator;

import java.util.Map;

public class StudentValidator {
    private static final String ID_REGEX = "^[1-9]\\d{0,9}$";
    private static final String NAME_REGEX = "^\\p{L}{2,25}$";
    private static final String NUMBER_REGEX = "^\\p{L}{2,5}$";
    private static final String GRADUATION_REGEX = "^[1-2]\\d{0,9}$";
    private static final String EMPTY_VALUE = "";

    public static boolean isRegistrationParametersCorrect(Map<String, String> parameters) {
        boolean isCorrect = true;
        if (!isIdValid(parameters.get(RequestParameter.ID))) {
            isCorrect = false;
            parameters.put(RequestParameter.ID, EMPTY_VALUE);
        }
        if (!isIdValid(parameters.get(RequestParameter.GROUP_ID))) {
            isCorrect = false;
            parameters.put(RequestParameter.GROUP_ID, EMPTY_VALUE);
        }
        if (!isIdValid(parameters.get(RequestParameter.ID_STUDENT))) {
            isCorrect = false;
            parameters.put(RequestParameter.ID_STUDENT, EMPTY_VALUE);
        }
        if (!isIdValid(parameters.get(RequestParameter.ID_GROUP))) {
            isCorrect = false;
            parameters.put(RequestParameter.ID_GROUP, EMPTY_VALUE);
        }
        if (!isNameValid(parameters.get(RequestParameter.NAME))) {
            isCorrect = false;
            parameters.put(RequestParameter.NAME, EMPTY_VALUE);
        }
        if (!isNameValid(parameters.get(RequestParameter.SURNAME))) {
            isCorrect = false;
            parameters.put(RequestParameter.SURNAME, EMPTY_VALUE);
        }
        if (!isNumberValid(parameters.get(RequestParameter.NUMBER))) {
            isCorrect = false;
            parameters.put(RequestParameter.NUMBER, EMPTY_VALUE);
        }
        if (!isGraduationValid(parameters.get(RequestParameter.GRADUATION))) {
            isCorrect = false;
            parameters.put(RequestParameter.GRADUATION, EMPTY_VALUE);
        }
        if (!isNewStudentParametersValid(parameters.get(RequestParameter.ID),parameters.get(RequestParameter.NAME),parameters.get(RequestParameter.SURNAME))) {
            isCorrect = false;
            parameters.put(RequestParameter.ID, EMPTY_VALUE);
            parameters.put(RequestParameter.NAME,EMPTY_VALUE);
            parameters.put(RequestParameter.SURNAME,EMPTY_VALUE);
        }
        return isCorrect;
    }

    public static boolean isIdValid(String id) {
        return isStringCorrect(id, ID_REGEX);
    }

    public static boolean isNameValid(String name) {
        return isStringCorrect(name, NAME_REGEX) && !name.isBlank();
    }

    public static boolean isNumberValid(String number) {
        return isStringCorrect(number, NUMBER_REGEX) && !number.isBlank();
    }

    public static boolean isGraduationValid(String graduationYear) {
        return isStringCorrect(graduationYear, GRADUATION_REGEX) && !graduationYear.isBlank();
    }

    public static boolean isNewStudentParametersValid(String id,String name,String surname) {
        return isStringCorrect(id, ID_REGEX) && isStringCorrect(name, NAME_REGEX) && !name.isBlank() && isStringCorrect(surname, NAME_REGEX) && !surname.isBlank();
    }

    private static boolean isStringCorrect(String line, String regex) {
        boolean result = false;
        if (line != null) {
            result = line.matches(regex);
        }
        return result;
    }
}
