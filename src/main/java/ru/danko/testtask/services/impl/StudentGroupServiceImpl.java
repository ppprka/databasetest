package ru.danko.testtask.services.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.danko.testtask.dao.StudentDao;
import ru.danko.testtask.dao.StudentGroupDao;
import ru.danko.testtask.dao.impl.StudentDaoImpl;
import ru.danko.testtask.dao.impl.StudentGroupDaoImpl;
import ru.danko.testtask.entity.Student;
import ru.danko.testtask.entity.StudentGroup;
import ru.danko.testtask.exception.DaoServiceException;
import ru.danko.testtask.services.StudentGroupService;
import ru.danko.testtask.validator.RequestParameter;
import ru.danko.testtask.validator.StudentValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StudentGroupServiceImpl implements StudentGroupService {
    private static final Logger logger = LogManager.getLogger(StudentServiceImpl.class);
    private final StudentGroupDao studentGroupDao = StudentGroupDaoImpl.getInstance();

    @Override
    public Optional<StudentGroup> findById(String id) throws DaoServiceException {
        Optional<StudentGroup> optionalStudentGroup = Optional.empty();
        try {
            if (StudentValidator.isIdValid(id)) {
                long studentGroupId = Long.parseLong(id);
                optionalStudentGroup = studentGroupDao.findById(studentGroupId);
            }
            logger.log(Level.INFO, "Finded by id");
        } catch (DaoServiceException e) {
            logger.log(Level.ERROR, "Error while finding student by id");
            throw new DaoServiceException("Error while finding user by id", e);
        }
        return optionalStudentGroup;
    }

    @Override
    public Optional<StudentGroup> findByNumber(String number) throws DaoServiceException {
        Optional<StudentGroup> optionalStudentGroup = Optional.empty();
        StudentValidator studentValidator = new StudentValidator();
        try {
            if (studentValidator.isNameValid(number)) {
                optionalStudentGroup = studentGroupDao.findByNumber(number);
            }
            logger.log(Level.INFO, "Finded by number");
        } catch (DaoServiceException e) {
            logger.log(Level.ERROR, "Error while getting student by namenumber");
            throw new DaoServiceException("Error while finding student group by number", e);
        }
        return optionalStudentGroup;
    }

    @Override
    public List<StudentGroup> findAll() throws DaoServiceException {
        try {
            List<StudentGroup> list = studentGroupDao.findAll();
            logger.log(Level.INFO, "Finded all");
            return list;
        } catch (DaoServiceException e) {
            logger.log(Level.ERROR, "Error while getting all student groups");
            throw new DaoServiceException("Error while finding all student group", e);
        }
    }

    @Override
    public boolean add(Map<String, String> parameters) throws DaoServiceException {
        boolean result = false;
        String id = parameters.get(RequestParameter.ID);
        String number = parameters.get(RequestParameter.NUMBER);
        StudentValidator studentValidator = new StudentValidator();
        if (studentValidator.isRegistrationParametersCorrect(parameters)) {
            try {
                StudentGroup studentGroup = new StudentGroup();
                studentGroup.setId(Long.parseLong(id));
                studentGroup.setNumber(number);
                result = studentGroupDao.add(studentGroup);
                logger.log(Level.INFO, "Added");
            } catch (DaoServiceException e) {
                logger.log(Level.ERROR, "Error while adding student group");
                throw new DaoServiceException("Error while adding student group", e);
            }
        }
        return result;
    }

    @Override
    public boolean update(String id) throws DaoServiceException {
        boolean result = false;
        if (studentGroupDao.findById(Long.parseLong(id)).isPresent() && StudentValidator.isIdValid(id)) {
            try {
                StudentGroup studentGroup = studentGroupDao.findById(Long.parseLong(id)).get();
                result = studentGroupDao.update(studentGroup);
                logger.log(Level.INFO, "Updated");
            } catch (DaoServiceException e) {
                logger.log(Level.ERROR, "Error while updating student group");
                throw new DaoServiceException("Error while updating student group", e);
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) throws DaoServiceException {
        boolean result = false;
        if (studentGroupDao.findById(Long.parseLong(id)).isPresent() && StudentValidator.isIdValid(id)) {
            try {
                result = studentGroupDao.delete(Long.parseLong(id));
                logger.log(Level.INFO, "Deleted");
            } catch (DaoServiceException e) {
                logger.log(Level.ERROR, "Error while deleting student group");
                throw new DaoServiceException("Error while deleting student group", e);
            }
        }
        return result;
    }
}
