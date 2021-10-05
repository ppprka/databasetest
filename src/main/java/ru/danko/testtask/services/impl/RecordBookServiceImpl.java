package ru.danko.testtask.services.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.danko.testtask.dao.RecordBookDao;
import ru.danko.testtask.dao.impl.RecordBookDaoImpl;
import ru.danko.testtask.entity.RecordBook;
import ru.danko.testtask.exception.DaoServiceException;
import ru.danko.testtask.services.RecordBookService;
import ru.danko.testtask.validator.RequestParameter;
import ru.danko.testtask.validator.StudentValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RecordBookServiceImpl implements RecordBookService {
    private static final Logger logger = LogManager.getLogger(StudentServiceImpl.class);
    private final RecordBookDao recordBookDao = RecordBookDaoImpl.getInstance();

    @Override
    public Optional<RecordBook> findById(String id) throws DaoServiceException {
        Optional<RecordBook> optionalRecordBook = Optional.empty();
        try {
            if (StudentValidator.isIdValid(id)) {
                long studentId = Long.parseLong(id);
                optionalRecordBook = recordBookDao.findById(studentId);
            }
            logger.log(Level.INFO, "Finded by id");
        } catch (DaoServiceException e) {
            logger.log(Level.ERROR, "Error while finding record book by id");
            throw new DaoServiceException("Error while finding record book by id", e);
        }
        return optionalRecordBook;
    }

    @Override
    public Optional<RecordBook> findByGraduationYear(String graduationYear) throws DaoServiceException {
        Optional<RecordBook> optionalRecordBook = Optional.empty();
        try {
            if (StudentValidator.isNumberValid(graduationYear)) {
                optionalRecordBook = recordBookDao.findByGraduationYear(graduationYear);
            }
            logger.log(Level.INFO, "Finded by name");
        } catch (DaoServiceException e) {
            logger.log(Level.ERROR, "Error while getting record book by graduation year");
            throw new DaoServiceException("Error while finding record book by graduation year", e);
        }
        return optionalRecordBook;
    }

    @Override
    public Optional<RecordBook> findByGroupId(String groupId) throws DaoServiceException {
        Optional<RecordBook> optionalRecordBook = Optional.empty();
        try {
            if (StudentValidator.isIdValid(groupId)) {
                optionalRecordBook = recordBookDao.findByGroupId(groupId);
            }
            logger.log(Level.INFO, "Selected by group id");
        } catch (DaoServiceException e) {
            logger.log(Level.ERROR, "Error while finding record book by group id");
            throw new DaoServiceException("Error while finding record book by group id", e);
        }
        return optionalRecordBook ;
    }

    @Override
    public List<RecordBook> findAll() throws DaoServiceException {
        try {
            List<RecordBook> list = recordBookDao.findAll();
            logger.log(Level.INFO, "Selected all");
            return list;
        } catch (DaoServiceException e) {
            logger.log(Level.ERROR, "Error while getting all record books");
            throw new DaoServiceException("Error while finding all record books", e);
        }
    }

    @Override
    public boolean add(Map<String, String> parameters) throws DaoServiceException {
        boolean result = false;
        String id = parameters.get(RequestParameter.ID);
        String graduationYear = parameters.get(RequestParameter.GRADUATION);
        String groupId = parameters.get(RequestParameter.GROUP_ID);
        StudentValidator studentValidator = new StudentValidator();
        if (studentValidator.isRegistrationParametersCorrect(parameters)) {
            try {
                RecordBook recordBook = new RecordBook();
                recordBook.setId(Long.parseLong(id));
                recordBook.setGraduationYear(graduationYear);
                recordBook.setGroup_id(groupId);
                result = recordBookDao.add(recordBook);
                logger.log(Level.INFO, "Added");
            } catch (DaoServiceException e) {
                logger.log(Level.ERROR, "Error while adding record book");
                throw new DaoServiceException("Error while adding record book", e);
            }
        }
        return result;
    }

    @Override
    public boolean update(String id) throws DaoServiceException {
        boolean result = false;
        if (recordBookDao.findById(Long.parseLong(id)).isPresent() && StudentValidator.isIdValid(id)) {
            try {
                RecordBook recordBook = recordBookDao.findById(Long.parseLong(id)).get();
                result = recordBookDao.update(recordBook);
                logger.log(Level.INFO, "Updated");
            } catch (DaoServiceException e) {
                logger.log(Level.ERROR, "Error while updating record book");
                throw new DaoServiceException("Error while updating record book", e);
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) throws DaoServiceException {
        boolean result = false;
        if (recordBookDao.findById(Long.parseLong(id)).isPresent() && StudentValidator.isIdValid(id)) {
            try {
                result = recordBookDao.delete(Long.parseLong(id));
                logger.log(Level.INFO, "Deleted");
            } catch (DaoServiceException e) {
                logger.log(Level.ERROR, "Error while deleting record book");
                throw new DaoServiceException("Error while deleting record book", e);
            }
        }
        return result;
    }
}
