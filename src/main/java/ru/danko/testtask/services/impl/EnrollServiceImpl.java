package ru.danko.testtask.services.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.danko.testtask.dao.EnrollDao;
import ru.danko.testtask.dao.RecordBookDao;
import ru.danko.testtask.dao.impl.EnrollDaoImpl;
import ru.danko.testtask.dao.impl.RecordBookDaoImpl;
import ru.danko.testtask.entity.Enroll;
import ru.danko.testtask.entity.RecordBook;
import ru.danko.testtask.exception.DaoServiceException;
import ru.danko.testtask.services.EnrollService;
import ru.danko.testtask.validator.RequestParameter;
import ru.danko.testtask.validator.StudentValidator;

import java.util.Map;
import java.util.Optional;

public class EnrollServiceImpl implements EnrollService {
    private static final Logger logger = LogManager.getLogger(StudentServiceImpl.class);
    private final EnrollDao enrollDao = EnrollDaoImpl.getInstance();

    @Override
    public Optional<Enroll> findGroupByStudentId(String idStudent) throws DaoServiceException {
        Optional<Enroll> optionalEnroll = Optional.empty();
        try {
            if (StudentValidator.isIdValid(idStudent)) {
                long studentId = Long.parseLong(idStudent);
                optionalEnroll = enrollDao.findGroupByStudentId(studentId);
            }
            logger.log(Level.INFO, "Finded by id");
        } catch (DaoServiceException e) {
            logger.log(Level.ERROR, "Error while finding group id by student id");
            throw new DaoServiceException("Error while finding group id by student id", e);
        }
        return optionalEnroll;
    }

}
