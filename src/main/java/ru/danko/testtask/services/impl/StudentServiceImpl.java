package ru.danko.testtask.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import ru.danko.testtask.dao.StudentDao;
import ru.danko.testtask.dao.impl.StudentDaoImpl;
import ru.danko.testtask.entity.Student;
import ru.danko.testtask.exception.DaoServiceException;
import ru.danko.testtask.services.StudentService;
import ru.danko.testtask.validator.RequestParameter;
import ru.danko.testtask.validator.StudentValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {
    private static final Logger logger = LogManager.getLogger(StudentServiceImpl.class);
    private final StudentDao studentDao = StudentDaoImpl.getInstance();

    @Override
    public Optional<Student> findByNameAndSurname(String name, String surname) throws DaoServiceException {
        Optional<Student> optionalStudent;
        try {
            optionalStudent = studentDao.findByNameAndSurname(name,surname);
            logger.log(Level.INFO, "Finded by name and surname");
        } catch (DaoServiceException e) {
            logger.log(Level.FATAL, "Error while getting student by name and surname");
            throw new DaoServiceException("Error while getting student by name and surname", e);
        }
        return optionalStudent;
    }

    @Override
    public Optional<Student> findById(String id) throws DaoServiceException {
        Optional<Student> optionalStudent = Optional.empty();
        try {
            if (StudentValidator.isIdValid(id)) {
                long userId = Long.parseLong(id);
                optionalStudent = studentDao.findById(userId);
            }
            logger.log(Level.INFO, "Finded by id");
        } catch (DaoServiceException e) {
            logger.log(Level.FATAL, "Error while finding student by id");
            throw new DaoServiceException("Error while finding user by id", e);
        }
        return optionalStudent;
    }

    @Override
    public Optional<Student> findByName(String name) throws DaoServiceException {
        Optional<Student> optionalStudent = Optional.empty();
        try {
            if (StudentValidator.isNameValid(name)) {
                optionalStudent = studentDao.findByName(name);
            }
            logger.log(Level.INFO, "Finded by name");
        } catch (DaoServiceException e) {
            logger.log(Level.FATAL, "Error while getting student by name");
            throw new DaoServiceException("Error while finding user by name", e);
        }
        return optionalStudent;
    }

    @Override
    public Optional<Student> findBySurname(String surname) throws DaoServiceException {
        Optional<Student> optionalStudent = Optional.empty();
        try {
            if (StudentValidator.isNameValid(surname)) {
                optionalStudent = studentDao.findBySurname(surname);
            }
            logger.log(Level.INFO, "Finded by surname");
        } catch (DaoServiceException e) {
            logger.log(Level.FATAL, "Error while getting student by surname");
            throw new DaoServiceException("Error while finding user by surname", e);
        }
        return optionalStudent;
    }

    @Override
    public List<Student> findAll() throws DaoServiceException {
        try {
            List<Student> list = studentDao.findAll();
            logger.log(Level.INFO, "Finded all");
            return list;
        }
        catch (DaoServiceException e) {
            logger.log(Level.FATAL, "Error while getting all students");
            throw new DaoServiceException("Error while finding all students", e);
        }
    }

    @Override
    public boolean addStudent(Map<String,String> parameters) throws DaoServiceException {
        boolean result = false;
        String id = parameters.get(RequestParameter.ID);
        String name = parameters.get(RequestParameter.NAME);
        String surname = parameters.get(RequestParameter.SURNAME);
        if(StudentValidator.isRegistrationParametersCorrect(parameters)){
            try{
                Student student = new Student();
                student.setId(Long.parseLong(id));
                student.setName(name);
                student.setSurname(surname);
                result = studentDao.add(student);
                logger.log(Level.INFO, "Added");
            }
            catch (DaoServiceException e){
                logger.log(Level.FATAL, "Error while adding student");
                throw new DaoServiceException("Error while adding student", e);
            }
        }
        return result;
    }

    @Override
    public boolean updateStudent(String id) throws DaoServiceException {
        boolean result = false;
        if(studentDao.findById(Long.parseLong(id)).isPresent()&&StudentValidator.isIdValid(id)){
            try{
                Student student = studentDao.findById(Long.parseLong(id)).get();
                result = studentDao.update(student);
                logger.log(Level.INFO, "Updated");
            }
            catch (DaoServiceException e){
                logger.log(Level.FATAL, "Error while updating student");
                throw new DaoServiceException("Error while updating student", e);
            }
        }
        return result;
    }

    @Override
    public boolean deleteStudent(String id) throws DaoServiceException {
        boolean result = false;
        if(studentDao.findById(Long.parseLong(id)).isPresent()&&StudentValidator.isIdValid(id)){
            try{
                result = studentDao.delete(Long.parseLong(id));
                logger.log(Level.INFO, "Deleted");
            }
            catch (DaoServiceException e){
                logger.log(Level.FATAL, "Error while deleting student");
                throw new DaoServiceException("Error while deleting student", e);
            }
        }
        return result;
    }
}
